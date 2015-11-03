package ua.nure.your_last_name.SummaryTask3;

import ua.nure.your_last_name.SummaryTask3.controller.DOMController;
import ua.nure.your_last_name.SummaryTask3.controller.SAXController;
import ua.nure.your_last_name.SummaryTask3.controller.STAXController;
import ua.nure.your_last_name.SummaryTask3.entity.Test;
import ua.nure.your_last_name.SummaryTask3.util.Sorter;

/**
 * Entry point for st3 example (simple version).
 * @author D.Kolesnikov
 *
 */
public class Main {
	public static void usage() {
		System.out.println("Usage:\njava -jar ST3ExampleSimple.jar xmlFileName");
		System.out.println("java ua.kharkov.knure.your_last_name.st3example.Main xmlFileName");
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			usage();
			return;
		}
		
		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);
		
		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////
		
		// get
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		Test test = domController.getTest();

		// sort (case 1)
		Sorter.sortQuestionsByQuestionText(test);
		
		// save
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////
		
		// get
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		test = saxController.getTest();
		
		// sort  (case 2)
		Sorter.sortQuestionsByAnswersNumber(test);
		
		// save
		outputXmlFile = "output.sax.xml";
		
		// other way: 
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
		
		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////
		
		// get
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		test = staxController.getTest();
		
		// sort  (case 3)
		Sorter.sortAnswersByContent(test);
		
		// save
		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
	}

}