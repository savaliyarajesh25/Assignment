package com.rajesh.drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	    private DriverManager() {
	    }

	    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void setDriver(WebDriver driverref) {
	        driver.set(driverref);
	    }

	    public static void unload() {
	        driver.remove();
	    }

}
