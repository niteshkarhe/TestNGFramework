package com.seleniumpractice.homepage;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.seleniumpractice.pages.LoginPage;
import com.seleniumpractice.selenium.SeleniumTest;

public class LoginTest extends SeleniumTest
{
	@Parameters({"constructor"})
	public LoginTest(String constructorName)
	{
		System.out.println("Parameter: " + constructorName);
	}
	
	@Test(testName = "This is Demo Test1", groups = {"Login", "LoginDemo"})
	public void ThisIsDemoTest1()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test2")
	public void ThisIsDemoTest2()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test3")
	public void ThisIsDemoTest3()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test4")
	public void ThisIsDemoTest4()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
	
	@Test(testName = "This is Demo Test5", groups = {"TestWithReturn", "LoginDemo", "interceptor"}, priority = 1)
	public int ThisIsDemoTest5()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
		return 0;
	}
}
