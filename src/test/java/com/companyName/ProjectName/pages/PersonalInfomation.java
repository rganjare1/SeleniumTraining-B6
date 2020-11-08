package com.companyName.ProjectName.pages;

import java.util.Hashtable;

import org.testng.Assert;

import com.companyName.ProjectName.TestBase.TestBase;
import com.companyName.ProjectName.utilities.CommonMethods;

public class PersonalInfomation extends TestBase {

	public static void enterPersonalInformation(Hashtable<String, String> testData) {
		
		Assert.assertFalse(!CommonMethods.isDisplyed("XperInfoText", "Personam Information Text"));
		
		CommonMethods.selectRadioButton("Xgender1", "Mr.");
		
		CommonMethods.enterTextInTextBox("XfirstName", "FirstName", testData);
		
		CommonMethods.enterTextInTextBox("XlastName", "LastName", testData);
		
		CommonMethods.enterTextInTextBox("Xpassword", "Password", testData);
		
		CommonMethods.selectByValue("Xdays", "Day", "Date of Birth", testData);
		
		CommonMethods.selectByValue("Xmonths", "Month" , "Date of Birth", testData);
		
		CommonMethods.selectByValue("Xyears", "Year" , "Date of Birth", testData);
		
	}
}
