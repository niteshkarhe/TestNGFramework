package com.seleniumpractice.homepage;

import org.testng.annotations.Test;

import com.seleniumpractice.browserDriverConfiguration.DriverManager;
import com.seleniumpractice.pages.HomePage;
import com.seleniumpractice.pages.LoginPage;
import com.seleniumpractice.selenium.SeleniumTest;

public class HomePageTest extends SeleniumTest
{
	@Test
	public void LogicTest()
	{
		LoginPage loginPg = new LoginPage(getDriver().get());
		loginPg.LoginToTheApplication();
	}
}
