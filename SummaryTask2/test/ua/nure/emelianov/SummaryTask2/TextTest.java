package ua.nure.emelianov.SummaryTask2;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextTest {

	static Text text;

	@BeforeClass
	public static void constructorTest() {
		text = new Text();
	}

	@Before
	public void testGetSetFilename() {
		text.setFileName("input.txt");
		Assert.assertEquals("input.txt", text.getFileName());
	}

	@Test
	public void testFillContainer() {
		text.fillContainer();
		for (Map.Entry<Sentence, List<Word>> entry : text.getContainer()
				.entrySet()) {
			Assert.assertNotNull(entry.getKey());
			Assert.assertNotNull(entry.getValue());

		}
		text.setFileName("INCORRECT FILENAME");
		text.fillContainer();

	}

	@Test
	public void testComparison() {
		text.fillContainer();
		text.comparison();
		boolean flag = false;
		for (Map.Entry<Sentence, List<Word>> entry : text.getContainer()
				.entrySet()) {
			if (entry.getKey().isEqualWords()) {
				flag = true;
				break;
			}

		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testToString() {
		text.toString();

	}

}
