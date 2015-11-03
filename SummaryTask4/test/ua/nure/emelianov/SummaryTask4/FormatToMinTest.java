package ua.nure.emelianov.SummaryTask4;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.customTag.FormatToMin;

public class FormatToMinTest {
	static FormatToMin fmt;

	@BeforeClass
	public static void constructorTest() {
		fmt = new FormatToMin();
	}

	@Test
	public void doTagTest() throws JspException, IOException {
		fmt.setNumber("3600");
		fmt.doTag();
	}
}
