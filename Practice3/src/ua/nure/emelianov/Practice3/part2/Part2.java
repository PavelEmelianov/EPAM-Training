package ua.nure.emelianov.Practice3.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.emelianov.Practice3.part1.Part1;

public class Part2 {
	private static String input = Part1.getInput("part2.txt");

	public static String getMin(String input) {
		StringBuilder sb = new StringBuilder();
		int count = Integer.MAX_VALUE;
		sb.append("MIN: ");
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			Pattern p2 = Pattern.compile(m.group());
			Matcher m2 = p2.matcher(sb.toString().substring(5));
			if (count > m.group().length()) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("MIN: ");
				count = m.group().length();
				sb2.append(m.group());
				sb = sb2;
			} else if (count == m.group().length() && !(m2.find())) {
				sb.append(", " + m.group());

			}
		}
		return sb.toString();
	}

	public static String getMax(String input) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		sb.append("MAX: ");
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			Pattern p2 = Pattern.compile(m.group());
			Matcher m2 = p2.matcher(sb.toString().substring(5));
			if (count < m.group().length()) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("MAX: ");
				count = m.group().length();
				sb2.append(m.group());
				sb = sb2;
			} else if (count == m.group().length() && !(m2.find())) {
				sb.append(", " + m.group());

			}
		}
		return sb.toString();
	}

	public static String getInput(String filename) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(filename), "cp1251");
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Part2 FileNotFound");
		}
		return sb.toString();

	}

	public static void main(String[] args) {
		System.out.println(getMin(input));
		System.out.println(getMax(input) + System.lineSeparator());

	}
}
