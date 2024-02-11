package com.seleniumpractice.extentreports;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager 
{
	public static final ExtentReports extentReports = new ExtentReports();
	
	public synchronized static ExtentReports createExtentReports() {
		String htmlReportPath = CreateReportFolder();
        try
        {
        	if (htmlReportPath != null)
        	{
        		ExtentSparkReporter reporter = new ExtentSparkReporter(htmlReportPath);
                reporter.config().setReportName("Automation Report");
                extentReports.attachReporter(reporter);
                extentReports.setSystemInfo("Web Application", "Orange HRM");
                extentReports.setSystemInfo("Author", "Nitish Karhe");
        	}
        	else
        	{
        		throw new Exception("Test Result path is not set correctly");
        	}
            return extentReports;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	private static String CreateReportFolder()
	{
		try
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();
			Path testResultDirPath = Paths.get(System.getProperty("user.dir") + "//TestResult//" + dtf.format(now));
			if (Files.notExists(testResultDirPath, LinkOption.NOFOLLOW_LINKS))
			{
				String s = testResultDirPath.toString();
				new File(testResultDirPath.toString()).mkdir();
				// Files.createDirectory(testResultDirPath, null);
			}
			
			dtf = DateTimeFormatter.ofPattern("HHmm");
			Path executionSuiteDirPath = Paths.get(testResultDirPath + "//Execution-" + dtf.format(now));
			new File(executionSuiteDirPath.toString()).mkdir();
			// Files.createDirectory(executionSuiteDirPath, null);
			
			return executionSuiteDirPath + "//Test-Result.html";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
