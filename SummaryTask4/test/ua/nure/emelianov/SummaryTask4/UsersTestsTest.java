package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.bean.UsersTests;

public class UsersTestsTest {
	
	static UsersTests ut;
	
	@BeforeClass
	public static void constructorTest(){
		ut=new UsersTests();
	}
	@Test
	public void gettersSettersTest(){
		ut.setDate("date");
		ut.setfName("fname");
		ut.setId(1);
		ut.setlName("lname");
		ut.setResult(2);
		ut.setTestDifficulty("diff");
		ut.setTestName("testname");
		ut.setThemeName("themename");
		
		Assert.assertEquals("date", ut.getDate());
		Assert.assertEquals("fname", ut.getfName());
		Assert.assertEquals(1, ut.getId());
		Assert.assertEquals("lname", ut.getlName());
		Assert.assertEquals(2, ut.getResult());
		Assert.assertEquals("diff", ut.getTestDifficulty());
		Assert.assertEquals("testname", ut.getTestName());
		Assert.assertEquals("themename", ut.getThemeName());
	}

}
