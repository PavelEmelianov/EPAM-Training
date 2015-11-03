package ua.nure.emelianov.Task2.part1;

public enum Type {

	INT, STRING;

	public boolean isPrimitive() {

		switch (this) {
		case INT:
			return true;
		default:
			return false;
		}
	}

	public static String list() {

		StringBuilder sb = new StringBuilder();
		Type[] types = Type.values();
		for (int i = 0; i < types.length; i++) {

			if (types[i].toString().equals(INT.toString())) {
				sb.append("int ");
			}
			if (types[i].toString().equals(STRING.toString())) {
				sb.append("string ");
			}
		}
		return sb.toString().substring(0, sb.length() - 1);

	}

	public String toString() {

		switch (this) {
		case INT:
			return "primitive type int";
		case STRING:
			return "reference type string";
		}
		return "Error in toString() implementation";
	}
}
