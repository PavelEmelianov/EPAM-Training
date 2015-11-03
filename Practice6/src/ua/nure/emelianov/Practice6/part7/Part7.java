package ua.nure.emelianov.Practice6.part7;

public class Part7 {

	private static boolean local = false;
	private static final int START = 1;
	private static final int END = 10;

	public static boolean isLocal() {
		return local;
	}

	public static void setLocal(boolean local) {
		Part7.local = local;
	}

	public static void main(String[] args) {
		System.out.println("Reverse ==> " + local);
		Range range = new Range(START, END, isLocal());
		range.output();
		setLocal(true);
		System.out.println("\nReverse ==> " + isLocal());
		range = new Range(START, END, local);
		range.output();
	}

}
