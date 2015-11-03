package ua.nure.emelianov.SummaryTask2;

/**
 * 
 * class, that represents sentence in the text
 *
 */

public class Sentence {

	Sentence(String sentence) {
		setSentence(sentence);
	}

	private String sentence;

	/***
	 * this field determines that sentence has equal words(or word) in other
	 * sentences in the text
	 */
	private boolean equalWords;

	public String getSentence() {
		return sentence;
	}

	public final void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public boolean isEqualWords() {
		return equalWords;
	}

	public void setEqualWords(boolean equalWords) {
		this.equalWords = equalWords;
	}
}
