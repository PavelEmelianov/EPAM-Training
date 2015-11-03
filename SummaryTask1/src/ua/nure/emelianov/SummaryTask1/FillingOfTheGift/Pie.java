package ua.nure.emelianov.SummaryTask1.FillingOfTheGift;

/**
 * This class is the realization of the abstract class AbstractSweets
 */

public class Pie extends AbstractSweets {

	/**
	 * 
	 * @param name
	 *            name
	 * @param sugarValue
	 *            sugar value
	 * @param weight
	 *            weight
	 */

	public Pie(String name, int sugarValue, int weight) {
		super(sugarValue, weight);
		setName(name);
	}

}
