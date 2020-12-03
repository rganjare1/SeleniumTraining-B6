package com.companyName.ProjectName.TestCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.companyName.ProjectName.TestBase.TestBase;
import com.companyName.ProjectName.pages.HomePage;

public class TC03_CreateNewAndVerifyExistingUser extends TestBase { // Inheritance --OOPs Java

	@BeforeClass
	public void isSkip() {
		// if Run_mode is Y or N
		testCaseName = getClass().getSimpleName();
		if (!runModeHT.get(testCaseName).equalsIgnoreCase("Y"))
			throw new SkipException("Skipping Test case as it's Run Mode is set to N");
	}

	@Test(dataProvider = "Data_Collections")
	public void createNewAndVerifyExistingUser(Hashtable<String, String> testData) {
	
		HomePage.createAnAccountNewUsers(testData);
		
		//HomePage.loginWithAlreadyRegisteredUsers(testData);
		
	}

}
