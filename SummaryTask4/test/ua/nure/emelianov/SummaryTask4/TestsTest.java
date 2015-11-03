package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.entity.Tests;

public class TestsTest {

	static Tests tests;

	@BeforeClass
	public static void constructorTest() {
		tests = new Tests();
	}

	@Test
	public void getterSetterTest() {
		tests.setDifficulty("diff");
		tests.setId(1);
		tests.setName("name");
		tests.setQuestionsCount(2);
		tests.setThemesId(3);
		tests.setTimeForTest(4);
		Assert.assertEquals("diff", tests.getDifficulty());
		Assert.assertEquals(1, tests.getId());
		Assert.assertEquals("name", tests.getName());
		Assert.assertEquals(2, tests.getQuestionsCount());
		Assert.assertEquals(3, tests.getThemesId());
		Assert.assertEquals(4, tests.getTimeForTest());
	}
}
