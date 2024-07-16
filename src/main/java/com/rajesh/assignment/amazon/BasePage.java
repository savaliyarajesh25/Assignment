package com.rajesh.assignment.amazon;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.util.concurrent.Uninterruptibles;
import com.rajesh.drivers.DriverManager;
import com.rajesh.enums.WaitStrategy;
import com.rajesh.report.ExtentLogger;
import com.rajesh.report.ExtentManager;
import com.rajesh.utils.ConfigLoader;
import com.rajesh.utils.ConstantPaths;
import com.rajesh.utils.ExplicitWait;
import com.rajesh.utils.ScreenshotUtils;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, ConstantPaths.getExplicitWaitOfSeconds());
	}

	public void load(String endPoint) {
		System.out.println("URL: "+ConfigLoader.getInstance().getBaseUrl());
		driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
		ExtentLogger.info("  Navigating to : <b>" + ConfigLoader.getInstance().getBaseUrl()
				+ endPoint + "</b>");
	}
	
	public void loadTempURL(String url) {
		System.out.println("URL: "+ConfigLoader.getInstance().getBaseUrl());
		driver.get(url);
		ExtentLogger.info("  Navigating to : <b>" + url + "</b>");
	}

	protected void click(By by, WaitStrategy waitStrategy, String elementName) {
		// DriverManager.getDriver().findElement(by).click();
		ExplicitWait.performExplicitWait(waitStrategy, by).click();
		ExtentLogger.pass("<b>" + elementName + "</b> is clicked", true);
		// log(PASS,elementName+" is clicked");
	}

	protected void sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		ExplicitWait.performExplicitWait(waitStrategy, by).sendKeys(value);
		if(elementName.contains("Password")){
			ExtentLogger.pass("<b>" + "***********" + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		}else{
			ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		}

		// log(PASS,value +" is entered successfully in "+elementName);
	}

	protected void clear(By by, WaitStrategy waitStrategy, String elementName) {
		ExplicitWait.performExplicitWait(waitStrategy, by).clear();
		ExtentLogger.info("Clearing the field  <b>" + elementName + "</b>");
		// log(PASS,value +" is entered successfully in "+elementName);
	}

	protected void clearAndSendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
		WebElement element = ExplicitWait.performExplicitWait(waitStrategy, by);
		element.clear();
		element.sendKeys(value);
		ExtentLogger.pass("<b>" + value + "</b> is entered successfully in <b>" + elementName + "</b>", true);
		// log(PASS,value +" is entered successfully in "+elementName);
	}

	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	protected List<WebElement> getListOfElements(By by) {
		ExplicitWait.performExplicitWait(WaitStrategy.VISIBLE, by);
		return driver.findElements(by);
	}

	protected void captureScreenshot() {
		ExtentManager.getExtentTest().info("Capturing Screenshot",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	}

	protected void waitForGivenTime(long time) {
		Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
	}

}
