package ua.nure.emelianov.Practice5.part5;

public class Part5 {

	private static final String EXC = "Thread: "
			+ Thread.currentThread().getName()
			+ " is interrupted in Part5.main";

	public static void main(String[] args) {
		System.out.println("============Part51");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			System.out.println(EXC);
		}
		Part51.main(args);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}

		System.out.println("============Part52");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			System.err.println(EXC);
		}
		Part52.main(args);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}

		System.out.println("============Part53");
		Part53.main(args);
	}
}
