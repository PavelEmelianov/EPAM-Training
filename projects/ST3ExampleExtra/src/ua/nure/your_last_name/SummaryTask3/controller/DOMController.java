package ua.nure.your_last_name.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.constants.XML;
import ua.nure.your_last_name.SummaryTask3.entity.Answer;
import ua.nure.your_last_name.SummaryTask3.entity.Question;
import ua.nure.your_last_name.SummaryTask3.entity.Test;
import ua.nure.your_last_name.SummaryTask3.util.Transformer;

/**
 * Controller for DOM parser.
 * 
 * @author D.Kolesnikov
 * 
 */
public class DOMController {

	private String xmlFileName;

	private Test test; // <-- container

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	/**
	 * Parses XML document with DOM analyzer.<br/>
	 * 
	 * External implementation of DOM analyzer will be used as an engine if you
	 * place it to classpath (Xerces library for example). Otherwise, the
	 * internal implementation bundled with JDK will be used.<br/>
	 * Also it is possible to control which implementation will be used with the
	 * following code:
	 * 
	 * <pre>
	 * DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(
	 * 		"FQN of the external Document Builder Factory implementation class",
	 * 		null);
	 * </pre>
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		// obtain DOM parser

		// get document builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		System.out.println("DocumentBuilderFactory ==> " + factory.getClass());
		
		// set properties for Factory
		factory.setNamespaceAware(true); // <-- XML document has namespace
		if (validate) { // <-- make parser validating
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = factory.newDocumentBuilder();

		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e; // <-- throw exception if XML document is NOT valid
			}
		});

		Document document = db.parse(xmlFileName); // <-- parse XML document
		Element root = document.getDocumentElement(); // <-- get root element

		// create container
		test = new Test();

		NodeList questionNodes = root
				.getElementsByTagName(XML.QUESTION.value());

		for (int j = 0; j < questionNodes.getLength(); j++) {
			Question question = getQuestion(questionNodes.item(j));
			test.getQuestions().add(question); // <-- add question to container
		}
	}

	/**
	 * Extracts question object from the question XML node.
	 * 
	 * @param qNode
	 *            Question node.
	 * @return Question object.
	 */
	private Question getQuestion(Node qNode) {
		Question question = new Question();
		Element qElement = (Element) qNode;

		// process question text
		Node qtNode = qElement.getElementsByTagName(XML.QUESTION_TEXT.value())
				.item(0);
		// set question text
		question.setQuestionText(qtNode.getTextContent());

		// process answers
		NodeList aNodeList = qElement.getElementsByTagName(XML.ANSWER.value());
		for (int j = 0; j < aNodeList.getLength(); j++) {
			Answer answer = getAnswer(aNodeList.item(j));
			question.getAnswers().add(answer); // <-- add answer
		}

		return question;
	}

	/**
	 * Extracts answer object from the answer XML node.
	 * 
	 * @param aNode
	 *            Answer node.
	 * @return Answer object.
	 */
	private Answer getAnswer(Node aNode) {
		Answer answer = new Answer();
		Element aElement = (Element) aNode;

		// process correct
		String correct = aElement.getAttribute(XML.CORRECT.value());
		answer.setCorrect(Boolean.valueOf(correct)); // <-- set correct

		// process content
		String content = aElement.getTextContent();
		answer.setContent(content); // <-- set content

		return answer;
	}

	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	/**
	 * Creates and returns DOM of the Test container. Root element can be
	 * qualified with prefix.
	 * 
	 * @param test
	 *            Test object.
	 * @param tnsPrefix
	 *            Prefix to qualify root element.
	 * @throws IllegalArgumentException if tnsPrefix is null or empty.
	 */
	public static Document getDocument(Test test, String tnsPrefix)
			throws ParserConfigurationException {
		
		if (tnsPrefix == null || tnsPrefix.isEmpty()) {
			throw new IllegalArgumentException(
					"Target namespace prefix cannot be null or empty");
		}

		// get document builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory
		dbf.setNamespaceAware(true); // <-- XML document has namespace

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		// create qualified name for root element
		String qualifiedName = XML.TEST.value();
		if (!tnsPrefix.isEmpty()) {
			qualifiedName = tnsPrefix + ":" + qualifiedName;
		}

		// create root element
		Element tElement = document.createElementNS(XML.TNS_URI.value(),
				qualifiedName);

		// set schema location
		tElement.setAttributeNS(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
				Constants.SCHEMA_LOCATION__ATTR_FQN,
				Constants.SCHEMA_LOCATION__URI);

		document.appendChild(tElement);

		// add questions elements
		for (Question question : test.getQuestions()) {
			// add question
			Element qElement = document.createElement(XML.QUESTION.value());
			tElement.appendChild(qElement);

			// add question text
			Element qtElement = document.createElement(XML.QUESTION_TEXT
					.value());
			qtElement.setTextContent(question.getQuestionText());
			qElement.appendChild(qtElement);

			// add answers
			for (Answer answer : question.getAnswers()) {
				Element aElement = document.createElement(XML.ANSWER.value());
				aElement.setTextContent(answer.getContent());

				// set attribute
				if (answer.isCorrect()) {
					aElement.setAttribute(XML.CORRECT.value(), "true");
				}
				qElement.appendChild(aElement);
			}
		}
		return document;
	}

	/**
	 * Saves Test object in XML file (through DOM).
	 * 
	 * @param test
	 *            Test object to be saved.
	 * @param xmlFileName
	 *            Output xml file name.
	 */
	public static void saveToXML(Test test, String xmlFileName, String tnsPrefix)
			throws TransformerException, ParserConfigurationException {
		Transformer.saveToXML(getDocument(test, tnsPrefix), xmlFileName); 																		// XML
	}

	/**
	 * Test method.
	 */
	public static void main(String[] args) throws Exception {

		// try to parse NOT valid XML document with validation on (failed)
		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			domContr.parse(true); // <-- parse with validation (failed)
		} catch (SAXException ex) {
			System.err.println("====================================");
			System.err.println("XML not valid");
			System.err.println("Test object --> " + domContr.getTest());
			System.err.println("====================================");
		}

		// try to parse NOT valid XML document with validation off (success)
		domContr.parse(false); // <-- parse with validation off (success)

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + domContr.getTest());
		System.out.println("====================================");

		// save test to XML file
		DOMController.saveToXML(domContr.getTest(), Constants.INVALID_XML_FILE + ".dom-result.xml", "dom-prefix");

		// save test to XML file, but qualify root name with other prefix
		Test test = domContr.getTest();
		DOMController.saveToXML(test,
				Constants.INVALID_XML_FILE + "-2.dom.xml", "abcd");
	}
}