package ua.nure.emelianov.SummaryTask3.constants;

/**
 * This classHolds entities declared in XSD document.
 *
 */

public enum XML {

	// elements names
	DEVICE("Device"), PART("Part"), NAME("Name"), ORIGIN("Origin"), PRICE(
			"Price"), TYPE("Type"), PERIPHERAL("Peripheral"), ENERGYCONSUMPTION(
			"EnergyConsumption"), COOLERAVAILABILITY("CoolerAvailability"), GROUP(
			"Group"), PORT("Ports"), COM("COM"), USB("USB"), LPT("LPT"),

	// attribute name
	CRITICAL("critical");

	private String value;

	XML(String value) {
		this.value = value;
	}

	/**
	 * Determines if a name is equal to the string value wrapped by this enum
	 * element.<br/>
	 * If a SAX/StAX parser make all names of elements and attributes interned
	 * you can use
	 * 
	 * <pre>
	 * return value == name;
	 * </pre>
	 * 
	 * instead
	 * 
	 * <pre>
	 * return value.equals(name);
	 * </pre>
	 * 
	 * @param name
	 *            string to compare with value.
	 * @return value.equals(name)
	 */
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
