package ua.nure.emelianov.SummaryTask3.util;

import java.util.Collections;
import java.util.Comparator;
import ua.nure.emelianov.SummatyTask3.entity.*;

/**
 * This method contains static methods for sorting.
 * 
 */

public class Sorter {

	/***
	 * Sorts parts by origin value
	 */

	public static final Comparator<Part> SORT_PARTS_BY_ORIGIN = new Comparator<Part>() {

		@Override
		public int compare(Part p1, Part p2) {
			return p1.getOrigin().compareTo(p2.getOrigin());
		}

	};
	/***
	 * Sorts parts by price value
	 */

	public static final Comparator<Part> SORT_PARTS_BY_PRICE = new Comparator<Part>() {
		@Override
		public int compare(Part p1, Part p2) {
			return p1.getPrice() - p2.getPrice();
		}
	};

	/***
	 * Sorts parts by origin value
	 */

	public static final Comparator<Part> SORT_PARTS_BY_CRITICAL = new Comparator<Part>() {
		@Override
		public int compare(Part p1, Part p2) {
			if (p1.isCritical() && !p2.isCritical()) {
				return -1;
			}
			if (p2.isCritical() && !p1.isCritical()) {
				return 1;
			}
			return 0;
		}
	};

	/***
	 * This method takes Device object and sorts it with according comparator
	 */

	public static final void sortPartsByPrice(Device device) {
		Collections.sort(device.getParts(), SORT_PARTS_BY_PRICE);
	}

	/***
	 * This method takes Device object and sorts it with according comparator
	 */

	public static final void sortPartsByCritical(Device device) {
		Collections.sort(device.getParts(), SORT_PARTS_BY_CRITICAL);
	}

	/***
	 * This method takes Device object and sorts it with according comparator
	 */
	public static final void sortPartsByOrigin(Device device) {
		Collections.sort(device.getParts(), SORT_PARTS_BY_ORIGIN);
	}
}