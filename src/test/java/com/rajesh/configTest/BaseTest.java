package com.rajesh.configTest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.rajesh.drivers.DriverManager;
import com.rajesh.drivers.DriverManagerFactory;
import com.rajesh.enums.DriverType;

public class BaseTest {

	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	private void setDriver(WebDriver driver) {
		DriverManager.setDriver(driver);
	}

	@Parameters("browser")
	@BeforeMethod
	public synchronized void startDriver(@Optional String browser) {
		browser = setBrowserValue(browser);
		setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
		System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getDriver());
	}

	@Parameters("browser")
	@AfterMethod
	public synchronized void quitDriver(@Optional String browser, ITestResult result) throws IOException {
		takeScreenshotOnTestFailure(browser, result);
		getDriver().quit();

	}

	private void takeScreenshotOnTestFailure(String browser, ITestResult result) throws IOException {
		browser = setBrowserValue(browser);
		System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getDriver());

		if (result.getStatus() == ITestResult.FAILURE) {
			File destFile = new File("Screenshots" + File.separator + browser + File.separator
					+ result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName()
					+ ".png");
			takeScreenshot(destFile);
		}
	}
	private void takeScreenshot(File destFile) throws IOException {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, destFile);
	}
	private String setBrowserValue(String browser) {
		if (browser == null) {
			browser = "CHROME";
			System.out.println(
					"Test execution not done by Maven cmd or TestNG.xml file ->  setting the value: " + "CHROME");
		}
		browser = System.getProperty("browser", browser);
		return browser;
	}

}
