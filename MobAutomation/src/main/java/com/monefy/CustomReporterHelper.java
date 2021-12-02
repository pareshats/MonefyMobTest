package com.monefy;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

import com.MonefyTest.TestLauncher;

public class CustomReporterHelper extends Pojo {
	String strExcelFilePath = "";
	private XSSFWorkbook workbook;
	private XSSFSheet summarySheet;
	private XSSFRow summaryCurrentRow;
	int intSummarySheetRowCounter = 0;
	private XSSFSheet testResultSheet;
	private XSSFRow testResultSheetCurrentRow;
	int intTestResultRowCounter = 0;
	int intSkipSheetRowCounter = 0;
	String developer = "Undefined";
	String customError = "";
	Pojo objPojo;

	public void startExcelReport(ITestResult result, String status) {
		
		try {
			
			String moduleName = result.getTestContext().getName();
			String completeTestClassPath = result.getTestClass().getName();
			String packageName = completeTestClassPath.substring(0, completeTestClassPath.lastIndexOf("."));
			String testClassName = result.getTestClass().getRealClass().getSimpleName();
			String methodName = result.getMethod().getMethodName();
			String sample = TestLauncher.excutionFolderEXCELFile;
			double executionTime = (double) (result.getEndMillis() - result.getStartMillis()) / 1000.0D;
			objPojo = (Pojo) result.getInstance();
			String TCID = objPojo.getTestCaseID();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String reportDate = dateFormat.format(date).toString();
			if (strExcelFilePath.equals(""))
				//this.strExcelFilePath = System.getProperty("user.dir") + "/custom-reports/EXCEL-REPORT/" + reportDate + ".xlsx";
				this.strExcelFilePath = TestLauncher.excutionFolderEXCELFile;
			
			File reportFile = new File(this.strExcelFilePath);
			String exception;
			Thread.sleep(1200);
			if (!reportFile.exists()) {
				(new File(System.getProperty("user.dir") + "/Excel_reports")).mkdirs();
				reportFile.createNewFile();
				exception = System.getProperty("user.dir") + "/src/main/resources/reportTemplate.xlsx";
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(exception));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.summaryCurrentRow = this.summarySheet.getRow(1);
				this.testResultSheet = this.workbook.createSheet("TestResult");
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(0);
				this.createHeaderRow(this.testResultSheetCurrentRow);
			} else {
				this.workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(this.strExcelFilePath));
				this.summarySheet = this.workbook.getSheet("TestSummary");
				this.summaryCurrentRow = this.summarySheet.getRow(1);
				this.testResultSheet = this.workbook.getSheet("TestResult");
			}

