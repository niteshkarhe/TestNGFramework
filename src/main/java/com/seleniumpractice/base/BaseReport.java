package com.seleniumpractice.base;

import com.aventstack.extentreports.Status;

public class BaseReport 
{
	public void Log(String message, Status logStatus)
	{
		if (BaseTest.testReport.get() != null)
		{
			BaseTest.testReport.get().log(logStatus, message);
		}
	}
	
	public void LogResult(boolean hasFailures, String message)
	{
		if (BaseTest.testReport.get() == null)
		{
			return;
		}
		
		String result = hasFailures ? "FAIL" : "PASS";
		Status logStatus = hasFailures ? Status.FAIL : Status.PASS; 
		BaseTest.testReport.get().log(logStatus, message);
	}
}
