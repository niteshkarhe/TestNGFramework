package com.seleniumpractice.learnings;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.log.Log;
import org.openqa.selenium.devtools.v120.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebDriverBiDiTest 
{
	@Test
	public void CreateCDPSessionTest()
	{
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Emulation.setGeolocationOverride(
				Optional.of(47.604653), 
				Optional.of(-122.335461), 
				Optional.of(1))
				);
		
		driver.get("https://my-location.org");
		driver.manage().window().maximize();
	}
	
	@Test
	public void ConsoleLogTest()
	{
		List<String> logs = new ArrayList<>();
		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Log.enable());
		devTools.addListener(Log.entryAdded(), logEntry -> 
				System.out.println("log: " + logEntry.getText())
				);
		
		devTools.addListener(org.openqa.selenium.devtools.v119.runtime.Runtime.consoleAPICalled(), consoleAPICalled -> {
			logs.add(consoleAPICalled.getArgs().get(0).getValue().get().toString());
		});
		
		driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
		
		WebElement element = new WebDriverWait(driver,
							       Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("consoleLog")));
			
		element.click();
		boolean logFound = false;
		
		for (String log : logs) {
			if (log.contains("Hello, world!")) {
				Assert.assertEquals(log, "Hello, world!");
			logFound = true;
			break;
			}
		}

		Assert.assertTrue(logFound);
	}
}
