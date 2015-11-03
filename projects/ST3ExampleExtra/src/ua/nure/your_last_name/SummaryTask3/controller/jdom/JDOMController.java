package ua.nure.your_last_name.SummaryTask3.controller.jdom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.constants.XML;
import ua.nure.your_last_name.SummaryTask3.entity.Answer;
import ua.nure.your_last_name.SummaryTask3.entity.Question;
import ua.nure.your_last_name.SummaryTask3.entity.Test;

/**
 * Controller for JDOM parser.<br/>
 * WARNING!!! To use functionality from this class place jdom and xerces
 * libraries to classpath.
 * 
 * @author D.Kolesnikov
 * 
 */
public class JDOMController {

	private String xmlFileName;

	private Test test; // <-- container

	public JDOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	/**
	 * Parses XML document with JDOM.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 * @throws JDOMException
	 */
	public void parse(boolean validate) throws ParserConfigurationException,
			SAXException, IOException, JDOMException {
		
		// get builder (and set validation if it is necessary)
		SAXBuilder builder;
		if (validate) {
			builder = new SAXBuilder(XMLReaders.XSDVALIDATING);
		} else {
			builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		}
		
		// parse
		Document document = builder.build(xmlFileName);

		// process with JDOM
		Element root = document.getRootElement();

		// create container
		test = new Test();

		// loop over Questions
		Iterator<?> qIterator = root.getChildren().iterator();
		while (qIterator.hasNext()) {
			Element qElement = (Element) qIterator.next();

			Question question = new Question();
			question.setQuestionText(qElement.getChildText(XML.QUESTION_TEXT
					.value()));

			// loop over Answers
			Iterator<?> aIterator = qElement.getChildren(XML.ANSWER.value())
					.iterator();
			while (aIterator.hasNext()) {
				Element aElement = (Element) aIterator.next();

				Answer answer = new Answer();
				answer.setContent(aElement.getText());
				answer.setCorrect(Boolean.valueOf(aElement
						.getAttributeValue(XML.CORRECT.value())));

				question.getAnswers().add(answer);
			}
			test.getQuestions().add(question);
		}

		// at this point we have filled Test object
	}

	// //////////////////////////////////////////////////////
	// Static util methods
	// //////////////////////////////////////////////////////

	/**
	 * Saves Test object to XML document.
	 * 
	 * @param test
	 *            Test container to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 * @param tnsPrefix
	 *            Target namespace prefix.
	 * @exception IllegalArgumentException
	 *                if tnsPrefix null or empty.
	 */
	public static void saveToXML(Test test, String xmlFileName, String tnsPrefix)
			throws ParserConfigurationException, IOException {
		
		if (tnsPrefix == null || tnsPrefix.isEmpty()) {
			throw new IllegalArgumentException(
					"Target namespace prefix cannot be null or empty");
		}

		// create target namespace
		Namespace tnsNamespace = Namespace.getNamespace(tnsPrefix,
				XML.TNS_URI.value());

		// create the root element with according namespace
		Element root = new Element(XML.TEST.value(), tnsNamespace);

		for (Question question : test.getQuestions()) {

			// create Question element
			Element qElement = new Element(XML.QUESTION.value());

			// create QuestionText element
			Element qtElement = new Element(XML.QUESTION_TEXT.value());
			qtElement.setText(question.getQuestionText());
			qElement.addContent(qtElement);

			// process all Answer-s for given question
			for (Answer answer : question.getAnswers()) {
				Element aElement = new Element(XML.ANSWER.value());

				aElement.setText(answer.getContent());

				// process attribute
				if (answer.isCorrect()) {
					aElement.setAttribute(XML.CORRECT.value(),
							String.valueOf(answer.isCorrect()));
				}

				qElement.addContent(aElement);
			}

			root.addContent(qElement);
		}

		// obtain schema instance namespace
		Namespace xsi = Namespace.getNamespace(Constants.XSI_SPACE_PREFIX,
				XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);

		// set xsi namespace
		root.setAttribute(Constants.SCHEMA_LOCATION__ATTR_NAME,
				Constants.SCHEMA_LOCATION__URI, xsi);

		// save JDOM model to XML file
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(new Document(root), new FileOutputStream(xmlFileName));
	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerException, JDOMException {

		// try to parse NOT valid XML document (failed)
		JDOMController jdomContr = new JDOMController(Constants.INVALID_XML_FILE);
		try {
			jdomContr.parse(true); // <-- parse with validation (failed)
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("XML not valid\n" + ex.getMessage());
			System.err.println("Test object --> " + jdomContr.getTest());
			System.err.println("====================================");
		}

		// try to parse NOT valid XML document with validation off (success)
		jdomContr.parse(false); // <-- parse with validation off (success)
		Test test = jdomContr.getTest();

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");

		// now try to parse the valid XML document with validation on (success)
		jdomContr = new JDOMController(Constants.VALID_XML_FILE);
		jdomContr.parse(true); // <-- parse with validation on (success)
		test = jdomContr.getTest(); 

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + test);
		System.out.println("====================================");

		// save test in XML file
		JDOMController.saveToXML(test, Constants.VALID_XML_FILE + ".jdom-result.xml", "jdom-prefix");
	}
}