package com.monefy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.MonefyTest.TestLauncher;
import com.codoid.products.exception.FilloException;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;


public class Utilities {
	private Pojo objPojo;

	public Utilities(Pojo pojo) {
		this.objPojo = pojo;
	}

	// @Step("{0}")
	public void logReporter(String step, boolean resultLog)
	{
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}


	// @Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog)
	{
		String strLog = step + " || Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	// @Step("{0} - {1} - {2}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog)
	{
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
	}

	
	public void CustomLogger(String Customtext)
	{
		
		Logger logger = Logger.getLogger(Utilities.class);
		Reporter.log("Step Description--> ");
	}
	
	
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println(objPojo.getTestCaseID() + "--> " + strLog);
		Logger logger = Logger.getLogger(Utilities.class);
		Properties props = new Properties();
		try {
			// props.load(new FileInputStream("src\\test\\resources\\log4j.properties"));
			//props.load(new FileInputStream("C:\\Users\\Nishant\\Desktop\\Quosphere\\SpiritZone\\config.properties"));
			
			props.load(new FileInputStream(System.getProperty("user.dir")+"/config.properties"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultLog) {
			Allure.step(strLog, Status.PASSED);
			Reporter.log("Step Description--> " + strLog);
			logger.info("Step Description--> " + objPojo.getTestCaseID() + "---" + strLog);
			Assert.assertTrue(true);
		} else 
		{
			Allure.step(strLog, Status.FAILED);
			java.util.Date date=new java.util.Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			String folderDate=simpleDateFormat.format(date);
			String timeStamp = new SimpleDateFormat("dd-yyyy-MM----HH:mm:ss").format(new Date());
			String Condition = "";
			//String outputDIR = "C:\\Users\\Nishant\\Desktop\\Quosphere\\SpiritZone\\custom-reports\\HTML-REPORT\\"+folderDate;
			//String outputDIR = System.getProperty("user.dir") + "/custom-reports/EXCEL-REPORT/" +folderDate;
			
			String fileName =TestLauncher.excutionFolderHTML+"\\SpiritZone\\Failed-ScreenShot\\"+ "_TCID_" + objPojo.getTestCaseID()+ ".png";
			// String fileWithPath = Pojo.reportPath + "\\surefire-reports\\ScreenShot\\" +
			// fileName;
			String fileWithPath = fileName;
			Reporter.log("Step Description--> " + strLog);
			logger.error("Step Description--> " + objPojo.getTestCaseID() + "---" + strLog);
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			Assert.assertTrue(false);
		}
	}


	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		try {
			FileUtils.moveFile(srcFile, destFile);
			// this.fileToByte(destFile);
			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;

		}
	}

	
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		return current_date;
	}

	public String getRandomString(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring.toUpperCase();
	}


	public String getRandomNumbers(int length) {
		String allowedChars = "1234567890";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}


	public int getRandomNumbersWithinRange(int minimumLimt, int maximumLimit) {
		try {
			Random rand = new Random();
			return rand.nextInt((maximumLimit - minimumLimt) + 1) + minimumLimt;
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			exception.printStackTrace();
			return 0;
		}
	}

	public String TickValue(String normalValue) {
		String tickValue = new String();
		String a = null;
		String[] decimal1 = normalValue.split("\\.");
		int point = Integer.parseInt(decimal1[1]);
		int point1 = Integer.parseInt(decimal1[0]);
		int rev = point % 10;
		if (rev != 0 && rev != 5) {
			if (rev <= 4)
				rev = 5;
			else
				rev = 10;
			point = (((point / 10) * 10) + rev);
			if (point == 100) {
				point1 += 1;
				point = 0;
			}
		} else
			point = 0;
		rev = point1 % 10;
		if (rev % 2 != 0) {
			int rev1 = point1 + 1;
			return tickValue = rev1 + "." + Integer.toString(point);
		} else
			return tickValue = point1 + "." + Integer.toString(point);

	}

	public void setValueIntoDatbase(String string, String string2, String testData, String s) {
		 String[] keySeparator = testData.split(",");
		String TCID = objPojo.getTestCaseID();
		System.out.println(TCID);
		 for (int i = 0; i < keySeparator.length; i++) {		
		String query = "Update "+"DT_PlaceOrderEntity"+" Set " + string + "= '" + s + "'," + string2 + "= '" + s
				+ "' where TestScenario='" + TCID + "'";

		System.out.println(query);
		try {
			Uploader.testDataDBConnection.executeUpdate(query);
			System.out.println("sucess");

		} catch (FilloException e) {
			e.printStackTrace();
		}
		}
	}
}
