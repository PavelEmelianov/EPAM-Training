package ua.nure.emelianov.Practice4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import ua.nure.emelianov.Practice4.part1.Part1;
import ua.nure.emelianov.Practice4.part2.Part2;
import ua.nure.emelianov.Practice4.part3.Part3;
import ua.nure.emelianov.Practice4.part4.Part4;
import ua.nure.emelianov.Practice4.part5.Part5;

public class Demo {

	private static final InputStream STD_IN = System.in;

	private static String encoding = "cp1251";

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		Demo.encoding = encoding;
	}

	public static void main(String[] args) {

		try {
			System.out.println("=========================== PART1");
			Part1.main(args);

			System.out.println("=========================== PART2");
			Part2.main(args);

			System.out.println("=========================== PART3");
			// set the mock input
			System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble"
					.getBytes(getEncoding())));
			Part3.main(args);
			// restore the standard input
			System.setIn(STD_IN);

			System.out.println("=========================== PART4");
			Part4.main(args);

			System.out.println("=========================== PART5");
			// set the mock input
			System.setIn(new ByteArrayInputStream(
					"table ru\ntable en\napple ru".getBytes(getEncoding())));
			Part5.main(args);
			// restore the standard input
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding in Demo.java");
		}
	}

}