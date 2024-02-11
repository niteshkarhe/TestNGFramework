package com.seleniumpractice.browserDriverConfiguration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager
{
	private ChromeDriverService chService;
	   
    public void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    public void createDriver() {	
	        ChromeOptions options = new ChromeOptions();
	        // options.addArguments("--");
	        driver = new ChromeDriver(options);
    }
}
