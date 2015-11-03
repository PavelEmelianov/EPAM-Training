package ua.nure.your_last_name.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.constants.XML;
import ua.nure.your_last_name.SummaryTask3.entity.Answer;
import ua.nure.your_last_name.SummaryTask3.entity.Question;
import ua.nure.your_last_name.SummaryTask3.entity.Test;

/**
 * Controller for SAX parser.
 * 
 * @author D.Kolesnikov
 * 
 */
public class SAXController extends DefaultHandler {

	private String xmlFileName;

	private String currentElement; // <-- current element name holder

	private Test test; // <-- main container
	private Question question;
	private Answer answer;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}
	
	/**
	 * Parses XML document with SAX parser.<br/>
	 * 
	 * External implementation of SAX parser will be used as an engine if you
	 * place it to classpath (Xerces library for example). Otherwise, the
	 * internal implementation bundled with JDK will be used.<br/>
	 * Also it is possible to control which implementation will be used with the
	 * following code:
	 * 
	 * <pre>
	 * SAXParserFactory factory = SAXParserFactory.newInstance(
	 * 		"FQN of the external SAX Parser Factory implementation class",
	 *		this.getClass().getClassLoader());
	 * </pre>
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema. With
	 *            this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		// get sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		System.out.println("SAXParserFactory ==> " + factory.getClass());

		factory.setNamespaceAware(true);
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON,
					true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	// ///////////////////////////////////////////////////////////
	// ERROR HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		throw e; // <-- if XML document not valid just throw exception
	};

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {

		currentElement = localName;

		if (XML.TEST.is(currentElement)) {
			test = new Test();
			return;
		}

		if (XML.QUESTION.is(currentElement)) {
			question = new Question();
			return;
		}

		if (XML.ANSWER.is(currentElement)) {
			answer = new Answer();
			if (attributes.getLength() > 0) {
				answer.setCorrect(Boolean.parseBoolean(attributes.getValue(uri,
						XML.CORRECT.value())));
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) { // <-- return if content is empty
			return;
		}

		if (XML.QUESTION_TEXT.is(currentElement)) {
			question.setQuestionText(elementText);
			return;
		}

		if (XML.ANSWER.is(currentElement)) {
			answer.setContent(elementText);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.QUESTION.is(localName)) {
			// just add question to container
			test.getQuestions().add(question);
			return;
		}

		if (XML.ANSWER.is(localName)) {
			// just add answer to container
			question.getAnswers().add(answer);
			return;
		}
	}

	public static void main(String[] args) throws Exception {

		// try to parse valid XML file (success)
		SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);
		saxContr.parse(true); // <-- do parse with validation on (success)
		Test test = saxContr.getTest(); // <-- obtain container

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");

		// now try to parse NOT valid XML (failed)
		saxContr = new SAXController(Constants.INVALID_XML_FILE);
		try {
			saxContr.parse(true); // <-- do parse with validation on (failed)
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Validation is failed:\n" + ex.getMessage());
			System.err.println("Try to print test object:" + saxContr.getTest());
			System.err.println("====================================");
		}

		// and now try to parse NOT valid XML with validation off (success)
		saxContr.parse(false); // <-- do parse with validation off (success)

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + saxContr.getTest());
		System.out.println("====================================");
	}
	
}