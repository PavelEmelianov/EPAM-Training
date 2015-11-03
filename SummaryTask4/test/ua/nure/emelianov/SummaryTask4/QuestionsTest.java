package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.entity.Questions;

public class QuestionsTest {
	
	static Questions question;
	
	@BeforeClass
	public static void constructorTest(){
		question=new Questions();
	}
	
	@Test
	public void gettersSettersTest(){
		question.setId(1);
		question.setName("name");
		question.setTestsId(2);
		Assert.assertEquals(1, question.getId());
		Assert.assertEquals("name", question.getName());
		Assert.assertEquals(2, question.getTestsId());
	}

}
