package ua.nure.your_last_name.SummaryTask3.entity;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * Object factory for JAXB objects.<br/>
 * This class is used by JAXB marshal/unmarshal process.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Test createTest() {
		return new Test();
	}

	public Question createQuestion() {
		return new Question();
	}

	public Answer createAnswer() {
		return new Answer();
	}

}
