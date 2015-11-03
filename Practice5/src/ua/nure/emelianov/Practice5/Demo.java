package ua.nure.emelianov.Practice5;

import ua.nure.emelianov.Practice5.Part6.Part6;
import ua.nure.emelianov.Practice5.part1.Part1;
import ua.nure.emelianov.Practice5.part2.Part2;
import ua.nure.emelianov.Practice5.part3.Part3;
import ua.nure.emelianov.Practice5.part4.Part4;
import ua.nure.emelianov.Practice5.part5.Part5;

public class Demo {

	private static final String EXC = Thread.currentThread().getName()
			+ " is interrupted in Demo.main()";

	public static void main(String[] args) {

		System.out.println("~~~~~~~~~~~~Part1");
		Part1.main(args);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(EXC);
		}

		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			System.out.println(EXC);
		}

		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(EXC);
		}

		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(EXC);
		}

		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(EXC);
		}

		System.out.println("~~~~~~~~~~~~Part6");
		Part6.main(args);
	}

}