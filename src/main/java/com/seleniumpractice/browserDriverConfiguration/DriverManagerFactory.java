package com.seleniumpractice.browserDriverConfiguration;

public class DriverManagerFactory
{
	private static ChromeDriverManager chromeDriver = null;
	private static FirefoxDriverManager fireDriver = null;

	public static DriverManager getManager(String browserName) {
		if (browserName.equals("chrome")) {
			if (chromeDriver == null) {
				chromeDriver = new ChromeDriverManager();
				return chromeDriver;
			} else
				return chromeDriver;
		} else if (browserName.equals("firefox")) {
			if (fireDriver == null) {
				fireDriver = new FirefoxDriverManager();
				return fireDriver;
			} else
				return fireDriver;
		}
		
		return null;
	}
}