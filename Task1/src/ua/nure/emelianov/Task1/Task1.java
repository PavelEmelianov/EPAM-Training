package ua.nure.emelianov.Task1;

public class Task1 {

	private static void usage() {
		System.out.println("Usage: java "
				+ "ua.nure.emelianov.Task1.Task1 X Y");
	}

	public static int nod(int a, int b) {
		int x = a;
		int y = b;

		while (y != 0) {
			int tmp = x % y;
			x = y;
			y = tmp;
		}

		return x;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
			return;
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int nod = nod(x, y);
		System.out.println("The greatest common divisor of " + x + " and " + y
				+ " is: " + nod);
	}

}