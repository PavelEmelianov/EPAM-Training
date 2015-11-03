package ua.nure.emelianov.Task1;

import java.util.Arrays;

public class Task7 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task7 X ");
	}

	public static int[] primeNumber(int number) {
		int[] arr = new int[number];
		int value = 0;
		for (int i = 0; i < number; i++) {
			for (int j = value + 1; j < Integer.MAX_VALUE; j++) {
				int count = 0;
				for (int x = 1; x <= j; x++) {
					if (j % x == 0) {
						count++;
					}
				}
				if (count == 2 || number == 1) {
					arr[i] = j;
					value = j;
					break;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {

		if (args.length != 1) {
			usage();
			return;
		}
		int size = Integer.parseInt(args[0]);
		System.out.println("The array of " + size + " prime numbers is: "
				+ Arrays.toString(primeNumber(size)));
	}
}
