package ua.nure.emelianov.Practice5.part1;

public class Part1 {

	public static void main(String[] args) {
		Thread myThread = new MyThread();
		myThread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName()
					+ " is interrupted in Part1.main()");
		}
		Thread myThread2 = new Thread(new MyThread2());
		myThread2.start();
	}
}

class MyThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " Emelianov Pavel");
			try {
				sleep(750);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()
						+ " is interrupted");
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " Emelianov Pavel");
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()
						+ " is interrupted");
			}
		}

	}

}
