package ua.nure.emelianov.Task2.part3;

public class Demo3 {

	public static void main(String[] args) {
		for (EnumClass element : EnumClass.values()) {
			System.out.print(element.ordinal());
			System.out.print(element);
			System.out.println(EnumClass.valueOf(element.name()));
		}
	}
}
