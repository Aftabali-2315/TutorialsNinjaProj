package com.tutorialsNinja.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tutorialsNinja.utils.Utilities;

public class Base {

	public WebDriver driver;
	public Properties prop;
	@BeforeMethod
	public void setup() {
		 prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		if(prop.getProperty("browserName").equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
		
		}else if(prop.getProperty("browserName").equalsIgnoreCase("firefox")) {
		
			driver = new FirefoxDriver();
		
		}else if(prop.getProperty("browserName").equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
		
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicaitWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadTimeOut));
		driver.get(prop.getProperty("url"));	
	}
	
	public void tearDown() {
		driver.quit();
	}
}
