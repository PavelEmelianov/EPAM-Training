package ua.nure.emelianov.SummatyTask3.entity;

import java.util.ArrayList;
import java.util.List;

public class Type {

	private boolean peripheral;
	private int energyConsumption;
	private boolean coolerAvailability;
	private String group;

	private List<Port> ports;

	public List<Port> getPorts() {
		if (ports == null) {
			ports = new ArrayList<Port>();
		}
		return ports;
	}

	public boolean isPeripheral() {
		return peripheral;
	}

	public void setPeripheral(boolean peripheral) {
		this.peripheral = peripheral;
	}

	public int getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(int energyConsumption) {
		this.energyConsumption = energyConsumption;
	}

	public boolean isCoolerAvailability() {
		return coolerAvailability;
	}

	public void setCoolerAvailability(boolean coolerAvailability) {
		this.coolerAvailability = coolerAvailability;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isPeripheral() + System.lineSeparator());
		sb.append(getEnergyConsumption() + System.lineSeparator());
		sb.append(isCoolerAvailability() + System.lineSeparator());
		sb.append(getGroup() + System.lineSeparator());
		for (Port port : ports) {
			sb.append(port + System.lineSeparator());
		}
		return sb.toString();
	}

}
