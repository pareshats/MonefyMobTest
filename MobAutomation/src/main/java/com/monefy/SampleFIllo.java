package com.monefy;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class SampleFIllo {

	public static void main(String[] args) throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(
				"/Users/aqmuser/Documents/NSE_Mobile_Automation/NSE_Mobile_TestData/TestData/NseMobileAutomation copy.xlsx");
		String query = "Update TableUploadMaster123 Set LowerCircuitValue = 'abc' where (TCID='TCID001') AND (TestDataFileName = 'DashboardEntity' OR TestDataFileName = 'QuoteEntity')";
		connection.executeUpdate(query);
		connection.close();

	}

}
