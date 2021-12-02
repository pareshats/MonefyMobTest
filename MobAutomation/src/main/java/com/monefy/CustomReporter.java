package com.monefy;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomReporter extends CustomReporterHelper implements ITestListener {
	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
		this.startExcelReport(result, "Pass");
	}

	public void onTestFailure(ITestResult result) {
		this.startExcelReport(result, "Fail");
	}

	public void onTestSkipped(ITestResult result) {
		this.startExcelReport(result, "Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
}