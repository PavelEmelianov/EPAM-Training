package ua.nure.emelianov.SummaryTask3.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.SummatyTask3.entity.Type;

public class TypeTest {

	Type type;

	@Before
	public void constructorTest() {
		type = new Type();
	}

	@Test
	public void getPortsTest() {
		type.getPorts();
		Assert.assertNotNull(type.getPorts());
	}

	@Test
	public void energyConsumptionTest() {
		type.setEnergyConsumption(500);
		Assert.assertEquals(500, type.getEnergyConsumption());
	}

	@Test
	public void coolerAvailabilityTest() {
		type.setCoolerAvailability(true);
		Assert.assertEquals(true, type.isCoolerAvailability());
	}

	@Test
	public void peripheralTest() {
		type.setPeripheral(true);
		Assert.assertEquals(true, type.isPeripheral());
	}
	
	@Test
	public void groupTest() {
		type.setGroup("group");
		Assert.assertEquals("group", type.getGroup());
	}
	@Test
	public void toStringTest() {
		type.getPorts();
		type.toString();
	}
}
