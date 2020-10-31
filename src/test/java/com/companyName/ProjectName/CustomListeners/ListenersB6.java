package com.companyName.ProjectName.CustomListeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.companyName.ProjectName.TestBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ListenersB6 extends TestBase implements ITestListener{
	
	// Interface is having the Signature of the methods
	// Definition of the methods

	public void onTestStart(ITestResult result) {
		test = extents.startTest(testCaseName);
		test.log(LogStatus.INFO, testCaseName+" test case execution has been started");
		launchBrowser();		
	} 

	public void onTestSuccess(ITestResult result) {

		test.log(LogStatus.PASS, testCaseName+" Test case has been passed");
		
		teardown();
		
	}

	public void onTestFailure(ITestResult result) {
			
       try {
			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\screenShots\\fail\\screenShots_"
					+ timeStamp_1 + ".png";

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileHandler.copy(source, new File(screenshot_path));
			test.log(LogStatus.FAIL, testCaseName+ " Test case is failed due to :-"  + result.getThrowable());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		teardown();
		
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.INFO, testCaseName+" Test case is skipped");
		
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
