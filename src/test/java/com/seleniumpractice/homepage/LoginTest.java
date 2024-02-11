package com.seleniumpractice.homepage;

import org.testng.annotations.Test;

import com.seleniumpractice.pages.LoginPage;
import com.seleniumpractice.selenium.SeleniumTest;

public class LoginTest extends SeleniumTest
{
	@Test(testName = "This is Demo Test")
	public void ThisIsDemoTest1()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test")
	public void ThisIsDemoTest2()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test")
	public void ThisIsDemoTest3()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test")
	public void ThisIsDemoTest4()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
}
