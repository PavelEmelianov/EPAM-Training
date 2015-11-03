package ua.nure.emelianov.Task1;

import java.util.Arrays;

public class Task6 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task6 X ");
	}

	public static long[] fib(int n) {
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			if (i < 2) {
				arr[i] = 1;
				continue;
			}
			arr[i] = arr[i - 2] + arr[i - 1];
		}

		return arr;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		int size = Integer.parseInt(args[0]);
		System.out.println("The array of " + size + " fibonacci numbers is: "
				+ Arrays.toString(fib(8)));

	}

}