/**
 * @author Nitish Karhe
 */

package com.seleniumpractice.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.seleniumpractice.extentreports.ExtentManager;

import lombok.Getter;
import lombok.Setter;

public class BaseTest
{
	static
	{
		setSuiteReport(ExtentManager.createExtentReports());
	}
	
	@Getter
	@Setter
	public static ExtentReports suiteReport;
	
	@Getter
	@Setter
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	@Getter
	@Setter
	public static ThreadLocal<Boolean> hasFailures = new ThreadLocal<Boolean>();
	
	@Getter
	@Setter
	public static List<String> TestNames = new ArrayList<String>();
	
	@BeforeMethod
	public void TestMethodSetup(Method method)
	{
		hasFailures.set(false);
		try
		{
			Test testngTest = method.getAnnotation(org.testng.annotations.Test.class);
			String testCaseName = testngTest.testName();
			testReport.set(suiteReport.createTest(testCaseName));
			setTestNames(new ArrayList<String>() { { add(testCaseName); } });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void TestMethodTearDown(ITestResult result)
	{
		try
		{
			getTestNames().remove(result.getTestName());
			int testStatus = result.getStatus();
			String stackTrace = result.getThrowable() == null
					? "" 
					: "<pre>" + Arrays.toString(result.getThrowable().getStackTrace()) + "</pre>";
			
			Map<Integer, Status> logStatus = new HashMap<Integer, Status>();
			logStatus.put(result.FAILURE, Status.FAIL);
			logStatus.put(result.SUCCESS, Status.PASS);
			logStatus.put(result.SKIP, Status.SKIP);
			logStatus.put(result.SUCCESS_PERCENTAGE_FAILURE, Status.WARNING);
			
			testReport.get().log(logStatus.get(testStatus), "Test Complete. Result: " + logStatus.get(testStatus) + stackTrace);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void EndSuite()
	{
		getSuiteReport().flush();
	}
}
