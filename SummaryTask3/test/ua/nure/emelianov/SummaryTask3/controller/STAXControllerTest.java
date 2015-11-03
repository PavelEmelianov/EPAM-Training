package ua.nure.emelianov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class STAXControllerTest {
	
	private static STAXController sc;
	
	@Before
	public void constructorTest(){
		sc = new STAXController("input.xml");
	}
	
	public void getDeviceTest(){
		Assert.assertNull(sc.getDevice());
	}
	@Test
	public void parseTest() throws ParserConfigurationException, SAXException, IOException, XMLStreamException{
		sc.parse();
		Assert.assertNotNull(sc.getDevice());
	}
	
	@Test (expected = XMLStreamException.class)
	public void parseExcTest() throws ParserConfigurationException, SAXException, IOException, XMLStreamException{
		sc = new STAXController("invalid.xml");
		sc.parse();
	}
}
