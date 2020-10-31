package com.companyName.ProjectName.TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.companyName.ProjectName.TestBase.TestBase;
import com.companyName.ProjectName.pages.HomePage;
import com.companyName.ProjectName.utilities.CommonMethods;

public class TC02_enterPersonalInformationDetails extends TestBase { // Inheritance --OOPs Java

	@BeforeClass
	public void isSkip() {
		// if Run_mode is Y or N
		testCaseName = getClass().getSimpleName();
		if (!runModeHT.get(testCaseName).equalsIgnoreCase("Y"))
			throw new SkipException("Skipping Test case as it's Run Mode is set to N");
	}

	@Test(dataProvider = "Data_Collections")
	public void creatAnAccountNewCustomer(Hashtable<String, String> testData) throws InterruptedException, IOException {

		Assert.assertEquals(CommonMethods.getpageTitle(), testData.get("Page_Title"));
		CommonMethods.clickOnWebElementWithLinkText("sign_In", "Sign In link");
		Assert.assertFalse(!CommonMethods.isDisplyed("CreateAnAccntText", "Create An Account Text"));
		CommonMethods.enterTextInTextBox("Email_address", "Email_ID", testData);
		CommonMethods.clickOnWebElement("CreateAccntBtn", "Create an account button");

		// Personal Information page actions
		
		HomePage.createAnAccountNewUsers(testData);
		
		
		// 20 - 25 Manual Test Case -- Min 20 -25
		// 40 -50 End to End test cases -n verification points steps
		// 3-4 // Confirmation MESSAGE , Download policy Document 
	}

}
