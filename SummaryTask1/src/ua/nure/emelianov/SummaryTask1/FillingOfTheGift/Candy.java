package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

/**
 * This class is the realization of the abstract class AbstractSweets
 */

public class Candy extends AbstractSweets {

	/**
	 * 
	 * @param name
	 *            name
	 * @param sugarValue
	 *            sugar value
	 * @param weight
	 *            weight
	 */

	public Candy(String name, int sugarValue, int weight) {
		super(sugarValue, weight);
		setName(name);
	}

}
