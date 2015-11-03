package ua.nure.emelianov.SummatyTask3.entity;

public class Port {

	private boolean com;
	private boolean usb;
	private boolean lpt;

	public boolean isCom() {
		return com;
	}

	public void setCom(boolean com) {
		this.com = com;
	}

	public boolean isUsb() {
		return usb;
	}

	public void setUsb(boolean usb) {
		this.usb = usb;
	}

	public boolean isLpt() {
		return lpt;
	}

	public void setLpt(boolean lpt) {
		this.lpt = lpt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(isCom() + System.lineSeparator());
		sb.append(isUsb() + System.lineSeparator());
		sb.append(isLpt() + System.lineSeparator());
		return sb.toString();
	}

}
