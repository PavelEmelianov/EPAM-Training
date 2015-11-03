package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask.security.Password;
import ua.nure.emelianov.SummaryTask4.exception.AppException;

public class PasswordTest {

	static Password pass;

	@BeforeClass
	public static void constructorTest() {
	Assert.assertNotNull(pass = new Password());
	}

	@Test
	public void hashTest() throws AppException {
		Assert.assertEquals(128, Password.hash("anyString").length());
	}
}
