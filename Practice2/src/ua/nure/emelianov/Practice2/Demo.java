package ua.nure.emelianov.Practice2;

import java.util.Iterator;

public class Demo {

	public static void main(String[] args) {
		String arrow =" ==> ";
		MyListImpl test = new MyListImpl();
		test.add("AAA");
		test.add(111);
		test.add("BBB");
		test.add(333);
		test.add("CCC");
		test.add("FFF");
		System.out.println("Objects added to the array: " + test + ", size:"
				+ test.size());
		test.remove("BBB");
		System.out.println("Object \"BBB\" removed: " + test);

		MyListImpl test2 = new MyListImpl();
		test2.add(111);
		test2.add("AAA");
		test2.add("FFF");

		String first = "CCC";
		String second = "ZZZ";

		System.out.println("Method \"contains\" demonstration: " + first
				+ arrow + test.contains(first) + " " + second + arrow
				+ test.contains(second));
		System.out.println("Method \"containsAll\" demonstration: " + test2
				+ arrow + test.containsAll(test2));
		test2.remove("FFF");
		test2.add(222);
		System.out.println("Method \"containsAll\" demonstration: " + test2
				+ arrow + test.containsAll(test2));
		test.clear();
		System.out.println("Array cleared: " + test + "\n");
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		System.out.println("Iterator demonstration:");
		for (Object o : test) {
			System.out.println(o);
		}
		System.out.println();
		Iterator<Object> it = test.iterator();
		//it.remove(); //(remove() before next()) uncomment this to receive an exception
		while (it.hasNext()){
			System.out.println(it.next());}
		System.out.println("remove() demonstration: \"6\" removed");
		it.remove();
		//it.remove(); //(remove() after remove() uncomment this to receive an exception
		System.out.println();
		System.out.println("ListIterator demonstration (without removed\"6\"):");
		ListIterator li = test.listIterator();
		// li.remove(); //(remove() before next() or previous() uncomment this to receive an exception
		while (li.hasNext()){
			System.out.println(li.next());}
		li.set("HELLO");
		System.out
				.println("\"set\" method demonstration (\"5\" replaced with \"HELLO\"): ");
		while (li.hasPrevious()){
			System.out.println(li.previous());}
		li.set("WORLD");
		// li.set("123"); //(set() after set()) uncomment this to receive an exception
		System.out
				.println("\"set\" method demonstration (\"1\" replaced with \"WORLD\"): ");
		while (li.hasNext()){
			System.out.println(li.next());}
		li.remove();
		// li.remove(); //(remove() after remove()) uncomment this to receive an exception
		System.out
				.println("\"remove\" method demonstration (\"HELLO\" removed): ");
		while (li.hasPrevious()){
			System.out.println(li.previous());}
		System.out
				.println("\"remove\" method demonstration (\"WORLD\" removed): ");
		li.remove();
		while (li.hasNext()){
			System.out.println(li.next());}
	}
}
