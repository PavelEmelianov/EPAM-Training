package ua.nure.emelianov.SummaryTask4;

import java.io.Serializable;
import java.util.Comparator;

import ua.nure.emelianov.SummaryTask4.db.bean.UsersTests;
import ua.nure.emelianov.SummaryTask4.db.entity.Tests;


/**
 * Class contains functionality for object comparison
 * @author Emelianov Pavel
 *
 */
public class Comparators {
	
	/**
	 * Comparator that compares objects by name value
	 * @author Emelianov Pavel
	 *
	 */

	public static class CompareByName implements Comparator<Tests>, Serializable {

		private static final long serialVersionUID = -2382790006724690579L;

		@Override
		public int compare(Tests t1, Tests t2) {
			return t1.getName().compareTo(t2.getName());
		}

	}
	
	/**
	 * Comparator that compares objects by test id value
	 * @author Emelianov Pavel
	 *
	 */
public static class CompareByUsersTestsId implements Comparator<UsersTests> , Serializable{

	private static final long serialVersionUID = 8952104900287006111L;

	@Override
	public int compare(UsersTests ut1, UsersTests ut2) {
		
		return ut2.getId()-ut1.getId();
	}
	
}

/**
 * Comparator that compares objects by difficulty value
 * @author Emelianov Pavel
 *
 */
	public static class CompareByDifficulty implements Comparator<Tests>, Serializable {

		private static final long serialVersionUID = -2073593354243486823L;

		@Override
		public int compare(Tests t1, Tests t2) {

			String diff1 = t1.getDifficulty();
			String diff2 = t2.getDifficulty();

			int temp1 = 0;
			int temp2 = 0;

			if (diff1.equals("elementary")) {
				temp1 = 1;
			} else if (diff1.equals("advanced")) {
				temp1 = 2;
			} else if (diff1.equals("proficient")) {
				temp1 = 3;
			}

			if (diff2.equals("elementary")) {
				temp2 = 1;
			} else if (diff2.equals("advanced")) {
				temp2 = 2;
			} else if (diff2.equals("proficient")) {
				temp2 = 3;
			}
			
			return temp1-temp2;
		}

	}
	
	/**
	 * Comparator that compares object by question count value
	 * @author Emelianov Pavel
	 *
	 */

	public static class CompareByQuestionsCount implements Comparator<Tests>, Serializable {
	
		private static final long serialVersionUID = 376967487844228899L;

		@Override
		public int compare(Tests t1, Tests t2) {
			return t1.getQuestionsCount() - t2.getQuestionsCount();
		}

	}

}
