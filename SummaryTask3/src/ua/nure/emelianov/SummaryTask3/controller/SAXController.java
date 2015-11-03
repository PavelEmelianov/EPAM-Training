package ua.nure.emelianov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.emelianov.SummaryTask3.constants.Constants;
import ua.nure.emelianov.SummaryTask3.constants.XML;
import ua.nure.emelianov.SummatyTask3.entity.*;

/**
 * Controller for SAX parser.
 * 
 */
public class SAXController extends DefaultHandler {

	private String xmlFileName;

	private String currentElement;

	private Device device;

	private Part part;

	private Type type;

	private Port port;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema. With
	 *            this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate) throws ParserConfigurationException,
			SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setNamespaceAware(true);

		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON,
					true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	public Device getDevice() {
		return device;
	}

	/***
	 * Content handler implementation
	 * 
	 */

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.DEVICE.equalsTo(currentElement)) {
			device = new Device();
			return;
		}

		if (XML.PART.equalsTo(currentElement)) {
			part = new Part();
			if (attributes.getLength() > 0) {
				part.setCritical(Boolean.parseBoolean(attributes.getValue(uri,
						XML.CRITICAL.value())));
			}
			return;
		}

		if (XML.TYPE.equalsTo(currentElement)) {
			type = new Type();
		}

		if (XML.PORT.equalsTo(currentElement)) {
			port = new Port();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) {
			return;
		}

		if (XML.NAME.equalsTo(currentElement)) {
			part.setName(elementText);
			return;
		}

		if (XML.ORIGIN.equalsTo(currentElement)) {
			part.setOrigin(elementText);
			return;
		}
		if (XML.PRICE.equalsTo(currentElement)) {
			part.setPrice(Integer.valueOf(elementText));
		}

		if (XML.PERIPHERAL.equalsTo(currentElement)) {
			type.setPeripheral(Boolean.valueOf(elementText));
		}

		if (XML.ENERGYCONSUMPTION.equalsTo(currentElement)) {
			type.setEnergyConsumption(Integer.valueOf(elementText));
		}
		if (XML.COOLERAVAILABILITY.equalsTo(currentElement)) {
			type.setCoolerAvailability(Boolean.valueOf(elementText));
		}
		if (XML.GROUP.equalsTo(currentElement)) {
			type.setGroup(elementText);
		}

		if (XML.COM.equalsTo(currentElement)) {
			port.setCom(Boolean.valueOf(elementText));
		}
		if (XML.USB.equalsTo(currentElement)) {
			port.setUsb(Boolean.valueOf(elementText));
		}
		if (XML.LPT.equalsTo(currentElement)) {
			port.setLpt(Boolean.valueOf(elementText));
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.PART.equalsTo(localName)) {
			device.getParts().add(part);
			return;
		}

		if (XML.TYPE.equalsTo(localName)) {
			part.getTypes().add(type);
			return;
		}
		if (XML.PORT.equalsTo(localName)) {
			type.getPorts().add(port);
			return;
		}
	}

}