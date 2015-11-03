package ua.nure.emelianov.SummaryTask2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * class-container, that contains text elements in aggregated container and
 * operations with this container
 * 
 *
 */

public class Text {

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fILENAME) {
		fileName = fILENAME;
	}

	private StringBuilder sb = new StringBuilder();

	private Map<Sentence, List<Word>> container = new HashMap<>();

	/***
	 * this method reads the file and adds the elements to the container
	 */

	public void fillContainer() {
		Scanner sc;
		try {
			sc = new Scanner(new File(getFileName()), "Cp1251");
			while (sc.hasNext()) {
				sb.append(sc.next() + " ");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("file " + getFileName());
		}

		Pattern p = Pattern.compile("\\p{javaUpperCase}.*?\\.");
		Matcher m = p.matcher(sb);
		while (m.find()) {
			String group = m.group();
			Sentence sentence = new Sentence(group);
			container.put(sentence, new ArrayList<Word>());
			String[] words = m.group().replaceAll("[\\p{Punct}|\\—]", "")
					.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				Word word = new Word(words[i]);
				List<Word> list = container.get(sentence);
				list.add(word);
			}
		}
	}

	public Map<Sentence, List<Word>> getContainer() {
		return container;
	}

	/***
	 * this method checks does current sentence has the same word(words) (that
	 * sentence contains) in other sentences, if true ==> inner field
	 * "equalWords" in Sentence object sets to true, that indicates, that
	 * current sentence has equal word(words) in other sentences
	 * 
	 * @return the number of sentences, which have equal words in other
	 *         sentences
	 */

	public int comparison() {

		int counter = 0;

		for (Entry<Sentence, List<Word>> entry : container.entrySet()) {

			boolean flag = false;

			Sentence sentence = entry.getKey();
			List<Word> list = entry.getValue();

			for (Entry<Sentence, List<Word>> innerEntry : container.entrySet()) {

				if (innerEntry.getKey().getSentence()
						.equals(sentence.getSentence())) {
					continue;
				}

				for (int i = 0; i < innerEntry.getValue().size(); i++) {
					for (int j = 0; j < list.size(); j++) {

						if (list.get(j).getWord()
								.equals(innerEntry.getValue().get(i).getWord())) {

							innerEntry.getKey().setEqualWords(true);
							flag = true;
							break;
						}
					}

				}
			}
			if (flag) {
				sentence.setEqualWords(true);
			}
		}

		for (Entry<Sentence, List<Word>> entry : container.entrySet()) {
			if (entry.getKey().isEqualWords()) {
				counter++;
			}
		}
		return counter;
	}

	/***
	 * String representation of this object
	 * 
	 */

	public String toString() {

		int counter = 0;

		int value = comparison();

		StringBuilder strBuild = new StringBuilder();
		strBuild.append("SENTENCES, WHICH HAVE THE SAME WORDS IN OTHER SENTENCES: "
				+ System.lineSeparator());
		strBuild.append(System.lineSeparator());
		for (Entry<Sentence, List<Word>> entry : container.entrySet()) {
			if (entry.getKey().isEqualWords()) {
				strBuild.append(entry.getKey().getSentence()
						+ System.lineSeparator());

			}
		}
		strBuild.append(System.lineSeparator());
		strBuild.append("SENTENCES, WHICH DO NOT HAVE THE SAME WORDS IN OOTHER SENTENCES: "
				+ System.lineSeparator());
		strBuild.append(System.lineSeparator());
		for (Entry<Sentence, List<Word>> entry : container.entrySet()) {
			if (!entry.getKey().isEqualWords()) {
				strBuild.append(entry.getKey().getSentence()
						+ System.lineSeparator());

			}
		}
		strBuild.append(System.lineSeparator());

		for (Entry<Sentence, List<Word>> entry : container.entrySet()) {
			counter++;
		}

		strBuild.append(System.lineSeparator());
		strBuild.append("TOTAL SENTENCES: " + counter + System.lineSeparator());
		strBuild.append(System.lineSeparator());

		strBuild.append("THE NUMBER OF SENTENCES, WHICH HAVE THE SAME WORDS IN OTHER SENTENCES: "
				+ value);

		return strBuild.toString();
	}

	public static void main(String[] args) {
		Text text = new Text();
		text.setFileName(args[0]);
		text.fillContainer();
		System.out.println(text);

	}
}
