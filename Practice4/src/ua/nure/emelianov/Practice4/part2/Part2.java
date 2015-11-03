package ua.nure.emelianov.Practice4.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Part2 {

	Part2(String fileName, String fileName2, String encoding) {
		setFileName(fileName);
		setFileName2(fileName2);
		setEncoding(encoding);
	}

	private Random random = new Random();
	
	private String encoding = "CP1251";
	
	private String fileName;
	
	private String fileName2;

	private int n = 10;
	
	private int max = 50;
	
	public Random getRandom() {
		return random;
	}


	public String getEncoding() {
		return encoding;
	}

	public final void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName2() {
		return fileName2;
	}

	public final void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}


	public int getN() {
		return n;
	}

	public int getMax() {
		return max;
	}


	public String sortNumbers(String str) {
		String[] arr = str.split(" ");
		int[] arr2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr2[i] = Integer.valueOf(arr[i]);
		}
		Arrays.sort(arr2);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr2.length; i++) {
			sb.append(arr2[i] + " ");
		}
		return sb.toString();
	}

	public String fillWithRandomNumbers() {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while (count != getN()) {
			sb.append(calculateRandomInt() + " ");
			count++;
		}

		return sb.toString().substring(0, sb.length()-1);
	}

	public int calculateRandomInt() {
		return getRandom().nextInt(getMax());

	}

	public void writeFirstFile() {
		try {
			PrintWriter pr = new PrintWriter(new File(getFileName()),
					getEncoding());
			pr.write(fillWithRandomNumbers());
			pr.close();
		} catch (FileNotFoundException e) {
			System.out.println(getFileName() + " cannot be written");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding");
		}
	}

	public void writeSecondFile() {
		try {
			Scanner sc = new Scanner(new File(getFileName()), getEncoding());
			PrintWriter pr2 = new PrintWriter(new File(getFileName2()),
					getEncoding());
			while (sc.hasNextLine()) {
				pr2.write(sortNumbers(sc.nextLine()));
			}
			sc.close();
			pr2.close();
		} catch (FileNotFoundException e) {
			System.out.println(getFileName() + " or " + getFileName2()
					+ " cannot be written");
		} catch (UnsupportedEncodingException | IllegalArgumentException e) {
			System.out.println("Unsupported encoding");
		}

	}

	public String output() {
		String str = null;
		try {
			StringBuilder sb = new StringBuilder();
			Scanner sc = new Scanner(new File(getFileName()), getEncoding());
			while (sc.hasNextLine()) {
				sb.append("input: " + sc.nextLine() + System.lineSeparator());
			}
			sc = new Scanner(new File(getFileName2()), getEncoding());
			while (sc.hasNextLine()) {
				sb.append("output: " + sc.nextLine());
			}
			sc.close();
			str = sb.toString().substring(0, sb.length() - 1);

		} catch (FileNotFoundException e) {
			System.out.println(getFileName() + "  or " + getFileName2()
					+ " not found");
		}
		return str;
	}

	public static void main(String[] args) {
		Part2 part2 = new Part2("part2.txt", "part2_sorted.txt", "CP1251");
		part2.writeFirstFile();
		part2.writeSecondFile();
		System.out.println(part2.output());

	}
}