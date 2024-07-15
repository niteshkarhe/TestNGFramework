package com.seleniumpractice.learnings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.virtualauthenticator.Credential;
import org.openqa.selenium.virtualauthenticator.HasVirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriverCommandTest 
{
	@Test
	public void ChromeOptionsTest()
	{
		ChromeOptions option = new ChromeOptions();
		//driver.manage().window().maximize();
		option.addArguments("start-maximized");
		
		// This is optional to set chrome.exe path
		option.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		
		// To block pop up like - restore the normal Chrome behavior when it is not controlled by ChromeDriver
		option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		
		// To set file download path
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", "E:\\NK\\Selenium Projects\\TestNGFramework");
		prefs.put("download.prompt_for_download", false);
		option.setExperimentalOption("prefs", prefs);
		
		// Headless
		// option.addArguments("headless");
		
		option.addArguments("ignore-certificate-errors");
		option.addArguments("disable-popup-blocking");
		option.addArguments("incognito");
		
		WebDriver driver = new ChromeDriver(option);
		// driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.get("https://demo.automationtesting.in/FileDownload.html");
		driver.findElement(By.linkText("Download")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
	
	@Test
	public void ChromeDriverMethodsTest()
	{
		ChromeOptions option = new ChromeOptions();
		//driver.manage().window().maximize();
		option.addArguments("start-maximized");
		
		ChromeDriver driver = new ChromeDriver(option);
		driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice");
		
		driver.deleteDownloadableFiles();
		
	}
	
	@Test
	public void VirtualAuthenticatorTest()
	{
		ChromeOptions option = new ChromeOptions();
		//driver.manage().window().maximize();
		option.addArguments("start-maximized");
		
		ChromeDriver driver = new ChromeDriver(option);
		
		VirtualAuthenticatorOptions options = new VirtualAuthenticatorOptions()
			      .setProtocol(VirtualAuthenticatorOptions.Protocol.U2F)
			      .setHasResidentKey(false);

			    VirtualAuthenticator authenticator =
			      ((HasVirtualAuthenticator) driver).addVirtualAuthenticator(options);

			    List<Credential> credentialList = authenticator.getCredentials();

			    Assert.assertEquals(0, credentialList.size());
	}
}
