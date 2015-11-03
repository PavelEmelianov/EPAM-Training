/**
 * Use the following command in command line to get JAXB model of XSD document
 * xjc input.xsd -p THIS_PACKAGE_FQN -d PATH_TO_SRC
 */

@javax.xml.bind.annotation.XmlSchema(
	namespace = "http://nure.ua/your_last_name/SummaryTask3/entity", 
	xmlns = { 
			@javax.xml.bind.annotation.XmlNs(
					namespaceURI = "http://nure.ua/your_last_name/SummaryTask3/entity",
					prefix = "jaxb-prefix" // <-- prefix to qualify root element
			)
	}
)

package ua.nure.your_last_name.SummaryTask3.entity;