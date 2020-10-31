package com.companyName.ProjectName.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import co.in.bymat.seleniumTraining.DataCollection;
import co.in.bymat.seleniumTraining.ExcelReader;

public class TestBaseNew {

	public static WebDriver driver;
	public static Properties config;
	public static Properties OR ; 
	public static ExtentReports extents;
	public static ExtentTest test;
	public static String screenshot_path, testCaseName;
	
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\MasterSheet_Batch6_New.xlsx");
	
	public static Hashtable<String, String> testCaseRunMode = new Hashtable<String, String>();
	
	@BeforeSuite
	public void init() throws InterruptedException, IOException {
		// Pre-Condition

		FileInputStream fis = new FileInputStream(
				"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\files\\config.properties");
		config = new Properties();
		config.load(fis);
		System.out.println("Config file has been loaded");

		FileInputStream fis1 = new FileInputStream(
				"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\files\\OR.properties");
		OR = new Properties();
		OR.load(fis1);
		System.out.println("OR file has been loaded");
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		extents = new ExtentReports(
				"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\execution_Reports\\Report_"
						+ timeStamp + ".html");
		System.out.println("Execution report path has been created");
		
		loadHashTable(testCaseRunMode, "Test_Cases", "TestCaseName", "Run_Mode");
	}		
	
	//@BeforeMethod
	public void launchBrowser() {
		try {
		if (config.getProperty("browser").equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			//System.out.println("ChromeDriver has been Launched");
			test.log(LogStatus.PASS, "Chrome browser has been Launched.");
		} else if (config.getProperty("browser").equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			//System.out.println("FirefoxDriver has been Launched");
			test.log(LogStatus.PASS, "Firefox browser has been Launched.");
		} else {
			System.setProperty("webdriver.ie.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			//System.out.println("InternetExplorerDriver has been Launched");
			test.log(LogStatus.PASS, "InternetExplorer browser has been Launched.");
		} } catch (Throwable t) {
			test.log(LogStatus.FAIL, "There is problem while launching the browser.");
		}
		driver.get(config.getProperty("App_URL"));
		test.log(LogStatus.PASS, "User navigated to " +config.getProperty("App_URL")+" application URL.");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//@AfterMethod
	public void teardown() {
		test.log(LogStatus.INFO, "Test Case execution has been Completed and Driver will close in tearDown method");
		driver.quit();
		
		extents.endTest(test);
		extents.flush();

	}
	
	//@AfterSuite(alwaysRun = true)
	//@AfterSuite()
	public void flushReport() {
		extents.endTest(test);
		extents.flush();
	}

	@DataProvider
	public Object[][] Data_Collections() {

		DataCollection dc = new DataCollection(excel, "Test_Data", testCaseName);

		return dc.dataArray();

		// Hash Table -- htdata
	}

	public void takeScreenShot_Pass() throws IOException {

		try {
			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\screenShots\\pass\\screenShots_"
					+ timeStamp_1 + ".png";

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileHandler.copy(source, new File(screenshot_path));

			test.log(LogStatus.PASS, test.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void takeScreenShot_Fail() throws IOException {

		try {
			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\screenShots\\fail\\screenShots_"
					+ timeStamp_1 + ".png";

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileHandler.copy(source, new File(screenshot_path));

			test.log(LogStatus.FAIL, test.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void loadHashTable(Hashtable<String, String> testCaseRunMode, String SheetName, String KeyCol,
			String valueCol) {

		int row = excel.getRowCount(SheetName);

		for (int i = 2; i <= row; i++) {

			String key = excel.getCellData(SheetName, KeyCol, i);

			String val = excel.getCellData(SheetName, valueCol, i);

			testCaseRunMode.put(key, val);
		}

		System.out.println(testCaseRunMode);
	}

	public static boolean isExecutable(String TC_name) {
		testCaseName = TC_name;
		if (testCaseRunMode.get(TC_name).equalsIgnoreCase("Y")) { // Run Mode value			 
			return true;
		} else {		
			return false;
		}
	}


}
