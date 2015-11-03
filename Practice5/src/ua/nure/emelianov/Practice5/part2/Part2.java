package ua.nure.emelianov.Practice5.part2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part2 {

	private static String encoding = "cp1251";

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		Part2.encoding = encoding;
	}

	public static void main(String[] args) {
		// save standard input
		InputStream stdIn = System.in;

		// create input stream with line terminator (=ENTER)
		ByteArrayInputStream bais;
		try {
			bais = new ByteArrayInputStream(System.lineSeparator().getBytes(
					getEncoding()));
		} catch (UnsupportedEncodingException e1) {
			System.err.println("Unsupported Encoding in Part2.main()");
			return;
		}

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// main functionality
		Spam.main(args);

		// assign new value of standard input
		System.setIn(bais);
		// waith for 5 sec
		try {
			Thread.sleep(5001);
		} catch (InterruptedException e) {
			System.err.println("Thread:" + Thread.currentThread().getName()
					+ " is interrupted in Part2.main()");
		}
		Spam.setFlag(true);
		System.out.println("Try to send enter to standard input");
		// move internal pointer to begin of input
		bais.reset();

		// restore standard input
		System.setIn(stdIn);
	}
}
