package ua.nure.emelianov.Task1;

public class Task3 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task3 X ");
	}

	public static boolean primeNumber(int number) {

		int count = 0;
		for (int i = 1; i <= number; i++) {
			if (number % i == 0) {
				count++;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		System.out.println("Number " + args[0] + " is prime: "
				+ primeNumber(Integer.parseInt(args[0])));
	}

}
