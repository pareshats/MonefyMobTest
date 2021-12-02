package com.monefy;

public class TestSuites {
	private String references = "";
	private String MasterTestSuiteReference = "";
	private String ModuleReference = "";
	private String Description = "";
	private String RepositoryFile = "";
	// private String ExecutionPriority = "";
	private Double ExecutionPriority;
	private String TestSuiteConfigurationReference = "";
	private String ExecutionMode = "";
	private Double SerialNumber;
	private String ExecuteTestSuite = "";
	private String ExecutionStatus = "";
	private String StartTime = "";
	private String EndTime = "";
	private String TestDataFilePath = "";

	public Double getExecutionPriority() {
		return ExecutionPriority;
	}

	public void setExecutionPriority(Double executionPriority) {
		ExecutionPriority = executionPriority;
	}

	public Double getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(Double serialNumber) {
		SerialNumber = serialNumber;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}

	public String getMasterTestSuiteReference() {
		return MasterTestSuiteReference;
	}

	public void setMasterTestSuiteReference(String masterTestSuiteReference) {
		MasterTestSuiteReference = masterTestSuiteReference;
	}

	public String getModuleReference() {
		return ModuleReference;
	}

	public void setModuleReference(String moduleReference) {
		ModuleReference = moduleReference;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getRepositoryFile() {
		return RepositoryFile;
	}

	public void setRepositoryFile(String repositoryFile) {
		RepositoryFile = repositoryFile;
	}

	// public String getExecutionPriority() {
	// return ExecutionPriority;
	// }
	//
	// public void setExecutionPriority(String executionPriority) {
	// ExecutionPriority = executionPriority;
	// }

	public String getTestSuiteConfigurationReference() {
		return TestSuiteConfigurationReference;
	}

	public void setTestSuiteConfigurationReference(String testSuiteConfigurationReference) {
		TestSuiteConfigurationReference = testSuiteConfigurationReference;
	}

	public String getExecutionMode() {
		return ExecutionMode;
	}

	public void setExecutionMode(String executionMode) {
		ExecutionMode = executionMode;
	}

	// public String getSerialNumber() {
	// return SerialNumber;
	// }
	//
	// public void setSerialNumber(String serialNumber) {
	// SerialNumber = serialNumber;
	// }

	public String getExecuteTestSuite() {
		return ExecuteTestSuite;
	}

	public void setExecuteTestSuite(String executeTestSuite) {
		ExecuteTestSuite = executeTestSuite;
	}

	public String getExecutionStatus() {
		return ExecutionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		ExecutionStatus = executionStatus;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getTestDataFilePath() {
		return TestDataFilePath;
	}

	public void setTestDataFilePath(String testDataFilePath) {
		TestDataFilePath = testDataFilePath;
	}

}
