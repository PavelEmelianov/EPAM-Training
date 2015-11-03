package ua.nure.emelianov.Practice3;

import java.security.NoSuchAlgorithmException;

import ua.nure.emelianov.Practice3.part1.Part1;
import ua.nure.emelianov.Practice3.part2.Part2;
import ua.nure.emelianov.Practice3.part3.Part3;
import ua.nure.emelianov.Practice3.part4.Part4;
import ua.nure.emelianov.Practice3.part5.Part5;

public class Demo {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Part1.main(args);
		Part2.main(args);
		Part3.main(args);
		Part4.main(new String[] {"password", "SHA-256"});
		Part4.main(new String[] {"password", "MD5"});
		Part5.main(args);

	}

}
