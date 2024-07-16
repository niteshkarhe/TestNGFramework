package com.seleniumpractice.filereader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.seleniumpractice.selenium.SeleniumTest;

public class JsonReader 
{
	private String filePath = "";
	private String testName = "";
	
	public JsonReader(String inputFileName, String inputTestName)
	{
		String fileName = SeleniumTest.seleniumConfig.getEnvironment() + "_" + inputFileName;
		this.filePath = System.getProperty("user.dir") + "\\testdata\\json\\" + fileName + ".json";
		this.testName = inputTestName;
	}
	
	public List<HashMap<String, String>> getJsonData()
	{
		List<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		try
		{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(this.filePath));
			JSONObject jsonObject =  (JSONObject) obj;
			if (jsonObject.containsKey(this.testName))
			{
				JSONArray testDataArray = (JSONArray) jsonObject.get(this.testName);
				for (int i = 0; i < testDataArray.size(); i++)
				{
					HashMap<String, String> dataMap = new HashMap<String, String>();
					JSONObject data = (JSONObject) testDataArray.get(i);
					Set<Entry<Object, Object>> dataEntries = data.entrySet();
					for (Entry<Object, Object> dataEntry : dataEntries)
					{
						dataMap.put(dataEntry.getKey().toString(), dataEntry.getValue().toString());
					}
					
					if (dataMap.size() > 0)
					{
						listMap.add(dataMap);
					}
				}
			}
			else
			{
				throw new Exception("Test case [" + this.testName + "] is not present in test data.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return listMap;
	}
}
