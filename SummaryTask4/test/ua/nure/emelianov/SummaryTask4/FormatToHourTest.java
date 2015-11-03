package ua.nure.emelianov.SummaryTask4;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.customTag.FormatToHour;

public class FormatToHourTest {
	
	static FormatToHour fmt;

	@BeforeClass
	public static void constructorTest() {
		fmt = new FormatToHour();
	}
	@Test
	public void doTagTest() throws JspException, IOException{
		fmt.setNumber("3600");
		fmt.doTag();
	}
}
