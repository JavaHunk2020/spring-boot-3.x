package com.kuebiko;
public class MochaService  {
	
	private int num;
	
	private MathUtil mathUtil=new MathUtil();
	
	public void setNum(int num) {
		this.num = num;
	}

	public MochaService() {
		
	}
	
	public MochaService(int num) {
		super();
		this.num = num;
	}
	
	
	public int wow(int n) {
		return num-10+n;
	}
	
	//what input - -10, 0 , 6
	
	//we cannot write it for float
	public int compute() {
		
		if(num==-4) {
		   return num;	
		}
		if(num<0) {
		  throw new RuntimeException("Hey! num cannnot be negative");	
		}
//		/mathUtil is other service generating random numbers
	 	int rand=mathUtil.findRandNum(); // this should be random num
	    rand=rand+9+(num*num);
	    return rand;
	}

}
