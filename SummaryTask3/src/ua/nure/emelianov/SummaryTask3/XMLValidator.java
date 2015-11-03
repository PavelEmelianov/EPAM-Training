package ua.nure.emelianov.SummaryTask3;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/***
 * This class validates XML files against XSD schema
 *
 */

public class XMLValidator {

	/***
	 * This method validates xml file against xsd schema
	 * 
	 * @param xsd
	 *            xsd schema
	 * @param xml
	 *            xml file
	 * @return boolean condition
	 */
	
	public static boolean validateXMLSchema(String xsd, String xml) {

		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xml)));
		} catch (IOException | SAXException e) {
			System.out.println("Invalid XML file " + e.getMessage());
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		System.out
				.println("output.dom.xml validates against inputNoTns.xsd ==> "
						+ validateXMLSchema("inputNoTns.xsd", "output.dom.xml"));
		System.out
				.println("output.sax.xml validates against inputNoTns.xsd ==> "
						+ validateXMLSchema("inputNoTns.xsd", "output.sax.xml"));
		System.out
				.println("output.stax.xml validates against inputNoTns.xsd ==> "
						+ validateXMLSchema("inputNoTns.xsd", "output.stax.xml"));

	}
}