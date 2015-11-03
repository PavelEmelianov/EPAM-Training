package ua.nure.emelianov.Practice6.part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parking {

	private Object[] arr2;

	public Object[] getArr2() {
		return Arrays.copyOf(arr2, arr2.length);
	}

	Parking(int number) {
		arr = new ArrayList<>(number);
		arr2 = new Object[number];
	}

	private List<Object> arr;

	public void add(Object o) {
		for (int i = 0; i < arr2.length; i++) {
			if (arr2[i] == null) {
				arr2[i] = o;
				System.out.println(o + "has come");
				break;
			}
		}
	}

	public void remove(Object o) {
		for (int i = 0; i < arr2.length; i++) {
			if (o.equals(arr2[i])) {
				System.out.println(arr2[i] + "removed");
				arr2[i] = null;
				break;
			}
		}

	}

	public static void main(String[] args) {
		Parking parking = new Parking(20);
		parking.arr.add(5);
		System.out.println(parking.arr.get(0));
	}

}
