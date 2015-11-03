package ua.nure.emelianov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ua.nure.emelianov.SummatyTask3.entity.Device;

public class DOMControllerTest {
	
	public class DeviceMock extends Device{
		
	}
	Device deviceMock = new DeviceMock();
	
	static DOMController dc;
	
	@Before
	public  void constructortest() {
		dc = new DOMController("input.xml");
	}
	
	@Test
	public void getDeviceTest(){
		Assert.assertNull(dc.getDevice());
	}
	
	@Test
	public void parseTest() throws ParserConfigurationException, SAXException, IOException{
		dc.parse(false);
		dc.parse(true);
		Assert.assertNotNull(dc.getDevice());		
	}
	
	@Test (expected = SAXParseException.class)
	public void parseExceptionTest() throws ParserConfigurationException, SAXException, IOException{
		dc = new DOMController("input-invalid.xml");
		dc.parse(true);	
	}
	
	@Test (expected = IOException.class)
	public void parseExceptionTest2() throws ParserConfigurationException, SAXException, IOException{
		dc = new DOMController("invalid.xml");
		dc.parse(true);	
	}
	
	@Test
	public void getDocumentTest() throws ParserConfigurationException, SAXException, IOException {
		dc.parse(true);	
		DOMController.getDocument(dc.getDevice());
	}
	
	@Test
	public void saveToXMLTest() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		dc.parse(true);	
		DOMController.saveToXML(dc.getDevice(), "DOMTest.xml");
	}
	
	@Test
	public void saveToXMLTest2() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		dc.parse(true);	
		DOMController.saveToXML(DOMController.getDocument(dc.getDevice()), "DOMTest2.xml");
	}
	
}
