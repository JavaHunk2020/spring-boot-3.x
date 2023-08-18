package com.kuebiko;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MochaServiceTest {
	
	@Mock
	private MathUtil mathUtil; //fake object
	
	//MochaService has fake/mock instance of  MathUtil
	@InjectMocks
	MochaService mochaService=new MochaService(); //MochaService is actual service
	
	@Test
	public void testComputeWhenInputIsTwo() {
		mochaService.setNum(2);
		when(mathUtil.findRandNum()).thenReturn(100);
		int result=mochaService.compute();
		Assertions.assertEquals(113, result);
		verify(mathUtil, times(1)).findRandNum();
		verifyNoMoreInteractions(mathUtil);
	}
	
	@Test
	public void testComputeWhenInputMinusTwo() {
		mochaService.setNum(-2);
		//when(mathUtil.findRandNum()).thenReturn(100);
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> mochaService.compute());
		Assertions.assertEquals("Hey! num cannnot be negative", exception.getMessage());
		verify(mathUtil, times(0)).findRandNum();
		verifyNoMoreInteractions(mathUtil);
	}
	
	@Test
	public void testComputeWhenInputMinusFour() {
		mochaService.setNum(-4);
		//when(mathUtil.findRandNum()).thenReturn(100);
		int result=mochaService.compute();
		Assertions.assertEquals(-4, result);
		verify(mathUtil, times(0)).findRandNum();
		verifyNoMoreInteractions(mathUtil);
	}

}
