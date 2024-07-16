package com.rajesh.utils;

import java.time.Duration;

public class ConstantPaths {
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	
	private static final long EXPLICIT_WAIT = 30;//in second
	
	public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + "/ExtentReports/";
	
	public static final String EXTENT_REPORT_NAME = "AutomationReport.html";
	
	private static final String Project_Name = "Automation Test Report";
	
	private static String extentReportFilePath = "";
	
	
	public static String getExtentReportFilePath() {

		if (extentReportFilePath.isEmpty()) {
			extentReportFilePath = createExtentReportPath();
		}
		return extentReportFilePath;
	}

	public static String createExtentReportPath() {
		if (ConfigLoader.getInstance().getOverrideReports().equalsIgnoreCase("no")) {
			
			return EXTENT_REPORT_FOLDER_PATH + OSInfoUtils.getOSInfo() + "_" + DateUtils.getCurrentDate() + "_"
					+ EXTENT_REPORT_NAME;

		} else {
			return EXTENT_REPORT_FOLDER_PATH + EXTENT_REPORT_NAME;
		}
	}
	
	public static String getProjectName() {
		return Project_Name;
	}
	
	public static Duration getExplicitWaitOfSeconds() {
		return Duration.ofSeconds(EXPLICIT_WAIT);
	}
}
