package ua.nure.emelianov.Task1;

public class Task9 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task9 X ");
	}

	public static int[][][][][][] sixDimensionalArray(int number) {
		int[][][][][][] array = new int[number][number][number][number][number][number];
		int x = 0;
		for (int i = 0; i < Math.pow(number, 6); i++) {
			array[x & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1] = i + 1;
			x = i + 1;
		}
		return array;

	}

	public static void output(int[][][][][][] arr) {
		int x = 0;
		for (int i = 0; i < Math.pow(arr.length, 6); i++) {
			System.out
					.println(arr[x & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1][(x >>= 1) & 1]);
			x = i + 1;
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		int number = Integer.parseInt(args[0]);
		System.out.println("The values of six-dimensional array: ");
		output(sixDimensionalArray(number));
	}

}
