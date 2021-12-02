package com.monefy;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MasterAutomationScriptSteps {

	private String Reference = "";
	private String AutomationScriptReference = "";
	private ArrayList<String> StepKeyword = new ArrayList<String>();
	private String ConfigData = "";
	private ArrayList<String> StepGroup = new ArrayList<String>();
	private LinkedHashMap<String, String> KeywordGroup = new LinkedHashMap<>();
	
	
	
	public LinkedHashMap<String, String> getKeywordGroup() {
		return KeywordGroup;
	}

	public void setKeywordGroup(LinkedHashMap<String, String> keywordGroup) {
		KeywordGroup = keywordGroup;
	}

	private double SerialNumber;
	public double getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(double serialNumber) {
		SerialNumber = serialNumber;
	}

	private Integer ExecutionSequence;
	
	
	
	public Integer getExecutionSequence() {
		return ExecutionSequence;
	}

	public void setExecutionSequence(Integer executionSequence) {
		ExecutionSequence = executionSequence;
	}

	private String SkipStep = "";
	private String ToBeExecutedByMachine = "";

	public String getToBeExecutedByMachine() {
		return ToBeExecutedByMachine;
	}

	public void setToBeExecutedByMachine(String toBeExecutedByMachine) {
		ToBeExecutedByMachine = toBeExecutedByMachine;
	}

	public String getSkipStep() {
		return SkipStep;
	}

	public void setSkipStep(String skipStep) {
		SkipStep = skipStep;
	}


	public ArrayList<String> getStepGroup() {
		return StepGroup;
	}

	public void setStepGroup(String stepGroup) {
		StepGroup.add(stepGroup);
	}
	
	
	public ArrayList<String> getStepKeyword() {
		return StepKeyword;
	}

	public void setStepKeyword(String stepKeyword) {
		StepKeyword.add(stepKeyword);
	}
	
	public String getConfigData() {
		return ConfigData;
	}

	public void setConfigData(String configData) {
		ConfigData = configData;
	}



	public String getReference() {
		return Reference;
	}

	public void setReference(String reference) {
		Reference = reference;
	}

	public String getAutomationScriptReference() {
		return AutomationScriptReference;
	}

	public void setAutomationScriptReference(String automationScriptReference) {
		AutomationScriptReference = automationScriptReference;
	}

}
