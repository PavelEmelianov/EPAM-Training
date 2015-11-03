package ua.nure.emelianov.SummaryTask1.GiftContainer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.Candy;
import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.Donut;
import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.Pie;

public class NewYearsGiftTest {

	public static class CandyMock extends Candy {

		public CandyMock(String name, int sugarValue, int weight) {
			super(name, sugarValue, weight);
		}
	}

	public static class DonutMock extends Donut {

		public DonutMock(String name, int sugarValue, int weight) {
			super(name, sugarValue, weight);
		}
	}

	public static class PieMock extends Pie {

		public PieMock(String name, int sugarValue, int weight) {
			super(name, sugarValue, weight);
		}
	}

	static NewYearsGift gift;

	static CandyMock candyMock;
	static DonutMock donutMock;
	static PieMock pieMock;

	@BeforeClass
	public static void testAdd() {

		gift = new NewYearsGift();
		candyMock = new CandyMock("Candy", 15, 20);
		donutMock = new DonutMock("Donut", 10, 25);
		pieMock = new PieMock("Pie", 25, 30);

		gift.add(candyMock, 1);
		gift.add(donutMock, 1);
		gift.add(pieMock, 1);
		gift.add(candyMock, 1);
		Assert.assertEquals(3, gift.getList().size());
	}

	@Test
	public void testCalculateTotalWeight() {
		Assert.assertEquals(95, gift.calculateTotalWeight());
	}

	@Test
	public void testGetAllCandy() {
		gift.getAllCandy();
	}

	@Test
	public void testReceiveBySugarValue() {
		Assert.assertEquals(1, gift.receiveBySugarValue(15, 20).size());
	}

	@Test
	public void testSortBySugarValue() {
		gift.sortBySugarValue(gift.getList());
		Assert.assertEquals(10, gift.getList().get(0).getSugarValue());
	}
}
