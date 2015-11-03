//JTJ-436
package ua.nure.emelianov.SummaryTask1;

import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.*;
import ua.nure.emelianov.SummaryTask1.GiftContainer.NewYearsGift;

/**
 * Demonstration class
 */

public class Demo {

	private static final int START = 10;
	private static final int END = 21;

	// Creating objects
	private static final Candy CANDY1 = new Candy("Candy1", 10, 10);
	private static final Candy CANDY2 = new Candy("Candy2", 15, 15);
	private static final Candy CANDY3 = new Candy("Candy3", 25, 20);
	private static final Pie PIE1 = new Pie("Pie1", 10, 50);
	private static final Pie PIE2 = new Pie("Pie2", 20, 50);
	private static final Donut DONUT1 = new Donut("Donut1", 20, 15);
	private static final Donut DONUT2 = new Donut("Donut2", 30, 20);

	// Creating container
	private static final NewYearsGift GIFT = new NewYearsGift();

	public static NewYearsGift getGift() {
		return GIFT;
	}

	/**
	 * This method adds the objects to the container
	 */

	public static void createGift() {
		getGift().add(CANDY1, 7);
		getGift().add(CANDY2, 8);
		getGift().add(CANDY3, 4);
		getGift().add(PIE1, 1);
		getGift().add(PIE2, 1);
		getGift().add(DONUT1, 3);
		getGift().add(DONUT2, 4);
		getGift().add(DONUT2, 5);
		getGift().add(CANDY1, 14);
		getGift().add(CANDY2, 17);
		getGift().add(CANDY3, 13);
		getGift().add(PIE1, 1);
		getGift().add(PIE2, 1);
		getGift().add(DONUT1, 2);
		getGift().add(DONUT2, 1);
	}

	public static void main(String[] args) {
		createGift();
		System.out.println("Getting all objects from the gift:");
		getGift().getAllCandy();
		System.out.println();
		System.out.println("Sorting objects by sugarValue:");
		getGift().sortBySugarValue(getGift().getList());
		getGift().getAllCandy();
		System.out.println();
		System.out.println("Calculating total weight of the gift: ");
		System.out.println(getGift().calculateTotalWeight() + " gm");
		System.out.println();
		System.out
				.println("Getting all objects with selected range of sugar value(from "
						+ START + "% to " + END + "%) :");
		for (AbstractSweets sweets : getGift().receiveBySugarValue(START, END)) {
			System.out.print(sweets.printAllValues());
		}
	}

}
