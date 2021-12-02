package com.MonefyTest;

import java.text.SimpleDateFormat;

import com.monefy.Uploader;

public class TestLauncher {
	public static String excutionFolderHTML="";	
	public static String excutionFolderEXCELFile="";	
	
	
	public static void main(String[] args) throws Exception {
		//BasicConfigurator.configure();
		
		java.util.Date date=new java.util.Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
		String folderDate=simpleDateFormat.format(date);
		excutionFolderHTML = System.getProperty("user.dir") + "\\custom-reports\\HTML-REPORT\\"+ folderDate;
		excutionFolderEXCELFile= System.getProperty("user.dir") + "\\custom-reports\\EXCEL-REPORT\\"+ folderDate+".xlsx";
 		
		Uploader uploader = new Uploader();		
		uploader.createExecutionRecords();
		uploader.launchExecution();
		
		
		
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("  Your Exectution for HTML Reports   "  + excutionFolderHTML);
		System.out.println("  Your Exectution for EXCEL Reports   "  + excutionFolderEXCELFile);
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
	}
}