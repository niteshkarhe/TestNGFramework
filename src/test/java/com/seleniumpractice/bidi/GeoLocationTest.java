package com.seleniumpractice.bidi;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class GeoLocationTest 
{
	@Test
	public void LocationTest()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);

		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude", 40);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		driver.get("https://www.google.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement googleSearchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		googleSearchBox.sendKeys("netflix", Keys.ENTER);
		WebElement netflixLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Netflix - Watch TV Shows Online, Watch Movies Online']")));
		netflixLink.click();
		WebElement netflixTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@data-uia='nmhp-card-hero-text-title']")));
		String titleText = netflixTitleElement.getText();
		System.out.println(titleText);
		
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
