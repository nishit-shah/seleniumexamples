package com.examples.selenium;

public class Car {
	
	//constructor declaration
	//Constructor name should always be a classname
	//Constructor is a special method which is executed whenever object of the class is created
	//Constructor does not return anything including void
	//constructor signature: Access modifier Classname(){}
	public Car() {
		System.out.println("This is a constructor");
	}
	
	
	//Method declaration
	public void test() {
		System.out.println("This is a test method");
	}
	
	public static void main(String[] args) {
		//Classname objname = new classname();
		Car carobj = new Car();
		System.out.println(carobj);
		carobj.test();
	}

}
