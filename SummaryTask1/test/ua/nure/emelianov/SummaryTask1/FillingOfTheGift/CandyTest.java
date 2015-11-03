package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CandyTest {

	public static Candy candy;

	@BeforeClass
	public static void constructorTest() {
		candy = new Candy("Candy1", 10, 15);
	}

	@Test
	public void testPrintName() {
		Assert.assertEquals("name: Candy1", candy.printName());
	}

	@Test
	public void testSugarValue() {
		Assert.assertEquals("sugarValue: 10%", candy.printSugarValue());
	}

	@Test
	public void testPrintWeight() {
		Assert.assertEquals("Weight: 15 gm", candy.printWeight());
	}

	@Test
	public void testPrintCount() {
		Assert.assertEquals("Count: 0 pcs", candy.printCount());
	}

	@Test
	public void testPrintAllValues() {
		candy.printAllValues();
	}
}
