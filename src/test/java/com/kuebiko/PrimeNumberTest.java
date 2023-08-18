package com.kuebiko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimeNumberTest {

	@Test
	void testIsPrimeWhenNumIs2() {
		PrimeNumber primeNumber=new PrimeNumber();
		boolean result=primeNumber.isPrime(2);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	void testIsPrimeWhenNumIs3() {
		PrimeNumber primeNumber=new PrimeNumber();
		boolean result=primeNumber.isPrime(3);
		Assertions.assertEquals(true, result);
	}
	
	
	@Test
	void testIsPrimeWhenNumIs39() {
		PrimeNumber primeNumber=new PrimeNumber();
		boolean result=primeNumber.isPrime(39);
		Assertions.assertEquals(false, result);
	}
	
	@Test
	void testIsPrimeWhenNumI50() {
		PrimeNumber primeNumber=new PrimeNumber();
		boolean result=primeNumber.isPrime(50);
		Assertions.assertEquals(false, result);
	}
	
	@Test
	void testIsPrimeWhenNumIsOne() {
		PrimeNumber primeNumber=new PrimeNumber();
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> primeNumber.isPrime(1));
		Assertions.assertEquals("Sorry this is not valid", exception.getMessage());
	}
	
	@Test
	void testIsPrimeWhenNumIsNegative() {
		PrimeNumber primeNumber=new PrimeNumber();
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> primeNumber.isPrime(-1));
		Assertions.assertEquals("Sorry this is not valid", exception.getMessage());
	}

}
