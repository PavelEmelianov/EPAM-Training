package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.entity.Themes;

public class ThemesTest {

	static Themes themes;

	@BeforeClass
	public static void constructorTest() {
		themes = new Themes();
	}

	@Test
	public void getterSetterTest() {
		themes.setBlocked(true);
		themes.setId(1);
		themes.setName("name");

		Assert.assertEquals(true, themes.isBlocked());
		Assert.assertEquals(1, themes.getId());
		Assert.assertEquals("name", themes.getName());
	}
}
