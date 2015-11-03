package ua.nure.emelianov.Practice6.part1;

import java.io.Serializable;
import java.util.Comparator;

public class Word {

	private String word;

	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}

class CompareByFrequency implements Comparator<Word>, Serializable {

	private static final long serialVersionUID = -6424334161582552937L;

	@Override
	public int compare(Word o1, Word o2) {
		return -(o1.getFrequency() - o2.getFrequency());

	}

}

class CompareByWord implements Comparator<Word>, Serializable {

	private static final long serialVersionUID = 1933053173504791546L;

	@Override
	public int compare(Word o1, Word o2) {
		return o1.getWord().compareTo(o2.getWord());
	}

}