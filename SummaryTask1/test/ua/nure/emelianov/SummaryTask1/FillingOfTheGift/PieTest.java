package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PieTest {

	public static Pie pie;

	@BeforeClass
	public static void constructorTest() {
		pie = new Pie("Pie1", 25, 30);
	}

	@Test
	public void testPrintName() {
		Assert.assertEquals("name: Pie1", pie.printName());
	}

	@Test
	public void testSugarValue() {
		Assert.assertEquals("sugarValue: 25%", pie.printSugarValue());
	}

	@Test
	public void testPrintWeight() {
		Assert.assertEquals("Weight: 30 gm", pie.printWeight());
	}

	@Test
	public void testPrintCount() {
		Assert.assertEquals("Count: 0 pcs", pie.printCount());
	}

	@Test
	public void testPrintAllValues() {
		pie.printAllValues();
	}
}
