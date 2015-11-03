package ua.nure.emelianov.Practice4.part1;

import org.junit.Before;
import org.junit.Test;

public class Part1Test {

	Part1 part1;

	@Before
	public  void defaultConstructorTest() {
		part1 = new Part1("part1.txt");
	}

	@Test
	public void testConvertToUpperCase() {
		part1.convertToUpperCase();
		part1.setFileName("fdsfsdf");
		part1.convertToUpperCase();
	}
	@Test
	public void testMain() {
		Part1.main(new String[] {});
	}
}
