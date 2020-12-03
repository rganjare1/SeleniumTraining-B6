package com.companyName.ProjectName.Interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TryCatchBlock {
	
	@Test
	public static void tryCatchBlock() {
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\files\\config1.properties");
			Properties config = new Properties();
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			finally {				
				System.out.println("I am inside of Try Catch block.");
			}
			System.out.println("Config file has been loaded");
		
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Issue while loading the Config file:-" +e.getMessage());
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Issue while loading the Config file:-" +e.getMessage());
		}
		
		finally {
			
			System.out.println("I am executing every time.");
		}
  
		
	}
	
	//@Test
	public static void tryCatchBlock1() {
		try {
		Assert.assertEquals("Rahul", "Rahul1");		
		System.out.println("I am in Try Catch Block");
		
		} catch (Throwable t) {
			
			// secondary logic
			System.out.println(t.getMessage());
			//e.printStackTrace();		
			System.out.println("Assertion fail -- I am in  Catch Block");	
		}
		finally {
			System.out.println("I am executing every time");
		}
	}
	
}
	