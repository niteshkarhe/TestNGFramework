package com.seleniumpractice.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.seleniumpractice.selenium.SeleniumReport;
import com.seleniumpractice.selenium.SeleniumTest;

public class TestListeners implements ITestListener //extends SeleniumReport
{
	/*
	public TestListeners(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	*/

	@Override
	public void onFinish(ITestContext contextFinish)
	{
	}

	@Override
	public void onStart(ITestContext contextStart) 
	{
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
	}

	@Override
	public void onTestFailure(ITestResult result) {
		SeleniumReport report = SeleniumReport.ToSeleniumReport(SeleniumTest.getDriver().get());
		report.LogResult(true, "This is start of the test");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
	}
}