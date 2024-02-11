package com.seleniumpractice.selenium;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWait 
{
	private WebDriver driver;
	private SeleniumReport report;
	
	public DriverWait(WebDriver driver)
	{
		this.driver = driver;
		this.report = new SeleniumReport(this.driver);
	}
	
	public WebElement getElementWhenPresentInDom(By locator, long... inpSeconds)
	{
		try
		{
			long seconds = inpSeconds[0] != 0 ? inpSeconds[0] : SeleniumTest.seleniumConfig.getWebDriverMaxWait();
			WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(seconds));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (Exception e)
		{
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
		
		return null;
	}
	
	public WebElement getElementWhenVisible(By locator, long... inpSeconds)
	{
		try
		{
			long seconds = inpSeconds[0] != 0 ? inpSeconds[0] : SeleniumTest.seleniumConfig.getWebDriverMaxWait();
			WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(seconds));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		catch (Exception e)
		{
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
		
		return null;
	}
	
	public WebElement getElementToBeClickable(WebElement element, long... inpSeconds)
	{
		try
		{
			long seconds = inpSeconds[0] != 0 ? inpSeconds[0] : SeleniumTest.seleniumConfig.getWebDriverMaxWait();
			WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(seconds));
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch (Exception e)
		{
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
		
		return null;
	}
}
