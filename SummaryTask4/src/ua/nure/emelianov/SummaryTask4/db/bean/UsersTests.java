package ua.nure.emelianov.SummaryTask4.db.bean;

import java.io.Serializable;

/**
 * UsersTests bean
 * @author Emelianov Pavel
 *
 */

public class UsersTests implements Serializable {

	private static final long serialVersionUID = 1541536137020432784L;

	private int id;
	private String fName;
	private String lName;
	private String themeName;
	private String testName;
	private String testDifficulty;
	private String date;
	private int result;
	
	
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getTestDifficulty() {
		return testDifficulty;
	}
	public void setTestDifficulty(String testDifficulty) {
		this.testDifficulty = testDifficulty;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public String getTestName() {
		return testName;
	}
	public void setTestName(String testname) {
		this.testName = testname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "UsersTests [fName=" + fName + ", lName=" + lName
				+ ", testName=" + testName + ", date=" + date + ", result="
				+ result + "]";
	}


}
