package com.examples.webdrivers;

import net.bytebuddy.implementation.bind.annotation.Super;

public class Test extends GoogleSearchTest{
	public void add() {
		super.add();
		System.out.println("Inside add function of Test class");
	}
	
public static void main(String[] args) {
	Test test = new Test();
	test.add();
	test.show();
	//Test.add();
	
	
}
}
