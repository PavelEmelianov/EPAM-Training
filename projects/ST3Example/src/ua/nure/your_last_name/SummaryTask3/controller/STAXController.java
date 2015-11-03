package ua.nure.your_last_name.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.constants.XML;
import ua.nure.your_last_name.SummaryTask3.entity.Answer;
import ua.nure.your_last_name.SummaryTask3.entity.Question;
import ua.nure.your_last_name.SummaryTask3.entity.Test;

/**
 * Controller for StAX parser.
 * 
 * @author D.Kolesnikov
 * 
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	// main container
	private Test test;

	public Test getTest() {
		return test;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document with StAX (based on event reader). There is no validation during the
	 * parsing.
	 */
	public void parse() throws ParserConfigurationException, SAXException,
			IOException, XMLStreamException {

		Question question = null;
		Answer answer = null;
		
		// current element name holder
		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
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

				if (XML.TEST.equalsTo(currentElement)) {
					test = new Test();
					continue;
				}

				if (XML.QUESTION.equalsTo(currentElement)) {
					question = new Question();
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
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

				if (XML.QUESTION_TEXT.equalsTo(currentElement)) {
					question.setQuestionText(characters.getData());
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
					answer.setContent(characters.getData());
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.QUESTION.equalsTo(localName)) {
					test.getQuestions().add(question);
					continue;
				}

				if (XML.ANSWER.equalsTo(localName)) {
					question.getAnswers().add(answer);
				}
			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {

		// try to parse (valid) XML file (success)
		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse(); // <-- do parse (success)

		// obtain container
		Test test = staxContr.getTest();

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");
	}
}