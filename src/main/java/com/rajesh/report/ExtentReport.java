package com.rajesh.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.rajesh.utils.ConstantPaths;

public class ExtentReport {

		private ExtentReport() {
		}

		private static ExtentReports extent;

		public static void initReports() {
			if (Objects.isNull(extent)) {
				extent = new ExtentReports();
				ExtentSparkReporter spark = new ExtentSparkReporter(ConstantPaths.getExtentReportFilePath());
				extent.attachReporter(spark);

				// spark.config().setEncoding("utf-8");
				spark.config().setTheme(Theme.STANDARD);
				spark.config().setDocumentTitle(ConstantPaths.getProjectName() + " - ALL");
				spark.config().setReportName(ConstantPaths.getProjectName() + " - ALL");
			}
		}

		public static void flushReports() {
			if (Objects.nonNull(extent)) {
				extent.flush();
			}
			ExtentManager.unload();
			try {
				Desktop.getDesktop().browse(new File(ConstantPaths.getExtentReportFilePath()).toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void createTest(String testCaseName) {
			ExtentManager.setExtentTest(extent.createTest(testCaseName));
		}

}
