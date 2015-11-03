package ua.nure.emelianov.SummatyTask3.entity;

import java.util.ArrayList;
import java.util.List;

public class Device {

	private List<Part> parts;

	public List<Part> getParts() {
		if (parts == null) {
			parts = new ArrayList<Part>();
		}
		return parts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Part part : parts) {
			sb.append(part + System.lineSeparator());
		}
		return sb.toString();
	}
}
