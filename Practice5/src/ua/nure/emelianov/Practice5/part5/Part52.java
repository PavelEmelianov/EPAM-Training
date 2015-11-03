package ua.nure.emelianov.Practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Note!!! Without synchronization this application not work properly!! Most
 * likely a runtime exception will be thrown..
 * 
 */
public class Part52 {

	private static final String EXC = Thread.currentThread().getName()
			+ " is interrupted";

	private static final ReentrantLock LOCK1 = new ReentrantLock();
	private static final ReentrantLock LOCK2 = new ReentrantLock();

	private static final Condition CONDITION = LOCK2.newCondition();

	private static final int ITERATION_NUMBER = 3;

	private static final int READERS_NUMBER = 3;

	// shared resource (not thread-safe!!!)
	private static final StringBuilder BUFFER = new StringBuilder();

	private static final int BUFFER_LENGTH = 5;

	// speed parameter
	private static final int PAUSE = 5;

	// stop signal
	private static boolean stop;

	// reader
	private static class Reader extends Thread {
		private long l = 0;

		public long getL() {
			return l;
		}

		public void setL(long l) {
			this.l = l;
		}

		public void run() {
			while (!stop) {
				try {
					LOCK1.lock();
					try {
						read(getName());
					} finally {
						LOCK1.unlock();
					}
					LOCK2.lock();
					try {
						Thread.sleep(1000);
						CONDITION.signal();
						setL(CONDITION.awaitNanos(3000));
					} finally {
						LOCK2.unlock();
					}

				} catch (InterruptedException e) {
					System.err.println(EXC + getL());
				}
			}

		}
	}

	// writer
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					LOCK1.lock();
					try {
						write();
					} finally {
						LOCK1.unlock();
					}
					LOCK2.lock();
					try {
						CONDITION.signalAll();
						CONDITION.await();
					} finally {
						LOCK2.unlock();
					}

				} catch (InterruptedException e) {
					System.err.println(EXC);
				} finally {

					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	private static void read(String threadName) {

		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				System.err.println(EXC);
			}
			System.out.print(BUFFER.charAt(j));

		}
		System.out.println();

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}
	}

	private static void write() {

		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				System.err.println(EXC);
			}
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFFER.append(ch);
		}

		System.err.println();

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}
	}

	public static void main(String[] args) {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start writer
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}
		writer.start();

		// start readers
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}
		for (Thread reader : readers) {
			reader.start();
		}

		// main thread is waiting for the child threads
		try {
			writer.join();
		} catch (InterruptedException e) {
			System.err.println(EXC);
		}
		for (Thread reader : readers) {
			try {
				reader.join();
			} catch (InterruptedException e) {
				System.err.println(EXC);
			}
		}
	}

}