			this.workbook.setSheetOrder("TestResult", 0);
			this.workbook.setSheetOrder("TestSummary", 1);
			exception = "";
			if (status.equalsIgnoreCase("pass") || status.equalsIgnoreCase("fail")
					|| status.equalsIgnoreCase("skipped")) {
				this.intTestResultRowCounter = this.testResultSheet.getLastRowNum() + 1;
				this.testResultSheetCurrentRow = this.testResultSheet.createRow(this.intTestResultRowCounter);
				if (status.equalsIgnoreCase("fail")) {
					exception = result.getThrowable().getClass().getName() + ":" + result.getThrowable().getMessage();
					System.out.println("exception" + exception);
					System.out.println("Exception for +" + TCID + " - " + exception);
					System.out.println("Exception for M +" + TCID + " - " + result.getThrowable().getMessage());
				}
				if (status.equalsIgnoreCase("skipped"))
					exception = "Pre-Condition Failed";
				this.addExecutionResultInTestResultSheet(TCID, packageName, testClassName, methodName, exception,
						String.valueOf(executionTime), status, moduleName, this.developer, this.customError);
			}
			this.updateSummarySheet(status);
			this.endExcelReport();
		} catch (Exception var18) {
			var18.printStackTrace();
		}

	}

	public void createHeaderRow(XSSFRow headerRow) {
		this.createHeaderCell(headerRow, 0, "Sr. No.");
		this.createHeaderCell(headerRow, 1, "Time Stamp");
		this.createHeaderCell(headerRow, 2, "TCID");
		this.createHeaderCell(headerRow, 3, "Test Case Description");
		this.createHeaderCell(headerRow, 4, "Result");
		//this.createHeaderCell(headerRow, 5, "Exception");
		this.createHeaderCell(headerRow, 5, "Execution Time");
		this.createHeaderCell(headerRow, 6, "Resource Name");
		this.createHeaderCell(headerRow, 7, "Custom Error");
	}

	public void addExecutionResultInTestResultSheet(String TCID, String module, String className, String methodName,
			String exception, String executionTime, String result, String moduleName, String developer,
			String customError) {
		this.createSheetCellRightAlliged(this.testResultSheetCurrentRow, 0,
				String.valueOf(this.intTestResultRowCounter));
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 1, this.getCurrentDateTime());
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 2, moduleName);
		this.createSheetCell(this.testResultSheetCurrentRow, 3, objPojo.getTestStepDescription());
		this.createResultCell(this.testResultSheetCurrentRow, 4, result);
		//this.createSheetCell(this.testResultSheetCurrentRow, 5, exception);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 5, executionTime);
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 6, objPojo.getResourceName());
		this.createSheetCellCenterAlliged(this.testResultSheetCurrentRow, 7, objPojo.getCustomException());
		objPojo.setCustomException("");
	}

	public void updateSummarySheet(String status) {
		int skippedCount;
		if (status.equalsIgnoreCase("pass")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(0).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 0, String.valueOf(skippedCount));
		} else if (status.equalsIgnoreCase("fail")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(1).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 1, String.valueOf(skippedCount));
		} else if (status.equalsIgnoreCase("skipped")) {
			skippedCount = (int) this.summaryCurrentRow.getCell(2).getNumericCellValue() + 1;
			this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 2, String.valueOf(skippedCount));
		}

		skippedCount = (int) this.summaryCurrentRow.getCell(3).getNumericCellValue() + 1;
		this.createSheetCellCenterAlligedNumeric(this.summaryCurrentRow, 3, String.valueOf(skippedCount));
	}

	public void endExcelReport() throws IOException {
		this.autoSetColumnWidth();
		FileOutputStream fileOutputStream = new FileOutputStream(this.strExcelFilePath);
		this.workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	public void createSheetCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleLeftAlliged());
	}

	public void createSheetCellCenterAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
	}

	public void createSheetCellCenterAlligedNumeric(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue((double) Integer.parseInt(value));
		cell.setCellStyle(this.getCellStyleCenterAlliged());
	}

	public void createSheetCellRightAlliged(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getCellStyleRightAlliged());
	}

	public void createSheetPassStepCell(XSSFRow row, int cellNumber) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString("PASS"));
		cell.setCellStyle(this.getPassCellStyle());
	}

	public void createResultCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		System.out.println("Result ****************:     " + value.toLowerCase().trim());
		String var5 = value.toLowerCase().trim();
		byte var6 = -1;
		switch (var5.hashCode()) {
		case -1988746795:
			if (var5.equals("fail - Application Side Issue")) {
				var6 = 3;
			}
			break;
		case -1643861444:
			if (var5.equals("fail - Application Freeze")) {
				var6 = 2;
			}
			break;
		case 3135262:
			if (var5.equals("fail")) {
				var6 = 1;
			}
			break;
		case 3433489:
			if (var5.equals("pass")) {
				var6 = 0;
			}
			break;
		case 154432742:
			if (var5.equals("fail - Tool Limitations")) {
				var6 = 7;
			}
			break;
		case 1534758069:
			if (var5.equals("fail - Assertion Error")) {
				var6 = 4;
			}
			break;
		case 1872143336:
			if (var5.equals("fail - UI Change/Element Changed")) {
				var6 = 5;
			}
			break;
		case 1879267419:
			if (var5.equals("fail - Developer Side Issue")) {
				var6 = 6;
			}
			break;
		case 2147444528:
			if (var5.equals("skipped")) {
				var6 = 8;
			}
		}

		switch (var6) {
		case 0:
			cell.setCellStyle(this.getPassCellStyle());
			break;
		case 1:
		case 2:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 3:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 4:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 5:
			cell.setCellStyle(this.getFailCellStyle());
			break;
		case 6:
			cell.setCellStyle(this.getSeleniumSideFailCellStyle());
			break;
		case 7:
			cell.setCellStyle(this.getSeleniumSideFailCellStyle());
			break;
		case 8:
			cell.setCellStyle(this.getSkippedCellStyle());
		}

	}

	public void createHeaderCell(XSSFRow row, int cellNumber, String value) {
		XSSFCell cell = row.createCell(cellNumber);
		cell.setCellValue(new XSSFRichTextString(value));
		cell.setCellStyle(this.getHeaderCellStyle());
	}

	// public void createWelcomeHeaderCell(XSSFRow row, int cellNumber, String
	// value) {
	// XSSFCell cell = row.createCell(cellNumber);
	// cell.setCellValue(new XSSFRichTextString(value));
	// cell.setCellStyle(this.getWelcomeCellStyle());
	// }

	private XSSFCellStyle getCellStyleLeftAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleCenterAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		return cellStyle;
	}

	private XSSFCellStyle getCellStyleRightAlliged() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.RIGHT);
		return cellStyle;
	}

	private XSSFCellStyle getPassCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		XSSFColor myColor = new XSSFColor(Color.GREEN);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		XSSFColor myColor = new XSSFColor(Color.RED);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getSeleniumSideFailCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		XSSFColor myColor = new XSSFColor(Color.ORANGE);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getSkippedCellStyle() {
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		XSSFColor myColor = new XSSFColor(Color.LIGHT_GRAY);
		cellStyle.setFillForegroundColor(myColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	private XSSFCellStyle getHeaderCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setFillForegroundColor((short) 22);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}

	public XSSFCellStyle getWelcomeCellStyle() {
		XSSFFont headerFont = this.workbook.createFont();
		// headerFont.setBoldweight((short) 700);
		headerFont.setFontName("Arial");
		XSSFCellStyle cellStyle = this.workbook.createCellStyle();
		cellStyle.setFont(headerFont);
		// cellStyle.setAlignment((short) 2);
		// cellStyle.setBorderBottom((short) 1);
		// cellStyle.setBorderLeft((short) 1);
		// cellStyle.setBorderRight((short) 1);
		// cellStyle.setBorderTop((short) 1);
		// cellStyle.setFillPattern((short) 1);
		XSSFColor myColor = new XSSFColor(Color.GREEN);
		cellStyle.setFillForegroundColor(myColor);
		// cellStyle.setFillPattern((short) 1);
		return cellStyle;
	}

	// public void createSummaryLinkCell(XSSFRow row, int cellNumber, String value,
	// String nevigationSheet) {
	// XSSFCell cell = row.createCell(cellNumber);
	// cell.setCellValue(new XSSFRichTextString(value));
	// CreationHelper createHelper = this.workbook.getCreationHelper();
	// Hyperlink link = createHelper.createHyperlink(2);
	// link.setAddress("'" + nevigationSheet + "'!A1");
	// cell.setHyperlink(link);
	// cell.setCellStyle(this.getHlinkCellStyle());
	// }

	// public XSSFCellStyle getHlinkCellStyle() {
	// XSSFCellStyle cellStyle = this.workbook.createCellStyle();
	// Font hlink_font = this.workbook.createFont();
	// hlink_font.setUnderline((byte) 1);
	// hlink_font.setColor(IndexedColors.BLUE.getIndex());
	// hlink_font.setBoldweight((short) 700);
	// hlink_font.setFontName("Arial");
	// cellStyle.setFont(hlink_font);
	// cellStyle.setAlignment((short) 2);
	// cellStyle.setBorderBottom((short) 1);
	// cellStyle.setBorderLeft((short) 1);
	// cellStyle.setBorderRight((short) 1);
	// cellStyle.setBorderTop((short) 1);
	// cellStyle.setFillForegroundColor((short) 22);
	// cellStyle.setFillPattern((short) 1);
	// return cellStyle;
	// }

	private String getCurrentDateTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		return sdfDate.format(now);
	}

	public void autoSetColumnWidth() {
		this.testResultSheet.autoSizeColumn(0);
		this.testResultSheet.autoSizeColumn(1);
		this.testResultSheet.autoSizeColumn(2);
		this.testResultSheet.autoSizeColumn(3);
		this.testResultSheet.autoSizeColumn(4);
		this.testResultSheet.autoSizeColumn(5);
		this.testResultSheet.autoSizeColumn(6);
		this.testResultSheet.autoSizeColumn(7);
		this.testResultSheet.autoSizeColumn(8);
		this.testResultSheet.autoSizeColumn(9);
		this.testResultSheet.autoSizeColumn(10);
		this.testResultSheet.autoSizeColumn(11);
		this.testResultSheet.autoSizeColumn(12);
		this.testResultSheet.autoSizeColumn(13);
	}

}