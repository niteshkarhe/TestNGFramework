/**
 * @author Nitish Karhe
 */

package com.seleniumpractice.selenium;

import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeleniumConfig
{
	private String applicationUrl;
	private String browserName;
	private String browserVersion;
	private String browserScope;
	private String environment;
	private long webDriverMaxWait;
	private int implicitWait;
	private int pageLoadWait;
	private boolean headlessBrowser;
	private String fileDownloadPath;
	private boolean incognitoMode;
	private boolean screenCapture;
}