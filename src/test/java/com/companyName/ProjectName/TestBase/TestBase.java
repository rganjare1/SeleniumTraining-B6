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

public class TestBase {

	public static WebDriver driver;
	public static Properties config; 
	public static Properties OR;  // Global Variable
	public static ExtentReports extents;
	public static ExtentTest test;
	public static String screenshot_path;
	public static String testCaseName;
	public static Hashtable<String, String> runModeHT = new Hashtable<String, String>();
	
	static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\MasterSheet_Batch6.xlsx");
	
	@BeforeSuite	
	public void init() throws IOException {
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

		extents = new ExtentReports(
				"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\execution_Reports\\Report_"
						+ timeStamp + ".html");
		System.out.println("ExtentReports path has been set has been loaded");
		
		loadHashTable(runModeHT, "Test_Cases", "TestCaseName", "Run_Mode");
		
	}
		
	//@BeforeMethod
	public void launchBrowser() {
		
		if (config.getProperty("browser").equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			//System.out.println("ChromeDriver has been Launched");
			test.log(LogStatus.PASS, "Chrome Browser has been launched.");
		} else if (config.getProperty("browser").equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			//System.out.println("FirefoxDriver has been Launched");
			test.log(LogStatus.PASS, "Firefox Browser has been launched.");
		} else {
			System.setProperty("webdriver.ie.driver",
					"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			//System.out.println("InternetExplorerDriver has been Launched");
			test.log(LogStatus.PASS, "InternetExplorer Browser has been launched.");
		}

		driver.get(config.getProperty("App_URL"));
		test.log(LogStatus.PASS, "User navigated to the Application URL:-" +config.getProperty("App_URL"));
		
		driver.manage().window().maximize(); // POM - 100%
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //POM
	}

	
	//@AfterMethod
	public void teardown() {
		// closes all the browser windows opened by web driver
		// Post-Condition
		// System.out.println("Test Case execution has been Completed and Driver will
		// close in tearDown method");
		test.log(LogStatus.INFO, testCaseName + " Test Case execution has been Completed and Driver will close.");
		driver.quit();
		
		extents.endTest(test);
		extents.flush();
	}

//	@AfterSuite (alwaysRun=true)
//	public void flushExtentReport() {
//	extents.endTest(test);
//	extents.flush();
//	}
		
	@DataProvider
	public static Object[][] Data_Collections() {

		DataCollection dc = new DataCollection(excel, "Test_Data", testCaseName);

		return dc.dataArray();

		// Hash Table -- htdata
	}

	public static void takeScreenShot_Pass() {//20

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

	public static void takeScreenShot_Fail() {

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
	
	public void loadHashTable(Hashtable<String, String> hashtable,String sheetName, String col_TCName,String col_RMName) {
		
		int rows = excel.getRowCount(sheetName);
		System.out.println("Number of rows:-"+rows);
		
		for(int i = 2; i<=rows; i++ ) {
			String key = excel.getCellData(sheetName, col_TCName, i);
			String value = excel.getCellData(sheetName, col_RMName, i);
			hashtable.put(key, value);
		}
		System.out.println(hashtable);
	}

}
