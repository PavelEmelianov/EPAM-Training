package ua.nure.emelianov.SummatyTask3.entity;

import java.util.ArrayList;
import java.util.List;

public class Part {

	private String name;
	private String origin;
	private int price;
	private boolean critical;

	public boolean isCritical() {
		return critical;
	}

	public void setCritical(boolean critical) {
		this.critical = critical;
	}

	private List<Type> types;

	public List<Type> getTypes() {
		if (types == null) {
			types = new ArrayList<Type>();
		}
		return types;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName() + System.lineSeparator());
		sb.append(getOrigin() + System.lineSeparator());
		sb.append(getPrice() + System.lineSeparator());
		for (Type type : types) {
			sb.append(type + System.lineSeparator());
		}

		return sb.toString();

	}
}
