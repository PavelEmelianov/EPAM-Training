package ua.nure.emelianov.Task2.part2;

public enum EnumType {

	A, B, C(7);

	EnumType() {

	}

	EnumType(int value) {
		this.value = value;
		flag = true;
	}

	private int value;

	private boolean flag = false;

	public String hasValue(boolean flag) {
		if (flag) {
			return ", x = " + value;
		} else {
			return "";
		}
	}

	public String toString() {

		return "element " + this.name() + hasValue(this.flag);

	}

}
