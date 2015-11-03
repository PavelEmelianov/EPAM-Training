package ua.nure.emelianov.Task1;

public class Task4 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task4 X ");
	}

	public static int factorial(int number) {
		int factorial = 1;
		for (int i = 2; i <= number; i++) {
			factorial *= i;
		}
		return factorial;
	}

	public static int sum(int number) {
		int sum = 0;
		for (int i = 1; i <= number; i++) {
			if (i % 2 == 1) {
				sum += factorial(i);
			} else {
				sum -= factorial(i);
			}

		}
		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		System.out
				.println("The sum of factorials (1! - 2! + 3! - 4! + 5! - ... + N! when N > 0) for number "
						+ args[0] + " is: " + sum(Integer.parseInt(args[0])));
	}
}
