package com.seleniumpractice.bidi;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.dom.DOM;
import org.openqa.selenium.devtools.v121.dom.model.RGBA;
import org.openqa.selenium.devtools.v121.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MobileEmulatorTest
{
	@Test
	public void ChangeToMobileScreenSize()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);
		
		// Send commands to CDP. Always make sure Emulation class is imported from latest version
		// https://chromedevtools.github.io/devtools-protocol/tot/Emulation/
		devTool.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
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
