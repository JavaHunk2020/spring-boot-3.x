package com.kuebiko;

import java.util.Random;

public class MathUtil {
	
	/**
	 * 0-999
	 * @return
	 */
	public int findRandNum() {
		  //0-999 - 996
		 return new Random().nextInt(1000)-3;
	}

}
