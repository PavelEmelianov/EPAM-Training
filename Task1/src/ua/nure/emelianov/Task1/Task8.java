package ua.nure.emelianov.Task1;

import java.util.Arrays;

public class Task8 {

	private static void usage() {
		System.out
				.println("Usage: java " + "ua.nure.emelianov.Task1.Task8 X Y");
	}

	public static char[][] chessOrder(int n, int m) {
		char[][] arr = new char[n][m];
		boolean flag = true;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				if (flag) {
					arr[i][0] = '×';
				} else {
					arr[i][0] = 'Á';
				}
				if (arr[i][j - 1] == '×') {
					arr[i][j] = 'Á';
				} else {
					arr[i][j] = '×';
				}
			}
			flag = !flag;
		}
		return arr;
	}

	public static void main(String[] args) {

		if (args.length != 2) {
			usage();
			return;
		}
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		char[][] result = chessOrder(n, m);
		System.out
				.println("The two-dimensional array " + n + "*" + m + " is: ");
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}

}
