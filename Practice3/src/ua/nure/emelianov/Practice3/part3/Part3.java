package ua.nure.emelianov.Practice3.part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	private static String input = getInput("part3.txt");

	public static String toUpperCase(String input) {
		StringBuilder sb = new StringBuilder(input);
		Pattern p = Pattern.compile("[a-ÿÀ-ß]+");
		Matcher m = p.matcher(sb);
		while (m.find()) {
			sb.replace(m.start(), m.start() + 1, m.group().substring(0, 1)
					.toUpperCase());
		}

		return sb.toString();

	}

	public static String getInput(String filename) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(new File(filename), "cp1251");
			while (sc.hasNextLine()) {
				sb.append(sc.nextLine() + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {

			System.err.println("Part3 FileNotFound");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(Part3.toUpperCase(input));
	}

}
