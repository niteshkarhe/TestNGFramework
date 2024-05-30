package com.seleniumpractice.base;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestInvokeMethodListener implements IInvokedMethodListener
{
	@Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        log("Commencing", method);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        log("Completed", method);
    }
    
    private static void log(String prefix, IInvokedMethod method) {
        String type = "Configuration";
        if (method.isTestMethod()) {
            type = "Test";
        }
        String msg = prefix + " executing [" + type + "] method "
                + method.getTestMethod().getQualifiedName() + "()";
        System.err.println(msg);
    }
}
