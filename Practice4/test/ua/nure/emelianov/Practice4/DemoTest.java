package ua.nure.emelianov.Practice4;

import org.junit.Before;
import org.junit.Test;

public class DemoTest {

	Demo demo;

	@Before
	public void defaultConstructorTest() {
		demo = new Demo();
	}

	@Test
	public void testMain() {
		Demo.main(new String[] {});
		Demo.setEncoding("INVALID ENCODING");
		Demo.main(new String[] {});
	}

}
