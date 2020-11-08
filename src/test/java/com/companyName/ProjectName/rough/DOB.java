package com.companyName.ProjectName.rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DOB {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Automation_Testing\\WorkSpace\\BatchNo6_MVN_Project\\src\\test\\resources\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	//	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php");

		driver.findElement(By.xpath("//*[@class='login']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("test.test1237@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();
		Thread.sleep(3000);

		Select dropd = new Select(driver.findElement(By.id("days")));
		dropd.selectByValue("10");
		Thread.sleep(2000);

		WebElement element = driver.findElement(By.xpath("//select[@id='months']"));
		Select dd = new Select(element);
		dd.selectByValue("3");
		Thread.sleep(2000);

		Select DD = new Select(driver.findElement(By.xpath("//select[@id='years']")));
		//DD.selectByIndex(4);
		DD.selectByValue("2017");
		Thread.sleep(3000);

		driver.quit();
  } }
