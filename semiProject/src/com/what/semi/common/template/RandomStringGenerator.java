package com.what.semi.common.template;

import java.util.Random;

public class RandomStringGenerator {
	public String gen() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 8; i++) {
		    int rIndex = rnd.nextInt(2);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		String rndStr = temp.toString();
		return rndStr;
	}
}
