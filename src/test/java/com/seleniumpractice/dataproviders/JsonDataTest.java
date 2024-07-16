package com.seleniumpractice.dataproviders;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seleniumpractice.filereader.JsonReader;
import com.seleniumpractice.selenium.SeleniumTest;

public class JsonDataTest 
{
	@Test(dataProvider="jsondata", testName="Verify that the user details are displayed")
	public void JsonDataReaderTest(Map<String, String> data)
	{
		System.out.println(data);
	}
	
	@Test(dataProvider="jsondata", testName="Verify that the invalid user details are displayed")
	public void JsonDataReaderInvalidUserTest(Map<String, String> data)
	{
		System.out.println(data);
	}
	
	@DataProvider(name="jsondata")
	public Object[][] getJsonData(ITestContext context, Method method)
	{
		String fullClassName = SeleniumTest.seleniumConfig.getEnvironment() + "_" + context.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();
		String[] pkgWords = fullClassName.split("\\.");
		String testClassName = pkgWords[pkgWords.length - 1];
		String testName = method.getAnnotation(Test.class).testName();
		List<HashMap<String, String>> jsonData = new JsonReader(testClassName, testName).getJsonData();
		Object[][] testData = new Object[jsonData.size()][1];
		int index = 0;
		for (Map<String, String> data : jsonData) {
			testData[index++] = new Object[] {data};
	    }
		
		return testData;
	}
}
