package ua.nure.emelianov.Task1;

public class Task2 {

	private static void usage() {
		System.out.println("Usage: java " + "ua.nure.emelianov.Task1.Task2 X ");
	}

	public static int sum(String number) {
		int sum = 0;
		for (int i = 0; i < number.length(); i++) {
			sum += Integer.parseInt(String.valueOf(number.charAt(i)));
		}
		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			usage();
			return;
		}
		System.out.println("The sum of digits of " + args[0] + " is: "
				+ sum(args[0]));

	}

}
