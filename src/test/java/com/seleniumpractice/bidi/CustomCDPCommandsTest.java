package com.seleniumpractice.bidi;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CustomCDPCommandsTest 
{
	@Test
	public void executeCustomCommand()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);
		Map deviceMetrics = new HashMap();
		deviceMetrics.put("width", 600);
		deviceMetrics.put("height", 1000);
		deviceMetrics.put("deviceScaleFactor", 50);
		deviceMetrics.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		driver.findElement(By.cssSelector("button[data-target='#navbarSupportedContent']")).click();
		WebElement locatorElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Library")));
		locatorElement.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	
	private ChromeDriver getChromeDriver()
	{
		ChromeOptions option = new ChromeOptions();
		//driver.manage().window().maximize();
		option.addArguments("start-maximized");
		// This is optional to set chrome.exe path
		option.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(option);
		return driver;
	}
	
	private DevTools getDevTools(ChromeDriver driver)
	{
		DevTools devTool = driver.getDevTools();
		devTool.createSession();
		return devTool;
	}
}
