package ua.nure.emelianov.SummaryTask1.GiftContainer;

import ua.nure.emelianov.SummaryTask1.FillingOfTheGift.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is the class-container for AbstractSweets objects, that aggregates
 * the ArrayList
 * 
 */

public class NewYearsGift {

	private List<AbstractSweets> list = new ArrayList<AbstractSweets>();

	public List<AbstractSweets> getList() {
		return list;
	}

	/**
	 * This method adds the object to the container, if the object is already
	 * exists (checking by name), count value of existing object is increased by
	 * current count value.
	 * 
	 * @param absCandy
	 *            AbstractSweets object
	 * @param count
	 *            the number of current objects
	 */

	public void add(AbstractSweets absCandy, int count) {
		for (AbstractSweets candy : getList()) {
			if (candy.getName().equals(absCandy.getName())) {
				candy.setCount(candy.getCount() + count);
				return;
			}
		}
		getList().add(absCandy);
		absCandy.setCount(count);
	}

	/**
	 * This method calculates the total weight of the gift
	 * 
	 * @return total weight
	 */

	public int calculateTotalWeight() {
		int value = 0;
		for (AbstractSweets candy : getList()) {
			value += candy.getCount() * candy.getWeight();
		}
		return value;
	}

	public void getAllCandy() {
		for (AbstractSweets candy : getList()) {
			System.out.print(candy.printAllValues());
		}
	}

	/**
	 * This method returns a new sorted (by sugar value) list of AbstractSweets
	 * objects with the selected range of sugar value
	 * 
	 * @param start
	 *            inclusive value
	 * @param end
	 *            exclusive value
	 * @return new sorted (by sugar value) list of AbstractSweets objects with
	 *         the selected range of sugar value
	 */

	public List<AbstractSweets> receiveBySugarValue(int start, int end) {
		ArrayList<AbstractSweets> list2 = new ArrayList<>();
		sortBySugarValue(list2);
		for (AbstractSweets candy : getList()) {
			if (start <= candy.getSugarValue() && candy.getSugarValue() < end) {
				list2.add(candy);
			}
		}
		return list2;
	}

	/**
	 * This method sorts the existing list of AbstractSweets objects by sugar
	 * value
	 *
	 */

	public void sortBySugarValue(List<AbstractSweets> list) {
		Collections.sort(list, new CompareBySugarValue());
	}

	static class CompareBySugarValue implements Comparator<AbstractSweets>,
			Serializable {

		private static final long serialVersionUID = -6923839843945617223L;

		@Override
		public int compare(AbstractSweets o1, AbstractSweets o2) {
			return o1.getSugarValue() - o2.getSugarValue();
		}

	}
}
