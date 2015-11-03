package ua.nure.your_last_name.Task1;

public class Task1 {

	private static void usage() {
		System.out.println("Usage: java "
				+ "ua.nure.your_last_name.Task1.Task1 X Y");
	}

	public static int nod(int x, int y) {
		while (x != y) {
			if (x > y)
				x -= y;
			else
				y -= x;
		}
		return x; // x == y --> true
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
			return;
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int nod = nod(x, y);
		System.out.println("NOD x=" + x + ", y=" + y + " --> " + nod);
	}
	
}