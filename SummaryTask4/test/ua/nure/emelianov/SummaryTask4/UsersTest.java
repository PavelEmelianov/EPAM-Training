package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.entity.Users;

public class UsersTest {
	
	static Users user;

	@BeforeClass
	public static void constructorTest(){
		user= new Users();
	}
	
	@Test
	public void gettersSettersTest(){
		user.setBlocked(true);
		user.seteMail("mail");
		user.setfName("fname");
		user.setId(1);
		user.setlName("lname");
		user.setLogin("login");
		user.setPassword("password");
		user.setRoleId(2);
		
		
		Assert.assertEquals(true, user.getBlocked());
		Assert.assertEquals("mail", user.geteMail());
		Assert.assertEquals("fname", user.getfName());
		Assert.assertEquals(1, user.getId());
		Assert.assertEquals("lname", user.getlName());
		Assert.assertEquals("login", user.getLogin());
		Assert.assertEquals("password", user.getPassword());
		Assert.assertEquals(2, user.getRoleId());
	}
	
}
