/**
 * @author Nitish Karhe
 */

package com.seleniumpractice.selenium;

import java.io.FileReader;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.seleniumpractice.base.BaseTest;
import com.seleniumpractice.browserDriverConfiguration.DriverManager;
import com.seleniumpractice.browserDriverConfiguration.DriverManagerFactory;

import lombok.Getter;
import lombok.Setter;

public class SeleniumTest extends BaseTest {
	public static SeleniumConfig seleniumConfig = InitializeConfig();
	@Getter
	@Setter
	static public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private DriverManager factory = null;
	
	@BeforeClass(alwaysRun = true)
	public void startClass()
	{
		if (seleniumConfig.getBrowserScope().equals("class")) 
		{
			startWebDriver();
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void startTest()
	{
		System.out.println("#### Before Method to start driver");
		if (seleniumConfig.getBrowserScope().equals("method")) 
		{
			startWebDriver();
		}
	}
	
	/*
	 * @BeforeMethod(groups= {"NoUse"}) public void startTesdt() {
	 * System.out.println("#### Before Method of no use"); }
	 */
	  
	@AfterMethod(alwaysRun = true)
	public void stopTest()
	{
		if (seleniumConfig.getBrowserScope().equals("method"))
		{
			System.out.println("#### After method to close browser");
			stopWebDriver();
		} 
	}
	
	@AfterClass(alwaysRun = true)
	public void stopClass()
	{
		if (seleniumConfig.getBrowserScope().equals("class"))
		{
			System.out.println("#### After method to close browser after all class tests are run");
			stopWebDriver();
		}
	}
	
	private void startWebDriver() {
		try
		{
			factory = DriverManagerFactory.getManager(seleniumConfig.getBrowserName());
			driver.set(factory.getDriver());
			setDriver(driver);
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(seleniumConfig.getImplicitWait()));
			driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seleniumConfig.getPageLoadWait()));
			// driver.get().manage().deleteAllCookies();
			driver.get().manage().window().maximize();
			driver.get().navigate().to(seleniumConfig.getApplicationUrl());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void stopWebDriver()
	{
		try
		{
			driver.get().quit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static SeleniumConfig InitializeConfig()
	{
		try
		{
			String rootDirPath = System.getProperty("user.dir");
			String projectConfigPath = rootDirPath + "\\src\\main\\java\\com\\seleniumpractice\\configs\\projectconfigs.json";
			JsonReader reader = new JsonReader(new FileReader(projectConfigPath));
			Gson gson = new Gson();
			return gson.fromJson(reader, SeleniumConfig.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
