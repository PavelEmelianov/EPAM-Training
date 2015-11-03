package ua.nure.emelianov.SummaryTask4;


import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.emelianov.SummaryTask4.db.DBManager;
import ua.nure.emelianov.SummaryTask4.exception.DBException;


public class DBManagerTest {
	
	static DBManager db;
		
	@BeforeClass
	public static void constructorTest(){
		db= DBManager.getInstance();
	}
	
	@Test
	public void createAnswerName()  {
		try {
			db.createAnswer("Answer", 1);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
