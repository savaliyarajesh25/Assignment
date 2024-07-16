package com.rajesh.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rajesh.drivers.DriverManager;
import com.rajesh.enums.WaitStrategy;

public class ExplicitWait {

	private ExplicitWait() {
	}

	public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {

		WebElement element = null;
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(DriverManager.getDriver(), ConstantPaths.getExplicitWaitOfSeconds())
					.until(ExpectedConditions.elementToBeClickable(by));
		}
		else if (waitStrategy == WaitStrategy.PRESENCE) {
			element = new WebDriverWait(DriverManager.getDriver(), ConstantPaths.getExplicitWaitOfSeconds())
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} 
		else if (waitStrategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(DriverManager.getDriver(), ConstantPaths.getExplicitWaitOfSeconds())
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} 
		else if (waitStrategy == WaitStrategy.NONE) {
			System.out.println("Not Waiting for anything");
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}
}