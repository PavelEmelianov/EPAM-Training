package ua.nure.emelianov.Practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

	private int number;

	private static final int COUNTER = 50_000;

	public final int getNumber() {
		return number;
	}

	public final void setNumber(int number) {
		this.number = number;
	}

	Part2(int number) {
		setNumber(number);
		list = new ArrayList<Integer>();
		list2 = new LinkedList<Integer>();
		fillCollection();
	}

	private List<Integer> list;
	private List<Integer> list2;

	public final void fillCollection() {
		for (int i = 0; i < getNumber(); i++) {
			list.add(i);
			list2.add(i);
		}
	}

	public long removeFromList(List<Integer> list3, int counter) {
		long time = System.currentTimeMillis();
		int local = 0;
		for (int i = list3.size() - 1; i >= 0; i--) {
			if (list3.size() == 1) {
				break;
			}

			local += (counter - 1);

			while (local >= list3.size()) {
				local = local - list3.size();
			}
			list3.remove(local);
		}
		return System.currentTimeMillis() - time;
	}

	public static void main(String[] args) {
		Part2 pt2 = new Part2(COUNTER);
		System.out.println("The time for ArrayList(" + COUNTER + " values):");
		System.out.println(pt2.removeFromList(pt2.list, 3));

		System.out.println("The time for LinkedList(" + COUNTER + " values):");
		System.out.println(pt2.removeFromList(pt2.list2, 3));
	}

}
