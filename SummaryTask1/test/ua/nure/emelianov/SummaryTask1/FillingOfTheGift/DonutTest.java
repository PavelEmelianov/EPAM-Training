package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DonutTest {

	public static Donut donut;

	@BeforeClass
	public static void constructorTest() {
		donut = new Donut("Donut1", 20, 30);
	}

	@Test
	public void testPrintName() {
		Assert.assertEquals("name: Donut1", donut.printName());
	}

	@Test
	public void testSugarValue() {
		Assert.assertEquals("sugarValue: 20%", donut.printSugarValue());
	}

	@Test
	public void testPrintWeight() {
		Assert.assertEquals("Weight: 30 gm", donut.printWeight());
	}

	@Test
	public void testPrintCount() {
		Assert.assertEquals("Count: 0 pcs", donut.printCount());
	}

	@Test
	public void testPrintAllValues() {
		donut.printAllValues();
	}

}
