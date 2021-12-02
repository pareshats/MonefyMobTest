package com.monefy;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

public class EntityRunner {
	Recordset recordset;
	synchronized public static Recordset getEntityObject(String SheetName, Pojo objPojo,String stepGroup) {
		Recordset recordset = null;
		objPojo.setRunningSheetName(SheetName);
		String query = "Select * from " + SheetName + " where TestScenario ='" + objPojo.getTestCaseID() + "' and StepGroup = '"+stepGroup+"'";
		System.out.println(query);
		try {
			recordset = Uploader.testDataDBConnection.executeQuery(query);
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return recordset;
	}
	

	public EntityRunner(Recordset recordset) {
		this.recordset = recordset;
	}

	public boolean getBooleanValueForField(String FieldName) {
		boolean testData = false;
		try {
			if (recordset.getField(FieldName).equalsIgnoreCase("1")) 
			{
				testData = true;
				return testData;
			}
		} catch (FilloException e) {
			return testData;
		}
		return testData;
	}

	public String getStringValueForField(String FieldName) {
		String testData = null;
		try {
			testData = recordset.getField(FieldName);
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return testData;
	}

	public String setStringValueForField(String name, String value) {
		return value;
	}

	public String getAction() {
		return getStringValueForField("Action");
	}
//	
//	public void getGroupAction() {
//		String x= getStringValueForField("StepGroup");
//		System.out.println(x);
//		
//	
//	}
	
}
