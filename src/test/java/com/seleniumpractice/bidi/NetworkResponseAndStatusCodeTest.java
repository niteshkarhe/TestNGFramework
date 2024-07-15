package com.seleniumpractice.bidi;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.Request;
import org.openqa.selenium.devtools.v121.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NetworkResponseAndStatusCodeTest
{
	@Test
	public void NetworkLogActivityTest()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);
		
		//Network Domain of CDP API
		devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		// Before sending any request from UI, all request details are captured
		devTool.addListener(Network.requestWillBeSent(), request -> {
			Request requestObject = request.getRequest();
			System.out.println("Request URL: " + requestObject.getUrl());
		});
		
		//When response is received then Event will be fired
		devTool.addListener(Network.responseReceived(), response -> {
			Response responseObject = response.getResponse();
			System.out.println("Response URL: " + responseObject.getUrl());
			System.out.println("Response Status: " + responseObject.getStatus());
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement virtualLibraryBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Virtual Library']")));
		virtualLibraryBtn.click();
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