package ua.nure.emelianov.SummaryTask3.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.SummatyTask3.entity.Device;

public class DeviceTest {
	Device device;
	
	@Before
	public void constructorTest() {
		device = new Device();
	}
	@Test
	public void getPartsTest(){
		device.getParts();
		Assert.assertNotNull(device.getParts());
	}
	@Test
	public void toStringTest(){
		device.getParts();
		System.out.println(device);
	}
}
