package com.companyName.ProjectName.polymorphism;

public class MethodOverRidding { // Run time, Dynamic Binding, Method Overriding 

	public static void main(String[] args) {
		
	RBI_Bank sbi = new SBI();
	//SBI sbi = new SBI();
	//System.out.println(sbi.getRateOfInterest());
	System.out.println(sbi.getRateOfInterest());
	
	RBI_Bank hdfc = new HDFC();
	
	System.out.println(hdfc.getRateOfInterest());
	
	RBI_Bank icici = new ICICI();
	
	System.out.println(icici.getRateOfInterest());
	
	}

}
