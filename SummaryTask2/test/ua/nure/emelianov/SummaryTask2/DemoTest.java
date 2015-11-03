package ua.nure.emelianov.SummaryTask2;

import org.junit.Test;

public class DemoTest {

	@Test
	public void defaultConstructorTest() {
		Demo demo = new Demo();
	}

	@Test
	public void demoTest() {
		Demo.main(new String[] { "input.txt" });
	}

}
