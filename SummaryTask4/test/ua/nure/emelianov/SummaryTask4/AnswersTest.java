package ua.nure.emelianov.SummaryTask4;



import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.entity.Answers;

public class AnswersTest {
	
	static Answers answer;
	
	@BeforeClass
	public static void constructorTest(){
		answer = new Answers();
	}
	
	@Test
	public void getterSetterTests(){
		
		answer.setCorrect(true);
		answer.setId(1);
		answer.setName("name");
		answer.setQuestionsId(2);
		
		Assert.assertEquals(true, answer.isCorrect());
		Assert.assertEquals(1, answer.getId());
		Assert.assertEquals("name", answer.getName());
		Assert.assertEquals(2, answer.getQuestionsId());
	}

}
