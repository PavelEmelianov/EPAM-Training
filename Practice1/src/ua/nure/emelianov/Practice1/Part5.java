package ua.nure.emelianov.Practice1;

public class Part5 {

	public static int charToInt(char character) {
		return (character - 'A' + 1);
	}

	public static char intToChar(int number) {
		return (char) ('A' + number);
	}

	public static int chars2digits(String number) {
		int result = charToInt(number.charAt(number.length() - 1));
		int it = 1;
		for (int i = number.length() - 2; i >= 0; i--) {
			result += Math.pow(26, it) * charToInt(number.charAt(i));
			it++;
		}
		return result;
	}

	public static String digits2chars(int a) {
		int c = a;
		StringBuilder sb = new StringBuilder();
		int b;
		while (c != 0) {
			c--;
			b = c % 26;
			sb.insert(0, intToChar(b));
			c /= 26;
		}
		return sb.toString();
	}

	public static String rightColumn(String number) {
		String result;
		int a = chars2digits(number);
		a++;
		result = digits2chars(a);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(args[0] + "==> "+chars2digits(args[0]));
		System.out.println(args[1] + "==> "+digits2chars(Integer.parseInt(args[1])));
		System.out.println(args[2] + "==> "+rightColumn(args[2]));
	}

}
