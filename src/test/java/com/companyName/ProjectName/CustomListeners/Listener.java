package com.companyName.ProjectName.CustomListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.companyName.ProjectName.TestBase.TestBaseNew;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Rahul Ganjare
 */
public class Listener extends TestBaseNew implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = extents.startTest(testCaseName);
		test.log(LogStatus.INFO, testCaseName +" Test Case execution has been started.");
		launchBrowser();
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS,  testCaseName+" execution has been Passed.");
		teardown();
	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL,  testCaseName+" execution has been Failed.");
	teardown();
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.INFO, "Skipping " + testCaseName+" as Run mode is set to N");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	} 

	
}
