package com.seleniumpractice.homepage;

import org.testng.annotations.Test;

import com.seleniumpractice.browserDriverConfiguration.DriverManager;
import com.seleniumpractice.pages.HomePage;
import com.seleniumpractice.pages.LoginPage;
import com.seleniumpractice.selenium.SeleniumTest;

public class HomePageTest // extends SeleniumTest
{
	@Test
	public void LogicTest()
	{
		System.out.println(checkPatternMatches());
	}
	
	private boolean checkPatternMatches()
	{
		String pattern = "a";
		String s = "dog dog";
		
		String[] words = s.split("\\s");
		if (words.length != pattern.length())
		{
			return false;
		}

		String[] mapper = new String[26];
		int index = 0;
		boolean flag = false;
		for (char c : pattern.toCharArray())
		{
			if (mapper[c-'a'] == null)
			{
				mapper[c-'a'] = words[index];
			}
			else if (!mapper[c-'a'].equals(words[index]))
			{
				return false;
			}
			else 
			{
				flag = true;
			}

			index++;
		}

		if (flag == true)
		{
			return true;
		}
		
		return false;
	}
}
