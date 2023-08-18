package com.kuebiko;

public class PrimeNumber {
	
	 public boolean isPrime(int num) {
		 if(num <2)  {
			 throw new RuntimeException("Sorry this is not valid");
		 }
		 int  i  =2;
		 //17 - 8
		 while(i<=num/2) {   
			 if(num%i==0) {  //2
				  return false;
			 }
			 i++;
		 }
		 return true;
	 }

}
