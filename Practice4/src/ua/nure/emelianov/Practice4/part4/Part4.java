package ua.nure.emelianov.Practice4.part4;

import java.io.File;

import ua.nure.emelianov.Practice4.part4.Parser;

public class Part4 {

	Part4(String filename) {
		setFileName(filename);
	}

	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output() {
		Parser parser = new Parser(new File(getFileName()));
		for (String str : parser) {
			System.out.println(str);
		}

	}

	public static void main(String[] args) {
		new Part4("part4.txt").output();
	}
}