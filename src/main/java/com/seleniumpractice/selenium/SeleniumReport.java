package com.seleniumpractice.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.seleniumpractice.base.BaseReport;
import com.seleniumpractice.base.BaseTest;

import lombok.Getter;
import lombok.Setter;

public class SeleniumReport extends BaseReport
{
	@Getter
	@Setter
	public WebDriver driver;
	
	public SeleniumReport(ThreadLocal<WebDriver> driver)
	{
		setDriver(driver.get());
	}
	
	public SeleniumReport(WebDriver driver)
	{
		setDriver(driver);
	}
	
	public void LogResult(boolean hasFailures, String message)
	{
		if (BaseTest.testReport.get() == null)
		{
			return;
		}
		
		String result = hasFailures ? "FAIL" : "PASS";
		Status logStatus = hasFailures ? Status.FAIL : Status.PASS; 
		BaseTest.testReport.get().log(logStatus, "Result: " + result + " | " + "Details: " + message);
		if (hasFailures && SeleniumTest.seleniumConfig.isScreenCapture())
		{
			SaveScreenshot();
		}
	}
	
	public void SaveScreenshot()
	{
		if (BaseTest.testReport.get() == null)
		{
			return;
		}
		
		int width = 250;
		int height = 150;
		TakesScreenshot screenshotDriver = (TakesScreenshot) getDriver();
		
		try
		{
			String imageBinary = screenshotDriver.getScreenshotAs(OutputType.BASE64);
			BaseTest.testReport.get().log(Status.INFO,
					"<a href=data:image/png;base64," + imageBinary + " data-featherlight=\"image\"> <img alt=\"Embedded Image\" src=\"data:image/png;base64," + imageBinary +"\"width\"" + width + "\"height\""+ height + "\"/></a>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static SeleniumReport ToSeleniumReport(WebDriver driver)
	{
		try
		{
			return new SeleniumReport(driver);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
