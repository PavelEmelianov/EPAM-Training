package ua.nure.emelianov.SummaryTask3.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.SummatyTask3.entity.Part;

public class PartTest {
	private Part part;

	@Before
	public void constructorTest() {
		part = new Part();
	}

	@Test
	public void nameTest() {
		part.setName("name");
		Assert.assertEquals("name", part.getName());

	}

	@Test
	public void originTest() {
		part.setOrigin("origin");
		Assert.assertEquals("origin", part.getOrigin());
	}

	@Test
	public void priceTest() {
		part.setPrice(500);
		Assert.assertEquals(500, part.getPrice());

	}

	@Test
	public void criticalTest() {
		part.setCritical(true);
		Assert.assertEquals(true, part.isCritical());

	}

	@Test
	public void getTypesTest() {
		part.getTypes();
		Assert.assertNotNull(part.getTypes());
	}

	@Test
	public void toStringTest() {
		part.getTypes();
		part.toString();
	}

}
