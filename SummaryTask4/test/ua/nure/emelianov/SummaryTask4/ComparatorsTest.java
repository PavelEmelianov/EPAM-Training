package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.Comparators.CompareByUsersTestsId;
import ua.nure.emelianov.SummaryTask4.db.bean.UsersTests;
import ua.nure.emelianov.SummaryTask4.db.entity.Tests;

public class ComparatorsTest {

	private static class TestsMock extends Tests {

		private static final long serialVersionUID = 5958833986876244768L;

	}

	private static class UsersTestsMock extends UsersTests {

		private static final long serialVersionUID = 5958833986876244768L;

	}

	static Comparators comp;

	@BeforeClass
	public static void constructorTest() {
		Assert.assertNotNull(comp = new Comparators());
	}

	@Test
	public void nameComparatorTest() {

		Tests t1 = new ComparatorsTest.TestsMock();
		t1.setName("A");
		Tests t2 = new ComparatorsTest.TestsMock();
		t2.setName("B");
		Assert.assertEquals(-1, new Comparators.CompareByName().compare(t1, t2));
	}

	@Test
	public void difficultyComparatorTest() {

		Tests t1 = new ComparatorsTest.TestsMock();
		t1.setDifficulty("elementary");
		Tests t3 = new ComparatorsTest.TestsMock();
		t3.setDifficulty("proficient");
		Tests t2 = new ComparatorsTest.TestsMock();
		t2.setDifficulty("advanced");
		Assert.assertEquals(-1,
				new Comparators.CompareByDifficulty().compare(t1, t2));
		Assert.assertEquals(1,
				new Comparators.CompareByDifficulty().compare(t2, t1));
		Assert.assertEquals(-2,
				new Comparators.CompareByDifficulty().compare(t1, t3));
		Assert.assertEquals(2,
				new Comparators.CompareByDifficulty().compare(t3, t1));
		Assert.assertEquals(-1,
				new Comparators.CompareByDifficulty().compare(t2, t3));
		Assert.assertEquals(1,
				new Comparators.CompareByDifficulty().compare(t3, t2));
		Assert.assertEquals(0,
				new Comparators.CompareByDifficulty().compare(t3, t3));
	}

	@Test
	public void questionsCountComparatorTest() {

		Tests t1 = new ComparatorsTest.TestsMock();
		t1.setQuestionsCount(5);
		;
		Tests t2 = new ComparatorsTest.TestsMock();
		t2.setQuestionsCount(6);
		Assert.assertEquals(-1,
				new Comparators.CompareByQuestionsCount().compare(t1, t2));
	}

	@Test
	public void UsersTestsIdComparatorTest() {

		UsersTestsMock ut1 = new ComparatorsTest.UsersTestsMock();
		ut1.setId(1);
		UsersTestsMock ut2 = new ComparatorsTest.UsersTestsMock();
		ut2.setId(2);
		Assert.assertEquals(1, new CompareByUsersTestsId().compare(ut1, ut2));
	}
}
