package ua.nure.your_last_name.Practice4.part4;

public class Part4 {

	private static final String FILE_NAME = "part4.txt";

	private static final String ENCODING = "Cp1251";

	public static void main(String[] args) {
		// TODO this code throws a NullPointerEexception
		// you must implement the Parser before use it
		Parser parser = new Parser(FILE_NAME, ENCODING);		
		for (String str : parser) {
			System.out.println(str);
		}
	}

}