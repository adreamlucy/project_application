package util;

public class radomtoken {
	public static String getRandom() {
		String token = "";
		char[] str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@+-*&^%#$@!1234567890".toCharArray();
		for(int i = 0 ; i<str.length; i++) {
			token += str[(int) (Math.random() * 73)];
		}
		return token;
	}
}
