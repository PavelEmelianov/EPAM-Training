package ua.nure.emelianov.Practice5.part4;

import java.util.Arrays;
import java.util.Random;

public class Part4 {

	private static final Random RANDOM = new Random();
	private static final int SIZE = 4;
	private static int[][] arr = new int[SIZE][300];
	private static long counter = 0;

	public static void fillTheArrayWithNumbers() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = RANDOM.nextInt(999);
			}
		}
	}

	public static int maximumValue(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.err.println("Thread:" + Thread.currentThread().getName()
						+ " is interrupted in method maximumValue()");
			}
			if (arr[i] > max) {
				max = arr[i];
			}

		}
		return max;
	}

	public static int singleThread() {
		long time = System.currentTimeMillis();
		int max = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.err.println("Thread:"
							+ Thread.currentThread().getName()
							+ " is interrupted in method maximumValue()");
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time));
		return max;

	}

	public static int[] multiplyThreads() {
		final int[] temp = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {

			final int count = i;
			Thread myThread = new Thread() {
				public void run() {
					long time = System.currentTimeMillis();

					temp[count] = maximumValue(arr[count]);
					time = System.currentTimeMillis() - time;
					if (counter < time) {
						counter = time;
					}
				};
			};
			myThread.start();
		}
		return temp;
	}

	public static void main(String[] args) {
		fillTheArrayWithNumbers();
		System.out.println("Single Thread Demonstration: ");
		System.out.println("MaximumValue: " + singleThread());
		System.out.println("Multiply Thread Demonstration: ");
		int[] temp = multiplyThreads();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println("Thread:" + Thread.currentThread().getName()
					+ " is interrupted in method main()");
		}
		Arrays.sort(temp);
		System.out.println("Time: " + counter);
		System.out.println("Maximum value: " + temp[SIZE - 1]);
	}

}