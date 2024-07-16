package com.rajesh.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.rajesh.report.ExtentLogger;
import com.rajesh.report.ExtentReport;
import com.rajesh.utils.BrowserInfoUtils;
import com.rajesh.utils.OSInfoUtils;

import org.testng.*;

import java.util.Arrays;


public class ListenerClass implements ITestListener, ISuiteListener {

	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}

	@Override
	public void onTestStart(ITestResult result) {

		// System.out.println("onTestStart() ");
		count_totalTCs = count_totalTCs + 1;
		ExtentReport.createTest(result.getMethod().getMethodName());
		//ExtentReport.createTest(result.getMethod().getDescription());

		ExtentLogger.info("<b>" +
				OSInfoUtils.getOSInfo() + " & " + BrowserInfoUtils.getBrowserInfo() + " - "
				+ BrowserInfoUtils.getBrowserVersionInfo() + "</b>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		String logText = "<b>" + result.getMethod().getMethodName() + " is passed.</b>" + "  " + ":-)";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentLogger.pass(markup_message);
		ExtentLogger.pass();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		count_failedTCs = count_failedTCs + 1;
		ExtentLogger.fail("BUG" + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
				+ ":-(" + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
				+ "</details> \n";

		ExtentLogger.fail(message);

		String logText = "<b>" + result.getMethod().getMethodName() + " is failed.</b>" + "  " + ":-(";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentLogger.fail(markup_message, true);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		count_skippedTCs = count_skippedTCs + 1;

		ExtentLogger.skip("BUG" + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		// ExtentLogger.skip("<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ":-(";
		Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentLogger.skip(markup_message, true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * As of now, we are not using it
		 */
	}

	@Override
	public void onStart(ITestContext result) {
		/*
		 * As of now, we are not using it.
		 */
	}

	@Override
	public void onFinish(ITestContext result) {
		/*
		 * As of now, we are not using it.
		 */
	}

}
