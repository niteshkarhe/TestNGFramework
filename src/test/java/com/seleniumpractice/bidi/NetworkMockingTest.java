package com.seleniumpractice.bidi;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.fetch.Fetch;
import org.openqa.selenium.devtools.v121.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v121.network.model.Request;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NetworkMockingTest 
{
	@Test
	public void RequestMockingForAllUrlTest()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);

		// Fetch domain is to mock networks
		devTool.send(Fetch.enable(Optional.empty(), Optional.empty()));
		
		// Use Event to listen API request event
		devTool.addListener(Fetch.requestPaused(), request -> {
			Request req = request.getRequest();
			if (req.getUrl().contains("shetty"))
			{
				String mockUrl = req.getUrl().replace("=shetty", "=BadGuy");
				System.out.println(mockUrl);
				devTool.send(Fetch.continueRequest(
						request.getRequestId(), 
						Optional.of(mockUrl), 
						Optional.of(req.getMethod()),
						req.getPostData(), 
						request.getResponseHeaders(),
						Optional.of(false)));
			}
			else
			{
				devTool.send(Fetch.continueRequest(
						request.getRequestId(), 
						Optional.of(req.getUrl()), 
						Optional.of(req.getMethod()),
						req.getPostData(), 
						request.getResponseHeaders(),
						Optional.of(false)));
			}
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement virtualLibraryBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Virtual Library']")));
		virtualLibraryBtn.click();
		WebElement paragraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
		System.out.println(paragraph.getText());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test
	public void RequestMockingForSpecificUrlTest()
	{
		ChromeDriver driver = getChromeDriver();
		DevTools devTool = getDevTools(driver);
		
		Optional<List<RequestPattern>> pattern = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));
		// Fetch domain is to mock networks
		devTool.send(Fetch.enable(Optional.empty(), Optional.empty()));
				
		devTool.send(Fetch.enable(pattern, Optional.empty()));
		// Use Event to listen API request event
				devTool.addListener(Fetch.requestPaused(), request -> {
					Request req = request.getRequest();
					String mockUrl = req.getUrl().replace("=shetty", "=BadGuy");
					System.out.println(mockUrl);
					devTool.send(Fetch.continueRequest(
							request.getRequestId(), 
							Optional.of(mockUrl), 
							Optional.of(req.getMethod()),
							req.getPostData(), 
							request.getResponseHeaders(),
							Optional.of(false)));
				});
				
				driver.get("https://rahulshettyacademy.com/angularAppdemo");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement virtualLibraryBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Virtual Library']")));
				virtualLibraryBtn.click();
				WebElement paragraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));
				System.out.println(paragraph.getText());
				
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
