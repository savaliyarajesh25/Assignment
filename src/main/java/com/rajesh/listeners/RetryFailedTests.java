package com.rajesh.listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.rajesh.utils.ConfigLoader;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;
		if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase("Yes")) {
			if (count < retries) {
				count++;
				return true;
			}
		}
		return value;
	}
}
