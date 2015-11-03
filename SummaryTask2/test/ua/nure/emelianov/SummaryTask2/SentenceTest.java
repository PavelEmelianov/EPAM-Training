package ua.nure.emelianov.SummaryTask2;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class SentenceTest {

	static Sentence sentence;

	@BeforeClass
	public static void constructorTest() {
		sentence = new Sentence("sentence");
	}

	@Test
	public void getSentenceTest() {
		Assert.assertEquals("sentence", sentence.getSentence());
	}

	@Test
	public void equalWordsTest() {
		sentence.setEqualWords(true);
		Assert.assertEquals(true, sentence.isEqualWords());
	}
}
