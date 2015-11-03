package ua.nure.emelianov.Practice1;

public class Part4 {

	public static int sum(String str) {
		int result = 0;

		for (int i = 0; i < str.length(); i++) {

			result += Integer.parseInt(String.valueOf(str.charAt(i)));
		}

		return result;

	}

	public static void main(String[] args) {

		System.out.println(sum(args[0]));

	}

}
