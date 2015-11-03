package ua.nure.your_last_name.SummaryTask3.constants;

/**
 * Holds application constants.
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Constants {
	
	// files
	public static final String VALID_XML_FILE = "input.xml";
	public static final String INVALID_XML_FILE = "input-invalid.xml";
	public static final String XSD_FILE = "input.xsd";
	
	// this package contains jaxb entities. To get entities use the following
	// command: xjc input.xsd -p PACKAGE_FOR_ENTITIES -d YOUR_SRC_FOLDER
	public static final String JAXB_PACKAGE = "ua.nure.your_last_name.SummaryTask3.entity";
	
	// for schema location
	public static final String SCHEMA_LOCATION__URI = "http://nure.ua/your_last_name/SummaryTask3/entity input.xsd";
	public static final String SCHEMA_LOCATION__ATTR_NAME = "schemaLocation";
	public static final String SCHEMA_LOCATION__ATTR_FQN = "xsi:schemaLocation";
	public static final String XSI_SPACE_PREFIX = "xsi";

	// validation features
	public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
	public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";

	// full qualified names of classes
	
	public static final String CLASS_SAX_PARSER_XERCES = "org.apache.xerces.parsers.SAXParser";
	
	public static final String CLASS_DOCUMENT_BUILDER_FACTORY_INTERNAL = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
	public static final String CLASS_DOCUMENT_BUILDER_FACTORY_XERCES = "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl";
	
	public static final String CLASS_SAX_PARSER_FACTORY_INTERNAL = "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl";
	public static final String CLASS_XML_SCHEMA_FACTORY_INTERNAL = "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory";
	
}