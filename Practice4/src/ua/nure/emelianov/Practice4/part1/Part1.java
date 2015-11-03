package ua.nure.emelianov.Practice4.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	Part1(String fileName) {
		setFileName(fileName);
	}

	private String fileName;

	private static final String ENCODING = "CP1251";

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void convertToUpperCase() {
		Scanner s;
		try {
			s = new Scanner(new File(getFileName()), ENCODING);
			StringBuilder sb = new StringBuilder();
			while (s.hasNextLine()) {
				sb.append(s.nextLine() + System.lineSeparator());
				Pattern p = Pattern.compile("[Р-пр-џ\\w]{3,}");
				Matcher m = p.matcher(sb);

				while (m.find()) {
					sb.replace(m.start(), m.end(), m.group().toUpperCase());
				}
			}
			System.out.println(sb.toString().substring(0, sb.length() - 2));
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println(getFileName() + " not found");
		}
	}

	public static void main(String[] args) {
		new Part1("part1.txt").convertToUpperCase();
	}
}