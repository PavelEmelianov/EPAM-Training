package ua.nure.emelianov.Practice4.part5;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import ua.nure.emelianov.Practice4.part5.Part5;

public class Part5Test {

	private static final String ENCODING = "Cp1251";
	private String s = new String(System.lineSeparator());
	

	Part5 part5;

	@Before
	public void testConstructor() {
		part5 = new Part5();
	}

	@Test
	public void testOutput() {
		try {
			System.setIn(new ByteArrayInputStream(
					("table ru"+s+"table e"+s+"napple ru"+s+"stop").getBytes(ENCODING)));
			part5.output();
			System.setIn(new ByteArrayInputStream("INCORRECT_INPUT"
					.getBytes(ENCODING)));
			part5.output();
			System.setIn(new ByteArrayInputStream(
					("INCORRECT_KEY ru"+s+"table en"+s+"apple ru").getBytes(ENCODING)));
			part5.output();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding in test file");
		}
	}

	@Test
	public void testMain() {
		try {
			System.setIn(new ByteArrayInputStream(
					("table ru"+s+"table en"+s+"apple ru"+s+"stop").getBytes(ENCODING)));
			Part5.main(new String[] {});
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding in test file");
		}
	}

}
