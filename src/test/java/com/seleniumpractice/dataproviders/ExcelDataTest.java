package com.seleniumpractice.dataproviders;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seleniumpractice.filereader.ExcelReader;

public class ExcelDataTest {
	@Test(dataProvider = "exceldata", testName = "Verify that the user details are displayed")
	public void ExcelDataReaderTest(Map<String, String> data) {
		System.out.println(data);
	}
	
	@Test(dataProvider = "exceldata", testName = "Verify that the invalid user details are displayed")
	public void ExcelDataInvalidUserTest(Map<String, String> data) {
		System.out.println(data);
	}

	@DataProvider(name="exceldata")
	public Object[][] getExcelData(ITestContext context, Method method) 
	{
		String worksheetFullName = context.getCurrentXmlTest().getClasses().stream().findFirst().get().getName();
		String[] pkgWords = worksheetFullName.split("\\.");
		String worksheetName = pkgWords[pkgWords.length - 1];
		String testName = method.getAnnotation(Test.class).testName();
	    List<HashMap<String, String>> excelData = new ExcelReader("testdata", worksheetName, testName).getExcelData();
	    Object[][] dataObject = new Object[excelData.size()][1];
	    int index = 0;
	    for (Map<String, String> interimResult : excelData) {
	    	dataObject[index++] = new Object[] {interimResult};
	    }
	    return dataObject;
	}
}
