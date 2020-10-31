package com.companyName.ProjectName.polymorphism;

public class MethodOveloading {

	public static void main(String[] args) { // Compile time, Static Binding, Method Overloading 
		
		addition(10,20); // 1
		addition("Rahul", 5); //4
		
		addition(10,20, "two"); //2		
		addition(10, 20, 20.5, "three"); //3
			
	}

	public static void addition(int a, int b) {

		System.out.println("Addition of two number:-" + (a + b));

	}

	public static void addition(int a, int b, String c) {

		System.out.println("Addition of " + c + " numbers:-" + (a + b));

	}

	public static void addition(int a, int b, double e, String d) {

		System.out.println("Addition of " + d + " number:-" + (a + b + e));

	}

	public static void addition(String a, int b) {

		System.out.println("Same method name & parameters but different Data Type:-" + a);

	}

}
