package ua.nure.your_last_name.SummaryTask3.controller;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

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
public class STAXController extends DefaultHandler {
	
	private String xmlFileName;

	private Test test; // <-- main container
	
	/**
	 * Prefix to qualify root element.
	 */
	private String targetNamespacePrefix; // <-- prefix to qualify root element

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	/**
	 * Parses XML document with StAX.<br/>
	 * This method uses high level StAX API (event iterator).<br/>
	 * 
	 * There is no validation during the parsing.
	 */
	public void parse() throws Exception {		
		String currentElement = null; // <-- current element name holder

		Question question = null;
		Answer answer = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();
		System.out.println("XMLInputFactory ==> " + factory.getClass());
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));
		
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.TEST.is(currentElement)) {					
					// set target namespace prefix
					targetNamespacePrefix = startElement.getName().getPrefix();

					test = new Test();
					continue;
				}
				
				if (XML.QUESTION.is(currentElement)) {
					question = new Question();
					continue;
				}

				if (XML.ANSWER.is(currentElement)) {
					answer = new Answer();
					Attribute attribute = startElement.getAttributeByName(
							new QName(XML.CORRECT.value()));
					if (attribute != null) {
						answer.setCorrect(Boolean.parseBoolean(attribute.getValue()));
					}
				}
			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.QUESTION_TEXT.is(currentElement)) {
					question.setQuestionText(characters.getData());
					continue;
				}

				if (XML.ANSWER.is(currentElement)) {
					answer.setContent(characters.getData());
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.QUESTION.is(localName)) {
					test.getQuestions().add(question);
					continue;
				}

				if (XML.ANSWER.is(localName)) {
					question.getAnswers().add(answer);
				}
			}
		}
		reader.close();
	}

	/**
	 * Parses XML document with StAX.<br/>
	 * This method uses low level StAX API (logical cursor).<br/>
	 * There is no validation during the parsing.
	 */
	public void parse2() throws Exception {		
		Question question = null;
		Answer answer = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		System.out.println("XMLInputFactory ==> " + factory.getClass());
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		XMLStreamReader reader = factory.createXMLStreamReader(
				new StreamSource(xmlFileName));
		
		String currentElement = null; // <-- current element name holder
		
		int event = reader.getEventType();
		
		while (true) {
			switch (event) {

			// handler for start tags
			case XMLStreamConstants.START_ELEMENT:
				currentElement = reader.getName().getLocalPart();
				
				if (XML.TEST.is(currentElement)) {
					// set target namespace prefix
					targetNamespacePrefix = reader.getName().getPrefix();
				
					test = new Test();
					break;
				}
				
				if (XML.QUESTION.is(currentElement)) {
					question = new Question();
					break;
				}

				if (XML.ANSWER.is(currentElement)) {
					answer = new Answer();
					if (reader.getAttributeCount() > 0) {
						String attributeName = reader.getAttributeName(0).getLocalPart();
						if (XML.CORRECT.is(attributeName)) {
							String attributeValue = reader.getAttributeValue(0);
							answer.setCorrect(Boolean.parseBoolean(attributeValue));
						}
					}
					break;
				}
				break;	
				
			// handler for contents
			case XMLStreamConstants.CHARACTERS:
				String content = reader.getText().trim();
				if (content.isEmpty()) {
					break;
				}
				
				if (XML.QUESTION_TEXT.is(currentElement)) {
					question.setQuestionText(content);
					break;
				}

				if (XML.ANSWER.is(currentElement)) {
					answer.setContent(reader.getText());
					break;
				}
				break;				

			// handler for end tags
			case XMLStreamConstants.END_ELEMENT:
				currentElement = reader.getName().getLocalPart();

				if (XML.QUESTION.is(currentElement)) {
					test.getQuestions().add(question);
					break;
				}
			
				if (XML.ANSWER.is(currentElement)) {
					question.getAnswers().add(answer);
				}
				break;
				
			default:
				// no op
			}
			if (!reader.hasNext()) {
				break;
			}
			
			event = reader.next();
			
			
		}
		
		reader.close();
	}
	
	/**
	 * Saves current Test container to XML file.
	 * 
	 * @param xmlFileName
	 *            Output xml file name.
	 */
	public void saveToXML(String xmlFileName) throws Exception {
		DOMController.saveToXML(test, xmlFileName, targetNamespacePrefix);
	}

	public static void main(String[] args) throws Exception {
		
		// try to parse (valid) XML file (success)
		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse(); // <-- do parse (success)

		Test test = staxContr.getTest(); // <-- obtain container

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");
		
		staxContr.parse2();
		test = staxContr.getTest();
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");
	}
}