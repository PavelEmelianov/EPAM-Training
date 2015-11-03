package ua.nure.emelianov.Practice5.part3;

public class Part3 {

	private int firstCounter;

	public int getFirstCounter() {
		return firstCounter;
	}

	public void setFirstCounter(int counter1) {
		this.firstCounter = counter1;
	}

	private int secondCounter;

	public int getSecondCounter() {
		return secondCounter;
	}

	public void setSecondCounter(int counter2) {
		this.secondCounter = counter2;
	}

	public void output() {

		setFirstCounter(getFirstCounter() + 1);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.err.println("Thread:" + Thread.currentThread().getName()
					+ " is interrupted in Part3.output()");
		}
		setSecondCounter(getSecondCounter() + 1);
		System.out.print(firstCounter - secondCounter);
	}

	public synchronized void synchOutput() {

		setFirstCounter(getFirstCounter() + 1);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.err.println("Thread:" + Thread.currentThread().getName()
					+ " is interrupted in method Part3.synchOutput()");
		}
		setSecondCounter(getSecondCounter() + 1);
		System.out.print(firstCounter - secondCounter);
	}

	public static void result() {
		final Part3 obj = new Part3();
		System.out.println("Synchronized output: ");
		for (int i = 0; i < 10; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.synchOutput();
					System.out.println();
				};
			};
			myThread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println("Thread:" + Thread.currentThread().getName()
					+ " is interrupted in method result()");
		}
		System.out.println("NOT synchronized output: ");
		for (int i = 0; i < 10; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.output();
					System.out.println();
				};
			};
			myThread.start();
		}
	}

	public static void main(String[] args) {
		result();
	}

}