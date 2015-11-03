package ua.nure.emelianov.SummaryTask3.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.SummatyTask3.entity.Port;

public class PortTest {

	Port port;

	@Before
	public void constructorTest() {
		port = new Port();

	}

	@Test
	public void comTest() {
		port.setCom(true);
		Assert.assertEquals(true, port.isCom());

	}

	@Test
	public void usbTest() {
		port.setUsb(true);
		Assert.assertEquals(true, port.isUsb());

	}

	@Test
	public void lptTest() {
		port.setLpt(true);
		Assert.assertEquals(true, port.isLpt());

	}

	@Test
	public void toStringTest() {
		port.toString();
	}
}
