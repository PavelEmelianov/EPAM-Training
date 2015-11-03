package ua.nure.emelianov.SummaryTask3.controller;

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

import ua.nure.emelianov.SummaryTask3.constants.XML;
import ua.nure.emelianov.SummatyTask3.entity.*;

/**
 * Controller for StAX parser.
 * 
 */
public class STAXController extends DefaultHandler {

	private String xmlFileName;

	private Device device;

	public Device getDevice() {
		return device;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document with StAX (based on event reader). There is no
	 * validation during the parsing.
	 */

	public void parse() throws ParserConfigurationException, SAXException,
			IOException, XMLStreamException {

		Part part = null;

		Type type = null;

		Port port = null;

		String currentElement = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();

		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(new StreamSource(
				xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.DEVICE.equalsTo(currentElement)) {
					device = new Device();
					continue;
				}

				if (XML.PART.equalsTo(currentElement)) {
					part = new Part();
					Attribute attribute = startElement
							.getAttributeByName(new QName(XML.CRITICAL.value()));
					if (attribute != null) {
						part.setCritical(Boolean.parseBoolean(attribute
								.getValue()));
					}
					continue;
				}

				if (XML.TYPE.equalsTo(currentElement)) {
					type = new Type();
					continue;
				}

				if (XML.PORT.equalsTo(currentElement)) {
					port = new Port();
					continue;
				}
			}

			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.NAME.equalsTo(currentElement)) {
					part.setName(characters.getData());
					continue;
				}

				if (XML.ORIGIN.equalsTo(currentElement)) {
					part.setOrigin(characters.getData());
					continue;
				}
				if (XML.PRICE.equalsTo(currentElement)) {
					part.setPrice(Integer.valueOf(characters.getData()));
					continue;
				}

				if (XML.PERIPHERAL.equalsTo(currentElement)) {
					type.setPeripheral(Boolean.valueOf(characters.getData()));
					continue;
				}

				if (XML.ENERGYCONSUMPTION.equalsTo(currentElement)) {
					type.setEnergyConsumption(Integer.valueOf(characters
							.getData()));
					continue;
				}
				if (XML.COOLERAVAILABILITY.equalsTo(currentElement)) {
					type.setCoolerAvailability(Boolean.valueOf(characters
							.getData()));
					continue;
				}
				if (XML.GROUP.equalsTo(currentElement)) {
					type.setGroup(characters.getData());
					continue;
				}

				if (XML.COM.equalsTo(currentElement)) {
					port.setCom(Boolean.valueOf(characters.getData()));
					continue;
				}
				if (XML.USB.equalsTo(currentElement)) {
					port.setUsb(Boolean.valueOf(characters.getData()));
					continue;
				}
				if (XML.LPT.equalsTo(currentElement)) {
					port.setLpt(Boolean.valueOf(characters.getData()));
					continue;
				}
			}

			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.PART.equalsTo(localName)) {
					device.getParts().add(part);
					continue;
				}

				if (XML.TYPE.equalsTo(localName)) {
					part.getTypes().add(type);
					continue;
				}
				if (XML.PORT.equalsTo(localName)) {
					type.getPorts().add(port);
					continue;
				}
			}
		}
		reader.close();
	}
}