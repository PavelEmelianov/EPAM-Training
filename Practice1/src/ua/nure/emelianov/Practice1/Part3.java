package ua.nure.emelianov.Practice1;

public class Part3 {

	public static int calculate(String first, String second) {

		int a = Integer.parseInt(first);
		int b = Integer.parseInt(second);

		while (b != 0) {
			int tmp = a % b;
			a = b;
			b = tmp;
		}

		return a;
	}

	public static void main(String[] args) {
		System.out.println(calculate(args[0], args[1]));
	}

}