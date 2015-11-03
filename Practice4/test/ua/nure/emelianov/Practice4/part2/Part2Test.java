package ua.nure.emelianov.Practice4.part2;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Part2Test {

	private String filename = "part2.txt";
	private String filename2 = "part2_sorted.txt";

	Part2 part2;

	@Before
	public void ConstructorTest() {
		part2 = new Part2(filename, filename2, "CP1251");
	}

	@Test
	public void testSortNumbers() {
		Assert.assertEquals("3 9 16 16 18 21 23 23 29 30 ", part2.sortNumbers("30 23 16 16 9 23 3 18 21 29"));
	}

	@Test
	public void testFillWithRandomNumbers() {
		Assert.assertNotEquals(part2.fillWithRandomNumbers(),
				part2.fillWithRandomNumbers());
	}

	@Test
	public void testCalculateRandomInt() {
		Assert.assertEquals(new Integer(0).getClass(),
				new Integer(part2.calculateRandomInt()).getClass());
	}

	@Test
	public void testWriteFirstFile() {
		File file = new File("part2.txt");
		file.setReadOnly();
		part2.writeFirstFile();
		file.setWritable(true);
		part2.writeFirstFile();
		part2.setEncoding("INCORRECT_ENCODING");
		part2.writeFirstFile();
	}

	@Test
	public void testWriteSecondFile() {
		File file = new File(filename);
		File file_sorted = new File(filename2);
		file.setReadOnly();
		file_sorted.setReadOnly();
		part2.writeSecondFile();
		file.setWritable(true);
		file_sorted.setWritable(true);
		part2.writeSecondFile();
		part2.setEncoding("INVALID ENCODING");
		part2.writeSecondFile();
	}

	@Test
	public void testOutput() {
		part2.output();
		part2.setFileName("INCORRECT FILENAME");
		part2.output();
	}

	@Test
	public void testMain() {
		Part2.main(new String[] {});
	}
}
