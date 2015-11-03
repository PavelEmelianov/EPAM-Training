package ua.nure.emelianov.Task1;

public class Task5 {

	private static void usage() {
		System.out
				.println("Usage: java "
						+ "ua.nure.emelianov.Task1.Task5 X (first number of x!=0, x%2==0)");
	}

	public static int min(int count) {
		int min = 1;
		for (int i = 1; i < count; i++) {
			min *= 10;
		}
		return min;

	}

	public static int max(int count) {
		int j = 9;
		int max = 9;
		for (int i = 1; i < count; i++) {
			max += Math.pow(10, i) * j;
		}
		return max;

	}

	public static int sum(char[] number) {
		int sum = 0;
		for (int i = 0; i < number.length; i++) {
			sum += Integer.parseInt(String.valueOf(number[i]));
		}
		return sum;
	}

	public static int sum(int count) {
		int sum = 0;
		for (int i = min(count); i <= max(count); i++) {
			char[] first = String.valueOf(i).substring(0, count / 2)
					.toCharArray();
			char[] second = String.valueOf(i).substring(count / 2, count)
					.toCharArray();
			if (sum(first) == sum(second)) {
				sum++;
			}
		}
		return sum;
	}

	public static void main(String[] args) {

		if (args.length != 1 || args[0].charAt(0) == '0'
				|| (Integer.parseInt(args[0]) % 2 != 0)) {
			usage();
			return;
		}

		int count = Integer.parseInt(args[0]);
		System.out.println("The amount of " + count
				+ "-digit lucky numbers are: " + sum(count));

	}
}
