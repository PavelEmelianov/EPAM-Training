package ua.nure.emelianov.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class SAXControllerTest {
	static SAXController sc;
	@Before
	public void constructorTest(){
		sc = new SAXController("input.xml");
	}
	@Test
	public void getDeviceTest() throws ParserConfigurationException, SAXException, IOException{
		Assert.assertNull(sc.getDevice());
	}
	@Test
	public void parseTest() throws ParserConfigurationException, SAXException, IOException{
		sc.parse(false);
		sc.parse(true);
		Assert.assertNotNull(sc.getDevice());
	}
	
	@Test (expected = IOException.class)
	public void parseExcTest() throws ParserConfigurationException, SAXException, IOException{
		sc = new SAXController("invalid.xml");
		sc.parse(true);
	}
}
