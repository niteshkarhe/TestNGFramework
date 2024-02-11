package com.seleniumpractice.pages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocatorServerSide;

import com.seleniumpractice.selenium.SeleniumActions;

public class LoginPage extends SeleniumActions
{
	private Map<String, String> locators = new HashMap<String, String>();
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		locators.put("usernameTextBoxName", "username");
		locators.put("passwordTextBoxName", "password");
		locators.put("loginBtnXpath", "//button");
	}
	
	public void LoginToTheApplication()
	{
		try
		{
			Thread.sleep(3000);
			enterText(locators.get("usernameTextBoxName"), "name", "Admin", 5);
			enterText(locators.get("passwordTextBoxName"), "name", "admin123", 5);
			click(locators.get("loginBtnXpath"), "xpath", 5);
			Thread.sleep(3000);
		}
		catch (InterruptedException e) 
		{
			report.LogResult(true, Arrays.toString(e.getStackTrace()));
		}
	}
}
