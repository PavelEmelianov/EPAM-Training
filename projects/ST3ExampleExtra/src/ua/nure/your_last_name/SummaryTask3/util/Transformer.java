package ua.nure.your_last_name.SummaryTask3.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBResult;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;
import ua.nure.your_last_name.SummaryTask3.entity.Test;

/**
 * Util class for XML document transformation.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Transformer {

	/**
	 * Transforms XML document to JAXB objects.
	 * 
	 * @param xmlFileName
	 *            Input XML document.
	 * @return JAXB object.
	 */
	public static Test jaxbTransform(String xmlFileName) throws JAXBException, TransformerException {
		JAXBResult result = new JAXBResult(
				JAXBContext.newInstance(Constants.JAXB_PACKAGE));

		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();

		// run transformation
		t.transform(new StreamSource(xmlFileName), result);

		// obtain the unmarshalled content tree
		return (Test) result.getResult();
	}

	/**
	 * Transforms XML document to HTML document.
	 * 
	 * @param xmlFileName
	 *            Input XML document.
	 * @param xslFileName
	 *            Input XSL document.
	 * @param htmlFileName
	 *            Output HTML file.
	 * 
	 */
	public static void saveToHTML(String xmlFileName, String xslFileName, String htmlFileName) 
			throws JAXBException, TransformerException {
		
		StreamResult result = new StreamResult(new File(htmlFileName));

		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer(
				new StreamSource(new File(xslFileName)));
		t.setOutputProperty(OutputKeys.INDENT, "yes");			
		
		// run transformation
		t.transform(new StreamSource(xmlFileName), result);
	}

	/**
	 * Save DOM model to XML file.
	 * 
	 * @param document
	 *            DOM document to be saved.
	 * @param xmlFileName
	 *            Output XML file.
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
		Test test = Transformer.jaxbTransform(Constants.VALID_XML_FILE);
		System.out.println(test);		
		Transformer.saveToHTML(Constants.VALID_XML_FILE, "Test.xsl", "Test.html");
	}
}