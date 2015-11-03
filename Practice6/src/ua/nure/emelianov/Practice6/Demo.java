package ua.nure.emelianov.Practice6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ua.nure.emelianov.Practice6.part1.Part1;
import ua.nure.emelianov.Practice6.part2.Part2;
import ua.nure.emelianov.Practice6.part3.Part3;
import ua.nure.emelianov.Practice6.part4.Part4;
import ua.nure.emelianov.Practice6.part5.Part5;
import ua.nure.emelianov.Practice6.part6.Part6;
import ua.nure.emelianov.Practice6.part7.Part7;

public class Demo {

	public static void main(String[] args) throws IOException {

		try {
			System.setIn(new ByteArrayInputStream(
					"asd asdf asd asdf asdf 43 asdsf 43 43 434".getBytes(System
							.getProperty("file.encoding"))));
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported encoding");
		}
		System.setIn(System.in);
		System.out.println("~~~~~~~~~~~~Part1");
		Part1.main(args);

		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);
		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);
		System.out.println();

		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);

		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);

		System.out.println("~~~~~~~~~~~~Part6");
		System.out.println("Frequency demonstration: ");
		Part6.main(new String[] { "-i", "input.txt", "-t", "frequency" });
		System.out.println("Length demonstration: ");
		Part6.main(new String[] { "-i", "input.txt", "-t", "length" });
		System.out.println("Duplicates demonstration: ");
		Part6.main(new String[] { "-i", "input.txt", "-t", "duplicates" });
		System.out.println("~~~~~~~~~~~~Part7");
		Part7.main(args);
	}

}