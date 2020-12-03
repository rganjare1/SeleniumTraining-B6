package com.companyName.ProjectName.Interface;

public class TestWebDriverInterface { // Current user

	static SBI a = new SBI();
//	static WebDriver driver = new FireFoxDriver();
//	static InternetExplorerDriver driver = new InternetExplorerDriver();
	
	public static void main(String[] args) {
		
		a.get("URL");
		a.getTitle();
		a.close();
		a.quit();
		a.takeScreenShot();
		
	}

}
