package com.seleniumpractice.selenium;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;

public class SeleniumActions
{
	protected WebDriver driver;
	protected SeleniumReport report;
	
	public SeleniumActions(ThreadLocal<WebDriver> driver)
	{
		this.driver = driver.get();
		this.report = SeleniumReport.ToSeleniumReport(this.driver);
	}
	
	public SeleniumActions(WebDriver driver)
	{
		this.driver = driver;
		this.report = SeleniumReport.ToSeleniumReport(this.driver);
	}
	
	protected DriverWait driverWait()
	{
		return new DriverWait(this.driver);
	}
	
	protected WebElement getElement(String locator, String locatorType, long... seconds)
	{
		try
		{
			if (getByLocator(locator, locatorType) != null)
			{
				WebElement element = driverWait().getElementWhenVisible(getByLocator(locator, locatorType), seconds);
				if (element == null)
				{
					this.report.LogResult(true, "Element is not located with [<b>" + locatorType + "</b>] : [<b>" + locator + "</b>]");
					Assert.fail();
				}
				else
				{
					this.report.LogResult(false, "Element is located with [<b>" + locatorType + "</b>] : [<b>" + locator + "</b>]");
				}
				
				return element;
			}
			else
			{
				this.report.LogResult(true, "LocatorType [<b>" + locatorType + "</b>] is invalid");
			}
		}
		catch (Exception e)
		{
			this.report.LogResult(true, "Error: [<b>" + e.getMessage() + "</b>] \n" + Arrays.toString(e.getStackTrace()));
		}
		
		return null;
	}
	
	protected void enterText(String locator, String locatorType, String text, long... seconds)
	{
		try
		{
			WebElement element = getElement(locator, locatorType, seconds);
			if (element != null)
			{
				element.sendKeys(text);
				this.report.LogResult(false, "Text [<b>" + text + "</b>] is enetered into the textbox");
			}
			else
			{
				this.report.LogResult(true, "Text [<b>" + text + "</b>] is not enetered into the textbox because element is not located");
			}
		}
		catch (Exception e)
		{
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
	}
	
	protected void click(String locator, String locatorType, long... seconds)
	{
		try
		{
			WebElement element = getElement(locator, locatorType, seconds);
			if (element != null)
			{
				driverWait().getElementToBeClickable(element, seconds).click();
				this.report.LogResult(false, "Element is clicked with [<b>" + locatorType + "</b>] : [<b>" + locator + "</b>]");
			}
		}
		catch (Exception e)
		{
			this.report.LogResult(true, "<b>" + e.getMessage() + "</b>");
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
	}
	
	protected void scrollVertical(String scrollableVerticalBody, String heightToScroll)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.evaluate(\"" + scrollableVerticalBody + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.scrollBy(0," + heightToScroll + ")");
	}
	
	private By getByLocator(String locator, String locatorType)
	{
		try
		{
			switch (locatorType)
			{
				case "id": return By.id(locator);
				case "name": return By.name(locator);
				case "className": return By.className(locator);
				case "tagname": return By.tagName(locator);
				case "linkText": return By.linkText(locator);
				case "partialLinkText": return By.partialLinkText(locator);
				case "cssSelector": return By.cssSelector(locator);
				case "xpath": return By.xpath(locator);
				default: return null;
			}
		}
		catch (Exception e)
		{
			this.report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
		return null;
	}
}
