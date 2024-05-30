package com.seleniumpractice.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class TestTransformer implements IAnnotationTransformer
{
	@Override
  	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		List groupNames = Arrays.asList(annotation.getGroups());
  		System.out.println(groupNames.toString());
  		
  		String groupNameToSkip = "betaTest";

  		if(groupNames.contains(groupNameToSkip)){
  			System.out.println("found group name");
  			annotation.setEnabled(false);
  		}
  	}
}
