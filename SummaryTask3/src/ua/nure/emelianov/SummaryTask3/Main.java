package ua.nure.emelianov.SummaryTask3;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ua.nure.emelianov.SummaryTask3.controller.DOMController;
import ua.nure.emelianov.SummaryTask3.controller.SAXController;
import ua.nure.emelianov.SummaryTask3.controller.STAXController;
import ua.nure.emelianov.SummaryTask3.util.Sorter;
import ua.nure.emelianov.SummatyTask3.entity.Device;

/**
 * Entry point for SummaryTask3
 */

public class Main {

	public static void usage() {
		System.out
				.println("Usage:\njava -jar ST3ExampleSimple.jar xmlFileName");
		System.out
				.println("java ua.kharkov.knure.your_last_name.st3example.Main xmlFileName");
	}

	public static void output(String name) throws ParserConfigurationException,
			SAXException, IOException, TransformerException, XMLStreamException {

		String xmlFileName = name;
		System.out.println("Input ==> " + xmlFileName);

		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);

		Device device = domController.getDevice();

		System.out.println(System.lineSeparator() + "Parsed XML file: "
				+ System.lineSeparator() + device);

		Sorter.sortPartsByOrigin(device);

		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(device, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		SAXController saxController = new SAXController(xmlFileName);

		saxController.parse(true);

		device = saxController.getDevice();

		Sorter.sortPartsByPrice(device);

		outputXmlFile = "output.sax.xml";

		DOMController.saveToXML(device, outputXmlFile);

		System.out.println("Output ==> " + outputXmlFile);

		STAXController staxController = new STAXController(xmlFileName);

		staxController.parse();

		device = staxController.getDevice();

		Sorter.sortPartsByCritical(device);

		outputXmlFile = "output.stax.xml";

		DOMController.saveToXML(device, outputXmlFile);

		System.out.println("Output ==> " + outputXmlFile);
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		try {
			output(args[0]);
		} catch (ParserConfigurationException | SAXException | IOException
				| TransformerException | XMLStreamException e) {
			System.err.println("Incorrent XML file: " + args[0] + " "
					+ e.getMessage());
		}

	}
}