package com.monefy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.MonefyTest.TestLauncher;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class Uploader {

	public static Properties prop;
	public static String currentExecutionMasterTestSuiteExcelFilePath = "";
	public static String TestScanerioExcelFilePath = "";
	public static String TestDataExcelFilePath = "";


	public String TestDataDirectory = "";

	private InputStream input;

	private Properties tableConfigProp;
	private InputStream tableConfigInput;

	public File currentTestAutomationSuitesAndTestDataFolderReference;

	// private String remoteMasterTestSuiteFolderForExecution;

	// ARRAYLIST FOR testsuite sheet
	private ArrayList<TestSuites> arrTestSuites = new ArrayList<TestSuites>();

	// ARRAYLIST FOR MasterTestSuites sheet
	private ArrayList<MasterTestSuites> arrMasterTestSuites = new ArrayList<MasterTestSuites>();

	// ARRAYLIST FOR MasterAutomationScripts sheet
	private ArrayList<MasterAutomationScripts> arrMasterAutomationScripts = new ArrayList<MasterAutomationScripts>();

	public static Connection testDataDBConnection;

	// ARRAYLIST FOR MasterAutomationScriptSteps sheet
	public static Hashtable<String, MasterAutomationScriptSteps> arrMasterAutomationScriptSteps = new Hashtable<String, MasterAutomationScriptSteps>();

	// private ArrayList<MasterAutomationScriptSteps> arrMasterAutomationScriptSteps
	// = new ArrayList<MasterAutomationScriptSteps>();

	public static String currentExecutionResultsFolder;

	public Uploader() {
		loadProperties();
		this.TestDataDirectory = prop.getProperty("TestdataDirectory");

	}

	private void loadProperties() {
		prop = new Properties();
		try {
			input = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
			tableConfigProp = new Properties();
			tableConfigInput = new FileInputStream(System.getProperty("user.dir")+"/tableconfig.properties");
			tableConfigProp.load(tableConfigInput);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getExecutionResultsBaseFolder() {
		return System.getProperty("user.dir");
	}

	@SuppressWarnings("deprecation")
	public void launchExecution()
	{

		for (int i = 0; i < arrTestSuites.size(); i++) 
		{
			// ARRAYLIST FOR TestScanerio WorkBook/Sheet
			ArrayList<TestScenarios> arrTestScenarios = new ArrayList<TestScenarios>();

			TestSuites runningTestSuite = arrTestSuites.get(i);

			if (runningTestSuite.getExecuteTestSuite().equalsIgnoreCase("yes")) {
				TestNG testNGReference = new TestNG();
				List<XmlSuite> suites = new ArrayList<XmlSuite>();
				XmlSuite suite = new XmlSuite();

				// suite.setName(runningTestSuite.getDescription());

				suite.setName(runningTestSuite.getDescription().toString());


				currentExecutionMasterTestSuiteExcelFilePath = this.TestDataDirectory + "/";
				String testSuiteFilePath = currentExecutionMasterTestSuiteExcelFilePath
						+ runningTestSuite.getRepositoryFile();

				// System.out.println(testSuiteFilePath + "----testSuiteFilePath");

				XSSFWorkbook runningTestScenario = null;
				try {
					runningTestScenario = new XSSFWorkbook(new File(testSuiteFilePath));
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				Fillo fillo = new Fillo();
				try {
					// System.out.println(this.TestDataDirectory +
					// runningTestSuite.getTestDataFilePath());
					testDataDBConnection = fillo
							.getConnection(this.TestDataDirectory + "/" + runningTestSuite.getTestDataFilePath());
				} catch (FilloException e) {
					e.printStackTrace();
					System.exit(0);
				}

				XSSFSheet ScenarioSheet = runningTestScenario.getSheet("TestScenarios");

				for (int j = 1; j < ScenarioSheet.getLastRowNum(); j++) {
					TestScenarios runningScenarios = new TestScenarios();

					runningScenarios.setReference(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioReferenceColumn")))
							.getStringCellValue());
					runningScenarios.setPrerequisiteTestSuiteReference(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(
									tableConfigProp.getProperty("TestScenarioPrerequisiteTestSuiteReferenceColumn")))
							.getStringCellValue());
					runningScenarios.setPrerequisiteTestScenarioReference(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(
									tableConfigProp.getProperty("TestScenarioPrerequisiteTestScenarioReferenceColumn")))
							.getStringCellValue());
					runningScenarios.setDescription(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioDescriptionColumn")))
							.getStringCellValue());
					runningScenarios.setAutomationScriptReference(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(
									tableConfigProp.getProperty("TestScenarioAutomationScriptReferenceColumn")))
							.getStringCellValue());
					runningScenarios.setSerialNumber(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioSerialNumberColumn")))
							.getNumericCellValue());

					runningScenarios.setExecuteTestScenario(ScenarioSheet.getRow(j)
							.getCell(Integer
									.parseInt(tableConfigProp.getProperty("TestScenarioExecuteTestScenarioColumn")))
							.getStringCellValue());

					runningScenarios.setExecutionStatus(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecutionStatusColumn")))
							.getStringCellValue());

					runningScenarios.setAutomationScripterName(ScenarioSheet.getRow(j)
							.getCell(Integer
									.parseInt(tableConfigProp.getProperty("TestScenarioAutomationScripterNameColumn")))
							.getStringCellValue());

					runningScenarios.setLogFile(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioLogFileColumn")))
							.getStringCellValue());

					runningScenarios.setStartTime(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioStartTimeColumn")))
							.getStringCellValue());

					runningScenarios.setEndTime(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioEndTimeColumn")))
							.getStringCellValue());

					runningScenarios.setRemarks(ScenarioSheet.getRow(j)
							.getCell(Integer.parseInt(tableConfigProp.getProperty("TestScenarioRemarksColumn")))
							.getStringCellValue());

					arrTestScenarios.add(runningScenarios);
				}
				for (TestScenarios scenario : arrTestScenarios) {
					if (scenario.getExecuteTestScenario().equalsIgnoreCase("yes")) {
						XmlTest test = new XmlTest(suite);
						test.setName(scenario.getReference());
						test.setVerbose(2);
						Map<String, String> parameters = new HashMap<String, String>();
						parameters.put("executionTestSuite_Reference", scenario.getReference());
						parameters.put("executionAutomationScriptReference", scenario.getAutomationScriptReference());
						parameters.put("executionAutomationScripterName", scenario.getAutomationScripterName());
						parameters.put("executionAutomationDescription", scenario.getDescription());
						// test.setParameters(parameters);
						XmlClass testClass = new XmlClass("com.monefy.AutomationDriverScript");
						testClass.setParameters(parameters);
						// testClass.setParameters(parameters);

						// TestClass runnerClass=new Testcl
						List<XmlClass> classes = new ArrayList<XmlClass>();
						classes.add(testClass);
						test.setXmlClasses(classes);
					}
				}
				suites.add(suite);
				testNGReference.setXmlSuites(suites);
				testNGReference.addListener(new CustomReporter());
				java.util.Date date=new java.util.Date();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
				String folderDate=simpleDateFormat.format(date);
				String timeStamp = new SimpleDateFormat("dd-yyyy-MM----HH:mm:ss").format(new Date());
				String Condition = "";
				String outputDIR = TestLauncher.excutionFolderHTML;
				testNGReference.setOutputDirectory(outputDIR);
				try {
					testNGReference.run();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

	}

	public void createExecutionRecords() 
	{
		FileInputStream currentExecutionMasterTestSuiteFileInputStreamReference = null;

		currentExecutionMasterTestSuiteExcelFilePath = this.TestDataDirectory + "/"
				+ prop.getProperty("BaseMasterTestSuiteFileForExecution");
		File currentExecutionMasterTestSuiteExcelFileReference = new File(currentExecutionMasterTestSuiteExcelFilePath);
		// System.out.println(currentExecutionMasterTestSuiteExcelFilePath + "\n
		// ------------");

		try {
			
			//currentExecutionMasterTestSuiteFileInputStreamReference = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\0000_MasterTestSuite.xlsx");
			currentExecutionMasterTestSuiteFileInputStreamReference = new FileInputStream("/Users/pareshparmar/Documents/AndroidTestData/0000_MasterTestSuite.xlsx");
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		XSSFWorkbook currentExecutionMasterTestSuiteWorkBookReference = null;
		try {
			currentExecutionMasterTestSuiteWorkBookReference = new XSSFWorkbook(
					currentExecutionMasterTestSuiteFileInputStreamReference);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TestSuites

		XSSFSheet objTestSuites = currentExecutionMasterTestSuiteWorkBookReference.getSheet("TestSuites");

		for (int i = 1; i < objTestSuites.getLastRowNum(); i++) {
			String execSuite = objTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecuteTestSuiteColumn")))
					.getStringCellValue();

			if (execSuite.trim().equalsIgnoreCase("yes")) {
				TestSuites objTestSuite = new TestSuites();

				objTestSuite.setModuleReference(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteModuleReferenceColumn")))
						.getStringCellValue());

				objTestSuite.setMasterTestSuiteReference(objTestSuites.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("TestSuiteMasterTestSuiteReferenceColumn")))
						.getStringCellValue());

				objTestSuite.setDescription(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteDescriptionColumn")))
						.getStringCellValue());

				objTestSuite.setRepositoryFile(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteRepositoryFileColumn")))
						.getStringCellValue());
				objTestSuite.setTestDataFilePath(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteDataFilePath")))
						.getStringCellValue());
				// objTestSuite.setExecutionPriority(objTestSuites.getRow(i)
				// .getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionPriorityColumn")))
				// .getStringCellValue());
				objTestSuite.setExecutionPriority(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionPriorityColumn")))
						.getNumericCellValue());

				objTestSuite.setTestSuiteConfigurationReference(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteConfigurationReferenceColumn")))
						.getStringCellValue());

				objTestSuite.setExecutionMode(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionModeColumn")))
						.getStringCellValue());

				// objTestSuite.setSerialNumber(objTestSuites.getRow(i)
				// .getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteSerialNumberColumn")))
				// .getStringCellValue());

				objTestSuite.setSerialNumber(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteSerialNumberColumn")))
						.getNumericCellValue());

				objTestSuite.setExecuteTestSuite(execSuite);

				objTestSuite.setExecutionStatus(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionStatusColumn")))
						.getStringCellValue());

				objTestSuite.setStartTime(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteStartTimeColumn")))
						.getStringCellValue());

				objTestSuite.setEndTime(objTestSuites.getRow(i)
						.getCell(Integer.parseInt(tableConfigProp.getProperty("TestSuiteEndTimeColumn")))
						.getStringCellValue());

				arrTestSuites.add(objTestSuite);
			}
		}

		// MasterTestSuites
		XSSFSheet objMasterTestSuites = currentExecutionMasterTestSuiteWorkBookReference.getSheet("MasterTestSuites");
		for (int i = 1; i < objMasterTestSuites.getLastRowNum(); i++) {
			MasterTestSuites objMasterTestSuite = new MasterTestSuites();

			objMasterTestSuite.setReference(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteReferenceColumn")))
					.getStringCellValue());

			objMasterTestSuite.setApplicationReference(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteApplicationReferenceColumn")))
					.getStringCellValue());

			objMasterTestSuite.setDescription(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteDescriptionColumn")))
					.getStringCellValue());

			objMasterTestSuite.setSerialNumber(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteSerialNumberColumn")))
					.getStringCellValue());

			objMasterTestSuite.setExecuteMasterTestSuite(objMasterTestSuites.getRow(i)
					.getCell(Integer
							.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecuteMasterTestSuiteColumn")))
					.getStringCellValue());

			objMasterTestSuite.setExecutionStatus(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecutionStatusColumn")))
					.getStringCellValue());

			objMasterTestSuite.setStartTime(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteStartTimeColumn")))
					.getStringCellValue());

			objMasterTestSuite.setEndTime(objMasterTestSuites.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteEndTimeColumn")))
					.getStringCellValue());

			arrMasterTestSuites.add(objMasterTestSuite);
		}

		// MasterAutomationScripts

		XSSFSheet objMasterAutomationScripts = currentExecutionMasterTestSuiteWorkBookReference
				.getSheet("MasterAutomationScripts");
		for (int i = 1; i < objMasterAutomationScripts.getLastRowNum(); i++) {
			MasterAutomationScripts objMasterAutomationScript = new MasterAutomationScripts();
			objMasterAutomationScript.setReference(objMasterAutomationScripts.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptReferenceColumn")))
					.getStringCellValue());

			objMasterAutomationScript.setDescription(objMasterAutomationScripts.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptDescriptionColumn")))
					.getStringCellValue());

			objMasterAutomationScript.setSerialNumber(objMasterAutomationScripts.getRow(i)
					.getCell(Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptSerialNumberColumn")))
					.getNumericCellValue());
			arrMasterAutomationScripts.add(objMasterAutomationScript);
		}

		// MasterAutomationScriptSteps

		XSSFSheet objMasterAutomationScriptSteps = currentExecutionMasterTestSuiteWorkBookReference
				.getSheet("MasterAutomationScriptSteps");

		for (int i = 1; i < objMasterAutomationScriptSteps.getLastRowNum(); i++) {
			String automationScriptReference = objMasterAutomationScriptSteps.getRow(i)
					.getCell(Integer.parseInt(
							tableConfigProp.getProperty("MasterAutomationScriptStepAutomationScriptReferenceColumn")))
					.getStringCellValue();

			if (arrMasterAutomationScriptSteps.containsKey(automationScriptReference)) {

				arrMasterAutomationScriptSteps.get(automationScriptReference)
						.setStepKeyword(objMasterAutomationScriptSteps.getRow(i)
								.getCell(Integer.parseInt(
										tableConfigProp.getProperty("MasterAutomationScriptStepStepKeywordColumn")))
								.getStringCellValue());
			}

			if (arrMasterAutomationScriptSteps.containsKey(automationScriptReference)) {
				arrMasterAutomationScriptSteps.get(automationScriptReference)
						.setStepGroup(objMasterAutomationScriptSteps.getRow(i)
								.getCell(Integer.parseInt(
										tableConfigProp.getProperty("MasterAutomationScriptStepStepGroupColumn")))
								.getStringCellValue());

			}

			else {
				MasterAutomationScriptSteps objMasterAutomationScriptStep = new MasterAutomationScriptSteps();
				objMasterAutomationScriptStep.setReference(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepReferenceColumn")))
						.getStringCellValue());

				objMasterAutomationScriptStep.setAutomationScriptReference(automationScriptReference);

				objMasterAutomationScriptStep.setStepKeyword(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepStepKeywordColumn")))
						.getStringCellValue());

				objMasterAutomationScriptStep.setStepGroup(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepStepGroupColumn")))
						.getStringCellValue());

				// objMasterAutomationScriptStep.setConfigData(objMasterAutomationScriptSteps.getRow(i)
				// .getCell(Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepConfigDataColumn"))).getStringCellValue());

				objMasterAutomationScriptStep.setConfigData(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepConfigDataColumn")))
						.getStringCellValue());
				/*
				 * objMasterAutomationScriptStep.setStepGroup(objMasterAutomationScriptSteps.
				 * getRow(i) .getCell(Integer .parseInt(tableConfigProp.getProperty(
				 * "MasterAutomationScriptStepStepGroupColumn"))) .getStringCellValue());
				 */
				objMasterAutomationScriptStep.setSerialNumber(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepSerialNumberColumn")))
						.getNumericCellValue());

				objMasterAutomationScriptStep.setExecutionSequence((int) objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer.parseInt(
								tableConfigProp.getProperty("MasterAutomationScriptStepExecutionSequenceColumn")))
						.getNumericCellValue());

				objMasterAutomationScriptStep.setSkipStep(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer
								.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepSkipStepColumn")))
						.getStringCellValue());

				objMasterAutomationScriptStep.setToBeExecutedByMachine(objMasterAutomationScriptSteps.getRow(i)
						.getCell(Integer.parseInt(
								tableConfigProp.getProperty("MasterAutomationScriptStepToBeExecutedByMachineColumn")))
						.getStringCellValue());

				arrMasterAutomationScriptSteps.put(automationScriptReference, objMasterAutomationScriptStep);
			}

		}

	}

}
