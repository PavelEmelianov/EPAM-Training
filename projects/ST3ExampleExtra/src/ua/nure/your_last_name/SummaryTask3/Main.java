package ua.nure.your_last_name.SummaryTask3;

import ua.nure.your_last_name.SummaryTask3.controller.DOMController;
import ua.nure.your_last_name.SummaryTask3.controller.JAXBController;
import ua.nure.your_last_name.SummaryTask3.controller.SAXController;
import ua.nure.your_last_name.SummaryTask3.controller.STAXController;
import ua.nure.your_last_name.SummaryTask3.controller.jdom.JDOMController;
import ua.nure.your_last_name.SummaryTask3.entity.Test;
import ua.nure.your_last_name.SummaryTask3.util.Sorter;
import ua.nure.your_last_name.SummaryTask3.util.Transformer;

/**
 * The entry point for st3 example.<br/>
 * To compile and run project you need JDOM library (2+ version) on classpath.<br/>
 * If you run project under JVM6 and want to control a name of the root element
 * prefix when working with JAXB you need JAXB-RI library on classpath (not necessary for JDK7).<br/>
 * DOM/SAX/StAX controllers use the internal implementations of DOM/SAX/StAX
 * technologies if cannot find appropriate external implementation on classpath.
 * As the examples of external implementations you might use the following:
 * <ul>
 * 	<li>Xerces library implements DOM/SAX</li>
 * 	<li>Woodstox library implements StAX</li>
 * </ul>
 * To place library on classpath - create user library, attach all necessary jar
 * files and bind it to the project in Eclipse.
 * Preferred names for user libraries (for portability): JDOM, JAXB-RI, Xerces, Woodstox.<br/>
 * 
 * @author D.Kolesnikov
 * 
 */
public class Main {
	
	public static void usage() {
		System.out.println("Usage:\n" +
				"java -cp jdom2-library.jar;ST3Example.jar" +
				" ua.nure.yout_last_name.SummaryTask3.Main" +
				" input.xml input.xsd [xslFileName]");
		System.out.println("java ua.nure.your_last_name.SummaryTask3.Main" +
				" xmlFileName xsdFileName [xslFileName]");
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2 || args.length > 3) {
			usage();
			return;
		}

		String xmlFileName = args[0];
		String xsdFileName = args[1]; // <-- this parameter for JAXBController
		String xslFileName = null;
		if (args.length == 3) {
			xslFileName = args[2];
		}

		// //////////////////////////////////////////////////////
		// DOM
		// //////////////////////////////////////////////////////

		// obtain container
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		Test container = domController.getTest();

		// sort container
		Sorter.sortQuestionsByQuestionText(container);

		// save container to XML
		String outputXmlFileName = "output.dom.xml";

		// save with DOM
		DOMController.saveToXML(container, outputXmlFileName, "dom-prefix");

		// save with JAXB, prefix for root element is configured in
		// ua.nure.SummaryTask3.entity.package-info.java
		JAXBController.saveToXML(container, outputXmlFileName + ".saved-with-jaxb.xml", xsdFileName);

		// saves with JDOM
		JDOMController.saveToXML(container, outputXmlFileName + ".saved-with-jdom.xml", "jdom-prefix");

		// transform XML to HTML
		if (xslFileName != null) {
			Transformer.saveToHTML(outputXmlFileName, xslFileName, outputXmlFileName + ".html");
		}

		// //////////////////////////////////////////////////////
		// SAX
		// //////////////////////////////////////////////////////

		// obtain container
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		container = saxController.getTest();

		// sort container
		Sorter.sortQuestionsByAnswersNumber(container);

		// save container to XML
		outputXmlFileName = "output.sax.xml";
		DOMController.saveToXML(container, outputXmlFileName, "sax-prefix");
		
		// transform XML to HTML
		if (xslFileName != null) {
			Transformer.saveToHTML(outputXmlFileName, xslFileName, outputXmlFileName + ".html");
		}

		// //////////////////////////////////////////////////////
		// StAX high level (event reader)
		// //////////////////////////////////////////////////////

		// obtain container
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		container = staxController.getTest();

		// sort container
		Sorter.sortAnswersByContent(container);

		// save container to XML
		outputXmlFileName = "output.stax-high.xml";
		DOMController.saveToXML(container, outputXmlFileName, "stax-high-prefix");

		// transform XML to HTML
		if (xslFileName != null) {
			Transformer.saveToHTML(outputXmlFileName, xslFileName, outputXmlFileName + ".html");
		}

		// //////////////////////////////////////////////////////
		// StAX low level (stream reader)
		// //////////////////////////////////////////////////////

		// obtain container
		staxController = new STAXController(xmlFileName);
		staxController.parse2();
		container = staxController.getTest();

		// sort container
		Sorter.sortAnswersByContent(container);

		// save container to XML
		outputXmlFileName = "output.stax-low.xml";
		DOMController.saveToXML(container, outputXmlFileName, "stax-low-prefix");

		// //////////////////////////////////////////////////////
		// JDOM
		// //////////////////////////////////////////////////////

		// obtain container
		JDOMController jdomController = new JDOMController(xmlFileName);
		jdomController.parse(true);
		container = jdomController.getTest();

		// sort container
		Sorter.sortAnswersByCorrect(container);

		// save container to XML
		outputXmlFileName = "output.jdom.xml";
		DOMController.saveToXML(container, outputXmlFileName, "jdom-prefix");																				

		// transform XML to HTML
		if (xslFileName != null) {
			Transformer.saveToHTML(outputXmlFileName, xslFileName, outputXmlFileName + ".html");
		}		
	}
	
}