package ua.nure.emelianov.SummaryTask4;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidatorsTest {

	private static final String INVALID_SYMBOLS = "\" has invalid symbols";
	private static final String EMPTY = "\" cannot be empty";
	private static final String FIELD = "field \"";
	private static final String LONG = "\" is too long";

	static Validators validators;

	@BeforeClass
	public static void validatorsTest() {
		validators = new Validators();
	}

	@Test
	public void testValidateDifficulty() {
		Assert.assertEquals(null, Validators.validateDifficulty("elementary"));
		Assert.assertEquals("invalid difficulty, only \"elementary\", \"advanced\",\"proficient\" are available", Validators.validateDifficulty("something"));
		Assert.assertEquals(FIELD + "difficulty" + EMPTY, Validators.validateDifficulty(null));
	}
	@Test
	public void testValidateEmail(){
		Assert.assertEquals(null,Validators.validateEMail("mail@mail.ru", 20));
		Assert.assertEquals(FIELD + "e-mail" + LONG,Validators.validateEMail("mail@mail.ru", 5));
		Assert.assertEquals(FIELD + "e-mail" + EMPTY,Validators.validateEMail(null, 5));
		Assert.assertEquals(FIELD + "e-mail" + INVALID_SYMBOLS,Validators.validateEMail("TVRHVr", 10));
	}
	@Test
	public void testValidateFields(){
		Assert.assertEquals(FIELD + "fname" + EMPTY,Validators.validateFields("", "fname", 20));
		Assert.assertEquals(FIELD + "fname" + LONG,Validators.validateFields("field", "fname", 3));
		Assert.assertEquals(null,Validators.validateFields("field", "fname", 10));
		Assert.assertEquals(FIELD + "fname" + INVALID_SYMBOLS,Validators.validateFields("ÍÔ‡ÔÍ?:", "fname", 10));
		Assert.assertEquals(FIELD + "fname" + INVALID_SYMBOLS,Validators.validateFields("!%:?", "fname", 10));
	}
	@Test
	public void testValidateSentences(){
		Assert.assertEquals(FIELD + "sentence" + EMPTY,Validators.validateSentences("", "sentence", 5));
		Assert.assertEquals(FIELD + "sentence" + LONG,Validators.validateSentences("wgfgdgfdgfdg", "sentence", 5));
		Assert.assertEquals(null,Validators.validateSentences("wgfgdgfdgfdg", "sentence", 20));
		Assert.assertEquals(FIELD + "sentence" + INVALID_SYMBOLS,Validators.validateSentences("    ", "sentence", 20));
	}
	@Test
	public void testValidateOnlyLatinFields(){
		Assert.assertEquals(FIELD + "sentence" + LONG,Validators.validateOnlyLatinFields("fdfafd", "sentence", 3));
		Assert.assertEquals(FIELD + "sentence" + EMPTY,Validators.validateOnlyLatinFields("", "sentence", 3));
		Assert.assertEquals(null,Validators.validateOnlyLatinFields("ggggggg", "sentence",10));
		Assert.assertEquals(FIELD + "sentence" + INVALID_SYMBOLS,Validators.validateOnlyLatinFields("‡‡ÔÔ‡", "sentence", 10));
		Assert.assertEquals(FIELD + "sentence" + INVALID_SYMBOLS,Validators.validateOnlyLatinFields("‡‡ÔÔ‡hggh", "sentence", 10));
	}
	@Test
	public void testValidateNumbers(){
		Assert.assertEquals(FIELD + "sentence" + LONG,Validators.validateNumbers("435", "sentence", 1));
		Assert.assertEquals(FIELD + "sentence" + EMPTY,Validators.validateNumbers("", "sentence", 1));
		Assert.assertEquals(null,Validators.validateNumbers("323", "sentence", 10));
		Assert.assertEquals(FIELD + "sentence" + INVALID_SYMBOLS,Validators.validateNumbers("vcx", "sentence", 5));
	}
}
