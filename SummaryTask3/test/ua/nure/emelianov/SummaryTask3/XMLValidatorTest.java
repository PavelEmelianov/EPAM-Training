package ua.nure.emelianov.SummaryTask3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XMLValidatorTest {

	XMLValidator vld;

	@Before
	public void defaultConstructorTest() {
		vld = new XMLValidator();
	}

	@Test
	public void mainTest() {
		XMLValidator.main(new String[] {});
	}

	@Test
	public void validateXMLSchemaTest() {
		Assert.assertFalse(XMLValidator.validateXMLSchema("input.xsd",
				"input-invalid.xml"));
		Assert.assertTrue(XMLValidator.validateXMLSchema("input.xsd",
				"input.xml"));
	}

}
