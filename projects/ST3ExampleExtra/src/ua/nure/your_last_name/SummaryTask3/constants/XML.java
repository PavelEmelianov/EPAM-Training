package ua.nure.your_last_name.SummaryTask3.constants;

/**
 * Holds entities declared in XSD document.
 * 
 * @author D.Kolesnikov
 * 
 */
public enum XML {

	// elements names
	TEST("Test"), QUESTION("Question"), QUESTION_TEXT("QuestionText"), ANSWER("Answer"),

	// attribute name
	CORRECT("correct"),

	// target namespace uri
	TNS_URI("http://nure.ua/your_last_name/SummaryTask3/entity");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	/**
	 * Determines if a name is equal to the string value wrapped by the enum element.<br/>
	 * Note: we do not override equals(Object o) method here.<br/>
	 * If SAX/StAX parser make all names of elements and attributes interned you can use the following
	 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
	 * @param name string to compare with value. 
	 * @return value.equals(name)
	 */
	public boolean is(String name) {
		return value.equals(name);
	}
	
	public String value() {
		return value;
	}

}