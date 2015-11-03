package ua.nure.emelianov.SummaryTask3;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class MainTest {
		
	@Test
	public void defaultConstructorTest(){
		Main main = new Main();
	}
	
	@Test
	public void usageTest(){
		Main.main(new String[] {});
			
	}
	@Test
	public void outputTest() throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException{
		Main.output("input.xml");
	}
	@Test(expected = IOException.class)
	public void outputExceptionTest() throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException{
		Main.output("Invalid.xml");
	}
	@Test(expected = SAXException.class)
	public void outputExceptionTest2() throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException{
		Main.output("input-invalid.xml");
	}
	@Test 
	public void mainTest(){
		Main.main(new String[] {"input.xml"});
		Main.main(new String[] {"input-invalid.xml"});
	}
}
