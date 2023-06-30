package com.kuebiko.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author nagendra kumar
 *
 */
public class CreditCardUtils {

	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	public static long genRandNum() {
		return new Random(1000000000).nextLong();
	}

}
