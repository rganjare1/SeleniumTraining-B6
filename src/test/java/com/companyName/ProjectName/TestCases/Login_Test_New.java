package com.companyName.ProjectName.TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.companyName.ProjectName.TestBase.TestBaseNew;
import com.relevantcodes.extentreports.LogStatus;

public class Login_Test_New extends TestBaseNew { // Inheritance --OOPs Java
	
	@BeforeClass
	public void isSkip(){
		testCaseName = getClass().getSimpleName();
		if(!testCaseRunMode.get(testCaseName).equalsIgnoreCase("Y")) 
		throw new SkipException("Skipping Testcase as it's Run Mode is marked as N");
	}
	
	@Test(dataProvider = "Data_Collections")
	public void login_Test3(Hashtable <String, String> testData) throws InterruptedException, IOException {
		
		//String act_title = driver.getTitle();
		
	     Assert.assertEquals(driver.getTitle(), testData.get("Page_Title") );	
		//Assert.assertEquals(act_title, "My Stores", "User failed navigated to the My Store Application.");		
		test.log(LogStatus.PASS, "User navigated to the My Store Application.");
		takeScreenShot_Pass();
		
		driver.findElement(By.linkText(OR.getProperty("sign_In"))).click();
		//System.out.println("User clicked on sign_In link");
		test.log(LogStatus.PASS, "User clicked on sign_In link");
		Thread.sleep(2000);
		
		boolean text = driver.findElement(By.xpath(OR.getProperty("CreateAnAccntText"))).isDisplayed();
		//Assert.assertEquals(text, true);	
		Assert.assertFalse(!text); // false
		test.log(LogStatus.PASS, "Create An account section has been displayed on My Store Application.");
		takeScreenShot_Pass();
		
		driver.findElement(By.xpath(OR.getProperty("Email_address"))).sendKeys(testData.get("Email_ID"));
		//System.out.println("User entered Email Address");
		test.log(LogStatus.PASS, "User entered Email Address");
		Thread.sleep(2000);
		takeScreenShot_Pass();
		
		boolean CreateAccntBtn = driver.findElement(By.xpath(OR.getProperty("CreateAccntBtn"))).isEnabled();
		Assert.assertFalse(!CreateAccntBtn);
		test.log(LogStatus.PASS, "Create An Account button is enabled.");
		
		driver.findElement(By.xpath(OR.getProperty("CreateAccntBtn"))).click();
		//System.out.println("User clicked on CreateAccntBtn");	
		test.log(LogStatus.PASS , "User clicked on CreateAccntBtn");
		takeScreenShot_Pass();
		//takeScreenShot_Fail();
		
	}
	
}
