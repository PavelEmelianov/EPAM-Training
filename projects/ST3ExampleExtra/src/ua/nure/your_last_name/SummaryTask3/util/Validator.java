package ua.nure.your_last_name.SummaryTask3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ua.nure.your_last_name.SummaryTask3.constants.Constants;

/**
 * Validator util class. Holds static validate method for XML document validation.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Validator {
		
	/**
	 * Validates XML document against XML schema.
	 * 
	 * @param xmlFileName
	 *            Input XML file name.
	 * @param xsdFileName
	 *            Input XSD schema name. If null, XSD schema indicated in XML file
	 *            will be used for validation.
	 * @throws SAXException
	 *             If XML document is invalid.
	 */
	public static boolean validate(String xmlFileName, String xsdFileName)
			throws SAXException, IOException {		
		
		SchemaFactory factory = 
				SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Schema schema = null;
		if (xsdFileName != null) {			
			// external XSD will be used for validation
			schema = factory.newSchema(new File(xsdFileName));
		} else {
			// XSD indicated in XML will be used for validation
			schema = factory.newSchema();
		}

		// to avoid name collision here we use full qualified name
		javax.xml.validation.Validator validator = schema.newValidator();

		Source source = new SAXSource(
				new InputSource(new InputStreamReader(new FileInputStream(xmlFileName), "Cp1251")));

		validator.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception)
					throws SAXException {
				// DO NOTHING
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				// if XML document is invalid this method will be invoked

				// just throw exception and this exception will be thrown by
				// validate method
				throw exception;
			}

			@Override
			public void fatalError(SAXParseException exception)
					throws SAXException {
				// this method will be invoked if XML document is not
				// WELL-FORMED XML document (for a example)
				// it is not necessary to throw exception, because if fatalError
				// method was invoked, validate method throws exception
				// automatically

				// DO NOTHING
			}
		});

		try {
			validator.validate(source);
			return true;
		} catch (SAXException ex) {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		boolean result;

		// try to validate valid XML file with external XSD (success)
		result = Validator.validate(Constants.VALID_XML_FILE, Constants.XSD_FILE);
		System.out.println("Document " + Constants.VALID_XML_FILE + " is " +
				(result ? "" : "NOT ") + "valid against " + Constants.XSD_FILE);

		// try to validate valid XML file with XSD indicated in XML (success)
		result = Validator.validate("Test.xml", null);
		System.out.println("Document " + Constants.VALID_XML_FILE + " is " +
				(result ? "" : "NOT ") + "valid against its schema");

		// try to validate NOT valid XML file with external XSD (failed)
		result = Validator.validate(Constants.INVALID_XML_FILE , Constants.XSD_FILE);
		System.err.println("Document " + Constants.INVALID_XML_FILE + " is " +
				(result ? "" : "NOT ") + "valid against " + Constants.XSD_FILE);

		// try to validate NOT valid XML file with XSD indicated in XML (failed)
		result = Validator.validate(Constants.INVALID_XML_FILE , null);
		System.err.println("Document " + Constants.INVALID_XML_FILE + " is " +
				(result ? "" : "NOT ") + "valid against its schema");
	}
}