package com.companyName.ProjectName.pages;

import java.util.Hashtable;

import org.testng.Assert;

import com.companyName.ProjectName.TestBase.TestBase;
import com.companyName.ProjectName.utilities.CommonMethods;

public class HomePage extends TestBase {

	public static void createAnAccountNewUsers(Hashtable<String, String> testData) {
		
		Assert.assertEquals(CommonMethods.getpageTitle(), testData.get("Page_Title"));	
		CommonMethods.clickOnWebElementWithLinkText("sign_In", "Sign In link");
		Assert.assertFalse(!CommonMethods.isDisplyed("CreateAnAccntText", "Create An Account Text"));
		CommonMethods.enterTextInTextBox("Email_address", "Email_ID", testData);
		CommonMethods.clickOnWebElement("CreateAccntBtn", "Create an account button");

	}

	public static void loginWithAlreadyRegisteredUsers(Hashtable<String, String> testData) {
		
		Assert.assertEquals(CommonMethods.getpageTitle(), testData.get("Page_Title"));
		CommonMethods.clickOnWebElementWithLinkText("sign_In", "Sign In link");
		Assert.assertFalse(!CommonMethods.isDisplyed("XalreadyRegText", "ALREADY REGISTERED Text"));
		CommonMethods.enterTextInTextBox("XARemail", "Email_ID", testData);
		CommonMethods.enterTextInTextBox("XARpwd", "Password", testData);
		CommonMethods.clickOnWebElement("XARSubmitBtn", "Sign In button");
	}

	public static void contactUsDetails() {

	}

	public static void cart() {

	}

}
