package ua.nure.emelianov.SummaryTask2;

/**
 * 
 * class, that represents word in the text
 *
 */
public class Word {

	Word(String word) {
		setWord(word);
	}

	public String getWord() {
		return word;
	}

	public final void setWord(String word) {
		this.word = word;
	}

	private String word;

}
