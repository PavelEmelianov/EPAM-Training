package ua.nure.emelianov.SummaryTask4;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.web.Controller;

public class ControllerTest {

	static Controller ct;

	@BeforeClass
	public static void constructorTest() {
		ct = new Controller();
	}

	@Test (expected = IllegalStateException.class)
	public void test() throws ServletException, IOException {
		ct.service(new RequestFacade(null), new ResponseFacade(null));
	}

}
