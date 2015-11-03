package ua.nure.emelianov.Task1;

import java.util.Arrays;

public class Task10 {

	private static void usage() {
		System.out
				.println("Usage: java " + "ua.nure.emelianov.Task1.Task10 X ");
	}

	public static int[][] triangleArray(int n) {

		int count = n + 1;

		int secondSize = 1;
		for (int i = 0; i < n; i++) {
			secondSize += 2;
		}
		int[][] arr = new int[count][secondSize];

		int pos1 = (secondSize / 2 + secondSize % 2) - 1;
		int pos2 = (secondSize - (secondSize / 2 + secondSize % 2)) - 1;

		for (int i = 0; i < arr.length; i++) {

			for (int j = pos1; j <= pos2 + 1 & pos1 >= 0; j++) {
				if (i == 0) {
					arr[i][j] = 1;
				} else {
					if (j == 0) {
						arr[i][j] = arr[i - 1][j + 1];
						continue;
					}
					if (j == arr[i].length - 1) {
						arr[i][j] = arr[i - 1][j - 1];
						continue;
					}
					arr[i][j] = arr[i - 1][j + 1] + arr[i - 1][j - 1];
				}
			}
			pos1 = pos1 - 1;
			pos2 = pos2 + 1;
		}

		return arr;

	}

	public static void main(String[] args) {

		if (args.length != 1) {
			usage();
			return;
		}

		int pow = Integer.parseInt(args[0]);
		System.out.println("Pascale's triangle in " + pow + " degree: ");
		for (int i = 0; i < triangleArray(pow).length; i++) {
			System.out.println(Arrays.toString(triangleArray(pow)[i]));
		}

	}

}
