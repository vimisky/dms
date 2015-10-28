package com.vimisky.dms.utils;

import java.util.Date;

public class VimiskyUtils {

	public static String getRandomCode(){
		StringBuffer randomChar = new StringBuffer(5);
		for (int i = 0; i < randomChar.capacity(); i++) {
			randomChar.append(String.valueOf((char)(Math.round(Math.random()*(new Date().getTime()*10000)%25+65))));
		}
		return randomChar.toString();
	}
}
