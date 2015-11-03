package ua.nure.your_last_name.SummaryTask3.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

/**
 * Controller for DOM parser.
 * 
 * @author D.Kolesnikov
 * 
 */
public class DOMController {

	private String xmlFileName;

	// main container
	private Test test;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		// obtain DOM parser 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory
		
		// XML document contains namespaces
		dbf.setNamespaceAware(true);
		
		// make parser validating
		if (validate) {
			// turn validation on
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			
			// turn on xsd validation 
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		// set error handler
		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				// throw exception if XML document is NOT valid
				throw e;
			}
		});

		// parse XML document
		Document document = db.parse(xmlFileName);
		
		// get root element
		Element root = document.getDocumentElement();

		// create container
		test = new Test();

		// obtain questions nodes
		NodeList questionNodes = root
				.getElementsByTagName(XML.QUESTION.value());

		// process questions nodes
		for (int j = 0; j < questionNodes.getLength(); j++) {
			Question question = getQuestion(questionNodes.item(j));
			// add question to container
			test.getQuestions().add(question);
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

			// add answer			
			question.getAnswers().add(answer);
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
		answer.setCorrect(Boolean.valueOf(correct));

		// process content
		String content = aElement.getTextContent();
		answer.setContent(content);

		return answer;
	}
	
	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	/**
	 * Creates and returns DOM of the Test container.
	 * 
	 * @param test
	 *            Test object.
	 * @throws ParserConfigurationException 
	 */
	public static Document getDocument(Test test) throws ParserConfigurationException {
	
		// obtain DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory

		// XML document contains namespaces
		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		// create root element
		Element tElement = document.createElement(XML.TEST.value());

		// add root element
		document.appendChild(tElement);

		// add questions elements
		for (Question question : test.getQuestions()) {

			// add question
			Element qElement = document.createElement(XML.QUESTION.value());
			tElement.appendChild(qElement);

			// add question text
			Element qtElement = 
					document.createElement(XML.QUESTION_TEXT.value());
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
	 * Saves Test object to XML file.
	 * 
	 * @param test
	 *            Test object to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Test test, String xmlFileName)
			throws ParserConfigurationException, TransformerException {		
		// Test -> DOM -> XML
		saveToXML(getDocument(test), xmlFileName);		
	}
	
	/**
	 * Save DOM to XML.
	 * 
	 * @param document
	 *            DOM to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Document document, String xmlFileName) 
			throws TransformerException {
		
		StreamResult result = new StreamResult(new File(xmlFileName));
		
		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");			
		
		// run transformation
		t.transform(new DOMSource(document), result);
	}

	public static void main(String[] args) throws Exception {

		// try to parse NOT valid XML document with validation on (failed)
		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			// parse with validation (failed)
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("====================================");
			System.err.println("XML not valid");
			System.err.println("Test object --> " + domContr.getTest());
			System.err.println("====================================");
		}

		// try to parse NOT valid XML document with validation off (success)
		domContr.parse(false);

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + domContr.getTest());
		System.out.println("====================================");

		// save test in XML file
		Test test = domContr.getTest();
		DOMController.saveToXML(test, Constants.INVALID_XML_FILE + ".dom-result.xml");
	}
}
