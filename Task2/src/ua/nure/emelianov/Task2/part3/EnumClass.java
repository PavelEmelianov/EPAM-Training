package ua.nure.emelianov.Task2.part3;

public class EnumClass {

	EnumClass(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}

	public static final EnumClass A;
	public static final EnumClass B;
	public static final EnumClass C;

	private static int count;

	static {
		A = new EnumClass("A", count++);
		B = new EnumClass("B", count++);
		C = new EnumClass("C", count++);
	}

	private String name;
	private int ordinal;

	public static EnumClass[] values() {

		EnumClass[] arr = new EnumClass[count];
		arr[A.ordinal] = A;
		arr[B.ordinal] = B;
		arr[C.ordinal] = C;

		return arr;
	}

	public static EnumClass valueOf(String constantName) {

		for (int i = 0; i < count; i++) {
			if (values()[i].name().equals(constantName)) {
				return values()[i];
			}
		}
		return null;
	}

	public int ordinal() {
		return this.ordinal;
	}

	public String name() {
		return this.name;
	}

	public String toString() {
		return name();
	}

}