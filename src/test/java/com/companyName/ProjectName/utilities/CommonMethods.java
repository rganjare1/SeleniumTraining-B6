package com.companyName.ProjectName.utilities;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.companyName.ProjectName.TestBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends TestBase {

	public static void clickOnWebElement(String xpath, String element) {

		try {
			driver.findElement(By.xpath(OR.getProperty(xpath))).click();
			test.log(LogStatus.PASS, "User clicked on " + element);
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while Clicking on Webelement with LinkText:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while Clicking on Webelement with LinkText:-" + t.getMessage());
		}
	}

	public static void clickOnWebElementWithLinkText(String xpath, String element) {
		try {
			driver.findElement(By.linkText(OR.getProperty(xpath))).click();
			test.log(LogStatus.PASS, "User clicked on " + element + " link");
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while Clicking on Webelement with LinkText:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while Clicking on Webelement with LinkText:-" + t.getMessage());
		}
	}

	public static String getpageTitle() {

		String title = driver.getTitle();
		return title;
	}

	public static boolean isDisplyed(String xpath, String element) {
		boolean result = false;
		try {
			result = driver.findElement(By.xpath(OR.getProperty(xpath))).isDisplayed(); // true Or false
			test.log(LogStatus.PASS, element + " is present on the webPage");
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while finding Webelement on the WebPage:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while finding Webelement on the WebPage:-" + t.getMessage());
		}
		return result;
	}

	public static void enterTextInTextBox(String xpath, String KeyValue, Hashtable<String, String> testData) {
		try {
			driver.findElement(By.xpath(OR.getProperty(xpath))).sendKeys(testData.get(KeyValue));
			test.log(LogStatus.PASS, "User entered " + KeyValue);
			// Thread.sleep(2000);
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while entering values in text box:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while entering values in text box:-" + t.getMessage());
		}

	}

	public static void selectRadioButton(String xpath, String name) {
		try {
			driver.findElement(By.xpath(OR.getProperty(xpath))).click();
			test.log(LogStatus.PASS, "User selected " + name + " radio button");
			// Thread.sleep(2000);
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while selecting radio button:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while selecting radio button:-" + t.getMessage());
		}
	}

	public static void selectByValue(String xpath, String value, String dropdownname,Hashtable<String, String> testData) {
		try {
			Thread.sleep(2000);
			Select sel = new Select(driver.findElement(By.xpath(OR.getProperty(xpath))));
			sel.selectByValue(testData.get(value));
			test.log(LogStatus.PASS, "User selected " + value + " from " + dropdownname);
			// Thread.sleep(2000);
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while selecting value from dropdown:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while selecting value from dropdown:-" + t.getMessage());
		}
	}

	public static void selectByIndex(String xpath, int value, String dropdownname) {
		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.getProperty(xpath))));
			sel.selectByIndex(value);
			test.log(LogStatus.PASS, "User selected " + value + " from " + dropdownname);
			// Thread.sleep(2000);
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while selecting value from dropdown:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while selecting value from dropdown:-" + t.getMessage());
		}
	}

	public static void selectByVisibleText(String xpath, String value, String dropdownname) {
		try {

			Select sel = new Select(driver.findElement(By.xpath(OR.getProperty(xpath))));
			sel.selectByVisibleText(value);
			test.log(LogStatus.PASS, "User selected " + value + " from " + dropdownname);
			// Thread.sleep(2000);
			takeScreenShot_Pass();
		} catch (Throwable t) {
			test.log(LogStatus.FAIL, "Issue while selecting value from dropdown:-" + t.getMessage());
			//takeScreenShot_Fail();
			Assert.fail("Issue while selecting value from dropdown:-" + t.getMessage());
		}

	}

}
