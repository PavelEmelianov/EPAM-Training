package ua.nure.emelianov.Practice5.part2;

import java.io.IOException;

public class Spam extends Thread {

	private static boolean flag;

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		Spam.flag = flag;
	}

	private static int[] time = new int[] { 1000, 1000, 1000, 1000, 1000, 1000 };
	private static String[] messages = new String[] { "message1", "message2",
			"message3", "message4", "message5", "message6" };

	public void run() {
		while (true) {
			try {
				for (int i = 0; i < messages.length; i++) {
					System.out.println(messages[i]);
					Thread.sleep(time[i]);
					if (flag) {
						break;
					}
				}
				break;
			} catch (Exception ex) {
				System.err.println("Thread:" + Thread.currentThread().getName()
						+ " is interrupted");
			}
		}
	}

	public static void main(String[] args) {
		final Spam t = new Spam();
		t.setDaemon(true);
		t.start();

		new Thread() {
			public void run() {
				byte[] buffer = new byte[10];
				int count;
				try {
					do {

						while ((count = System.in.read(buffer)) == -1) {
							if (count == -1) {
								continue;
							}
						}
					} while (!System.lineSeparator().equals(
							new String(buffer, 0, count, "cp1251")));
				} catch (IOException ex) {
					System.err.println("Spam IOexception in method main");
				}
				System.out.println("ENTER has been obtained");
			}
		}.start();
	}
}
