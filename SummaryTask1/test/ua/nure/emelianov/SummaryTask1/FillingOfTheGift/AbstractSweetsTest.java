package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbstractSweetsTest {

	public static class Mock extends AbstractSweets {

		public Mock(String name, int sugarValue, int weight) {
			super(sugarValue, weight);
			setName(name);
		}

	}

	static Mock mock;

	@BeforeClass
	public static void constructorTest() {
		mock = new Mock("Mock", 32, 24);
	}

	@Test
	public void testPrintName() {
		Assert.assertEquals("name: Mock", mock.printName());
	}

	@Test
	public void testSugarValue() {
		Assert.assertEquals("sugarValue: 32%", mock.printSugarValue());
	}

	@Test
	public void testPrintWeight() {
		Assert.assertEquals("Weight: 24 gm", mock.printWeight());
	}

	@Test
	public void testPrintCount() {
		Assert.assertEquals("Count: 0 pcs", mock.printCount());
	}

	@Test
	public void testPrintAllValues() {
		mock.printAllValues();
	}

}
