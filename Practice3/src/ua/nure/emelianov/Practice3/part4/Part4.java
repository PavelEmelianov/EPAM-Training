package ua.nure.emelianov.Practice3.part4;

import java.nio.charset.Charset;
import java.security.*;

public class Part4 {
	public static String encoding(String name, String alghorythm)
			throws NoSuchAlgorithmException {

		StringBuilder sb = new StringBuilder();

		MessageDigest digest = MessageDigest.getInstance(alghorythm);
		 digest.update(name.getBytes(Charset.forName("UTF-8")));
		byte[] hash = digest.digest();
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] < 0) {
				String x = Integer.toHexString(hash[i]);
				String y = x.substring(x.length() - 2, x.length());
				sb.append(y + " ");
				continue;
			}
			sb.append(Integer.toHexString(hash[i]) + " ");
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(Part4.encoding(args[0], args[1])+System.lineSeparator());

	}
}
