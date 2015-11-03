package ua.nure.emelianov.SummaryTask3.constants;

import org.junit.Assert;
import org.junit.Test;


public class XMLTest {
	@Test
	public void constructorTest()  {
		Assert.assertEquals("critical", XML.valueOf("CRITICAL").value());
		XML xml = ua.nure.emelianov.SummaryTask3.constants.XML.COM;
	}
	@Test
	public void equalsToTest()  {
		XML device = XML.DEVICE;
		Assert.assertTrue(device.equalsTo("Device"));
	}
	@Test
	public void valueTest()  {
		XML device = XML.DEVICE;
		Assert.assertEquals("Device", device.value());
	}
}
