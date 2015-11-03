package ua.nure.emelianov.SummaryTask1;

import junit.framework.*;

import org.junit.Before;
import org.junit.Test;

public class DemoTest {

	Demo demo;

	@Before
	public void defaultConstructorTest() {
		demo = new Demo();
	}

	@Test
	public void testCreateGift() {
		Demo.createGift();
		Assert.assertEquals(7, Demo.getGift().getList().size());
	}

	@Test
	public void testMain() {
		Demo.main(new String[] {});
	}

}
