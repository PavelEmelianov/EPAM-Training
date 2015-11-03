package ua.nure.emelianov.SummaryTask3;

import org.junit.Test;

import ua.nure.emelianov.SummaryTask3.Demo;

public class DemoTest {
	
	@Test
	public void defaultConstructorTest() {
		Demo demo = new Demo();
	}

	@Test
	public void mainTest() {
		Demo.main(new String[0]);
	}

}
