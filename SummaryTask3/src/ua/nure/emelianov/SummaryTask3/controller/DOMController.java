package ua.nure.emelianov.SummaryTask3.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.emelianov.SummaryTask3.constants.Constants;
import ua.nure.emelianov.SummaryTask3.constants.XML;
import ua.nure.emelianov.SummatyTask3.entity.*;

/**
 * Controller for DOM parser.
 * 
 */
public class DOMController {

	private String xmlFileName;

	private Device device;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Device getDevice() {
		return device;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	
	public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		if (validate) {
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}
		});

		Document document = db.parse(xmlFileName);
		Element root = document.getDocumentElement();
		device = new Device();
		NodeList partNodes = root.getElementsByTagName(XML.PART.value());
		for (int j = 0; j < partNodes.getLength(); j++) {
			Part part = getParts(partNodes.item(j));
			device.getParts().add(part);
		}
	}

	/**
	 * Extracts part object from the part XML node.
	 * 
	 * @param partNode
	 *            Part node.
	 * @return Part object.
	 */
	
	private Part getParts(Node partNode) {

		Part part = new Part();
		Element partlement = (Element) partNode;

		String critical = partlement.getAttribute(XML.CRITICAL.value());
		part.setCritical(Boolean.valueOf(critical));


		Node nameNode = partlement.getElementsByTagName(XML.NAME.value()).item(
				0);

		part.setName(nameNode.getTextContent());


		Node originNode = partlement.getElementsByTagName(XML.ORIGIN.value())
				.item(0);

		part.setOrigin(originNode.getTextContent());


		Node priceNode = partlement.getElementsByTagName(XML.PRICE.value())
				.item(0);

		part.setPrice(Integer.valueOf(priceNode.getTextContent()));


		NodeList typeNodeList = partlement.getElementsByTagName(XML.TYPE
				.value());
		for (int j = 0; j < typeNodeList.getLength(); j++) {
			Type type = getTypes(typeNodeList.item(j));

			part.getTypes().add(type);
		}

		return part;
	}

	/**
	 * Extracts type object from the type XML node.
	 * 
	 * @param typeNode
	 *            Type node.
	 * @return Type object.
	 */

	private Type getTypes(Node typeNode) {

		Type type = new Type();
		Element typeElement = (Element) typeNode;

		Node peripheralNode = typeElement.getElementsByTagName(
				XML.PERIPHERAL.value()).item(0);
		type.setPeripheral(Boolean.valueOf(peripheralNode.getTextContent()));

		Node energuConsumptionNode = typeElement.getElementsByTagName(
				XML.ENERGYCONSUMPTION.value()).item(0);

		type.setEnergyConsumption(Integer.valueOf(energuConsumptionNode
				.getTextContent()));


		Node coolerAvailabilityNode = typeElement.getElementsByTagName(
				XML.COOLERAVAILABILITY.value()).item(0);

		type.setCoolerAvailability(Boolean.valueOf(coolerAvailabilityNode
				.getTextContent()));

		Node groupNode = typeElement.getElementsByTagName(XML.GROUP.value())
				.item(0);

		type.setGroup(groupNode.getTextContent());


		NodeList portNodeList = typeElement.getElementsByTagName(XML.PORT
				.value());

		for (int j = 0; j < portNodeList.getLength(); j++) {
			Port port = getPorts(portNodeList.item(j));


			type.getPorts().add(port);
		}

		return type;
	}
	
	/**
	 * Extracts port object from the port XML node.
	 * 
	 * @param portNode
	 *            Port node.
	 * @return Port object.
	 */

	private Port getPorts(Node portNode) {

		Port port = new Port();
		Element portElement = (Element) portNode;


		Node comNode = portElement.getElementsByTagName(XML.COM.value())
				.item(0);

		port.setCom(Boolean.valueOf(comNode.getTextContent()));

		Node usbNode = portElement.getElementsByTagName(XML.USB.value())
				.item(0);

		port.setUsb(Boolean.valueOf(usbNode.getTextContent()));

		Node lptNode = portElement.getElementsByTagName(XML.LPT.value())
				.item(0);

		port.setLpt(Boolean.valueOf(lptNode.getTextContent()));

		return port;
	}

	/**
	 * Creates and returns DOM of the Device container.
	 * 
	 * @param device
	 *            Device object.
	 * @throws ParserConfigurationException
	 */
	
	public static Document getDocument(Device device)
			throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element dElement = document.createElement(XML.DEVICE.value());

		document.appendChild(dElement);

		for (Part part : device.getParts()) {

			Element partElement = document.createElement(XML.PART.value());
			dElement.appendChild(partElement);

			if (part.isCritical()) {
				partElement.setAttribute(XML.CRITICAL.value(), "true");
			}

			Element nameElement = document.createElement(XML.NAME.value());
			nameElement.setTextContent(part.getName());
			partElement.appendChild(nameElement);

			Element originElement = document.createElement(XML.ORIGIN.value());
			originElement.setTextContent(part.getOrigin());
			partElement.appendChild(originElement);

			Element priceElement = document.createElement(XML.PRICE.value());
			priceElement.setTextContent(String.valueOf(part.getPrice()));
			partElement.appendChild(priceElement);

			for (Type type : part.getTypes()) {

				Element typeElement = document.createElement(XML.TYPE.value());
				partElement.appendChild(typeElement);

				Element peripheralElement = document
						.createElement(XML.PERIPHERAL.value());
				peripheralElement.setTextContent(String.valueOf(type
						.isPeripheral()));
				typeElement.appendChild(peripheralElement);

				Element energyConsumptionElement = document
						.createElement(XML.ENERGYCONSUMPTION.value());
				energyConsumptionElement.setTextContent(String.valueOf(type
						.getEnergyConsumption()));
				typeElement.appendChild(energyConsumptionElement);

				Element coolerAvailabilityElement = document
						.createElement(XML.COOLERAVAILABILITY.value());
				coolerAvailabilityElement.setTextContent(String.valueOf(type
						.isCoolerAvailability()));
				typeElement.appendChild(coolerAvailabilityElement);

				Element groupElement = document
						.createElement(XML.GROUP.value());
				groupElement.setTextContent(type.getGroup());
				typeElement.appendChild(groupElement);

				for (Port port : type.getPorts()) {

					Element portElement = document.createElement(XML.PORT
							.value());
					typeElement.appendChild(portElement);

					Element comElement = document
							.createElement(XML.COM.value());
					comElement.setTextContent(String.valueOf(port.isCom()));
					portElement.appendChild(comElement);

					Element usbElement = document
							.createElement(XML.USB.value());
					usbElement.setTextContent(String.valueOf(port.isUsb()));
					portElement.appendChild(usbElement);

					Element lptElement = document
							.createElement(XML.LPT.value());
					lptElement.setTextContent(String.valueOf(port.isLpt()));
					portElement.appendChild(lptElement);

				}

			}
		}

		return document;
	}

	/**
	 * Saves Device object to XML file.
	 * 
	 * @param device
	 *            Device object to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	
	public static void saveToXML(Device device, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		saveToXML(getDocument(device), xmlFileName);
	}

	/**
	 * Save DOM to XML.
	 * 
	 * @param document
	 *            DOM to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Document document, String xmlFileName)
			throws TransformerException {

		StreamResult result = new StreamResult(new File(xmlFileName));

		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");

		t.transform(new DOMSource(document), result);
	}

}
