package com.monefy;

import com.codoid.products.fillo.Recordset;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Pojo {

	MasterAutomationScriptSteps masterAutomationScriptSteps;

	private Recordset recordset;
	private AppiumDriver<MobileElement> driver;
	private String TCID = "";
	private String customException;
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	private String RunningSheetName;

	private String executionDescription;

	private String executionAutomationScripterName;

	public EntityRunner getEntityRunner() {
		return new EntityRunner(recordset);
	}

	public void setEntityRunner(Recordset recordset) {
		this.recordset = recordset;
	}

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	public void setDriver(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public String getTestCaseID() {
		return TCID;
	}

	public void setTestCaseID(String tCID) {
		TCID = tCID;
	}

	public void setCustomException(String string) {
		customException = string;
	}

	public String getCustomException() {
		return customException;
	}

	public Utilities getObjUtilities() {
		return objUtilities;
	}

	public void setObjUtilities(Utilities objUtilities) {
		this.objUtilities = objUtilities;
	}

	public WrapperFunctions getObjWrapperFunctions() {
		return objWrapperFunctions;
	}

	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
		this.objWrapperFunctions = objWrapperFunctions;
	}

	public void setRunningSheetName(String sheetName) {
		this.RunningSheetName = sheetName;

	}

	public String getRunningSheetName() {
		return RunningSheetName;
	}

	public void setTestStepDescription(String executionDescription) {
		this.executionDescription = executionDescription;

	}

	public String getTestStepDescription() {
		return executionDescription;
	}

	public void setResourceName(String executionAutomationScripterName) {
		this.executionAutomationScripterName = executionAutomationScripterName;

	}

	public String getResourceName() {
		return executionAutomationScripterName;

	}
}
