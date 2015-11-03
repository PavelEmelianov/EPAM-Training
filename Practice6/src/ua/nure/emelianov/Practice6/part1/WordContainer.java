package ua.nure.emelianov.Practice6.part1;

import java.util.ArrayList;
import java.util.List;

public class WordContainer {

	private List<Word> arr = new ArrayList<Word>();

	public List<Word> getArr() {
		return arr;
	}

	public void add(Word word) {
		for (Word w : arr) {
			if (w.getWord().equals(word.getWord())) {
				w.setFrequency(w.getFrequency() + 1);
				return;
			}
		}
		arr.add(word);

	}

	public static void main(String[] args) {

	}

}
