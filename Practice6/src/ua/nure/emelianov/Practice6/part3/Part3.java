package ua.nure.emelianov.Practice6.part3;

public class Part3 {

	public static void main(String[] args) {
		Parking parking = new Parking(10);
		parking.add(5);
		parking.add(7);
		parking.add(8);
		parking.add(9);
		for (int i = 0; i < parking.getArr2().length; i++) {
			if (parking.getArr2()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArr2()[i] + "]");
			}
		}
		System.out.println();
		parking.remove(7);
		for (int i = 0; i < parking.getArr2().length; i++) {
			if (parking.getArr2()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArr2()[i] + "]");
			}
		}
		System.out.println();
		parking.add(6);
		for (int i = 0; i < parking.getArr2().length; i++) {
			if (parking.getArr2()[i] == null) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + parking.getArr2()[i] + "]");
			}
		}

	}

}
