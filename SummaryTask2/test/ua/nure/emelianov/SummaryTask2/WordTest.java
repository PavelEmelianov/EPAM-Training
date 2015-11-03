package ua.nure.emelianov.SummaryTask2;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordTest {

	static Word word;

	@BeforeClass
	public static void constructorTest() {
		word = new Word("Word");
	}

	@Test
	public void getWordTest() {
		Assert.assertEquals("Word", word.getWord());
	}

}
