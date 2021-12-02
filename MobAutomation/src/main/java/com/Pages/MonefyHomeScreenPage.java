package com.Pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.MonefyTest.TestLauncher;
import com.monefy.EntityRunner;
import com.monefy.Pojo;
import com.monefy.TestScenarios;
import com.monefy.WrapperFunctions;

public class MonefyHomeScreenPage {

	TestScenarios TestScanerio;
	Pojo objPojo;
	EntityRunner EntityRunner;


	By CarCategory;
	By ExpenseButton;
	By ExpenseSource;
	By cash;
	By paymentCard;
	By money;
	By note;
	By chooseCategory;
	By date;
	By accounntsDropDown;
	By DayTab;
	By Allaccounnts;
	By SettingsDot;
	By AccountsSetting;
	By addAccountsIcon;
	By amexCard;
	By AddName;
	By AddCatogary;
	By settingButton;
	By budgetModeCheckbox;
	By budgetAmountTextField;
	By okButton;
	By language;
	By languageRadioButton;
	By currency;

	By leftHamburger;
	public MonefyHomeScreenPage(Pojo objPojo) {
		this.objPojo = objPojo;
		EntityRunner = objPojo.getEntityRunner();

		CarCategory = By.xpath("//android.widget.TextView[@text='December']//following::android.widget.ImageView[1]");
		ExpenseSource=By.xpath("//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/icon']");
		paymentCard = By.xpath("//android.widget.TextView[@text='Payment card']");
		money= By.xpath("//android.widget.Button[@text='1']");
		note= By.xpath("//android.widget.EditText[@text='Note']");
		//chooseCategory =By.xpath("//aandroid.widget.Button[@resource-id='com.monefy.app.lite:id/keyboard_action_button']");
		chooseCategory =By.id("com.monefy.app.lite:id/keyboard_action_button");
		leftHamburger =By.xpath("//android.widget.ImageButton[@content-desc='Open navigation']");
		accounntsDropDown=By.xpath("//android.widget.TextView[@text='INR']");
		DayTab=By.xpath("//android.widget.RadioButton[@text='Day']");
		Allaccounnts=By.xpath("//android.widget.TextView[@text='All accounts']");
		SettingsDot =By.id("com.monefy.app.lite:id/overflow");
		AccountsSetting =By.id("com.monefy.app.lite:id/accounts_imagebutton");
		addAccountsIcon =By.id("com.monefy.app.lite:id/imageButtonAddCategory");
		amexCard =By.xpath("//android.widget.Switch[@text='Included in the balance Yes']//following::android.widget.ImageView[1]");
		AddName =By.id("com.monefy.app.lite:id/editTextCategoryName");
		AddCatogary =By.id("com.monefy.app.lite:id/save");
		settingButton =By.id("com.monefy.app.lite:id/settings_imagebutton");
		budgetModeCheckbox=By.id("com.monefy.app.lite:id/is_budget_mode_checkbox");
		budgetAmountTextField=By.xpath("//android.widget.EditText[@text='0']");
		okButton=By.id("android:id/button1");
		language=By.xpath("//android.widget.TextView[@text='Language']");
		languageRadioButton=By.xpath("//android.widget.TextView[@text='Select preferable language']//following::android.widget.CheckedTextView[1]");
		currency=By.xpath("//android.widget.TextView[@text='Currency']");
	}

	public void  monefyHomeExpensePageDetails() throws InterruptedException {

		WrapperFunctions GenericMethod = objPojo.getObjWrapperFunctions();
		//String HomePageLabel = objPojo.getEntityRunner().getStringValueForField("HomePageLabel");
		String IncomeCashValue = objPojo.getEntityRunner().getStringValueForField("IncomeCashValue");
		String IncomePaymentCardValue = objPojo.getEntityRunner().getStringValueForField("IncomePaymentCardValue");
		String NoteTextValue = objPojo.getEntityRunner().getStringValueForField("Notes");

		String Day = objPojo.getEntityRunner().getStringValueForField("Day");
		String Week = objPojo.getEntityRunner().getStringValueForField("Week");
		String Month = objPojo.getEntityRunner().getStringValueForField("Month");
		String Year = objPojo.getEntityRunner().getStringValueForField("Year");
		String All = objPojo.getEntityRunner().getStringValueForField("All");
		String Interval = objPojo.getEntityRunner().getStringValueForField("Interval");
		String AllAccount = objPojo.getEntityRunner().getStringValueForField("AllAccount");
		String Cash = objPojo.getEntityRunner().getStringValueForField("IncomeCashValue");
		String Card = objPojo.getEntityRunner().getStringValueForField("IncomePaymentCardValue");



		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigAddingExpence")) {
			Thread.sleep(2000);
			GenericMethod.clickException(CarCategory, " Categoty");
			if (objPojo.getEntityRunner().getBooleanValueForField("ConfigIncome")) {

				//GenericMethod.clickException(ExpenseButton, "Incoome");
				GenericMethod.clickException(ExpenseSource, "Incoome Source");
				Thread.sleep(2000);

				String CashActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Cash']")).getText();
				String CashExpected = IncomeCashValue;
				String PaymentCardActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Payment card']")).getText();
				String PaymentCardExpected = IncomePaymentCardValue;
				Thread.sleep(2000);
				if(CashActual.equalsIgnoreCase(CashExpected)){
					objPojo.getObjUtilities().logReporter("First income source is-"+IncomeCashValue, true);
				}else
				{
					Assert.assertEquals(false, true,"HomePage is not visible !");	

				}
				Thread.sleep(2000);
				if(PaymentCardActual.equalsIgnoreCase(PaymentCardExpected)){
					objPojo.getObjUtilities().logReporter("Second income source is-"+IncomePaymentCardValue, true);
				}else
				{
					Assert.assertEquals(false, true,"HomePage is not visible !");	

				}
				if (objPojo.getEntityRunner().getBooleanValueForField("ConfigCardPayment")) {
					GenericMethod.clickException(paymentCard, "Cash Income");
					GenericMethod.clickException(money, "Adding Money");
					GenericMethod.clickException(money, "Adding Money");
					//GenericMethod.clickException(money, "Adding Money");
					//GenericMethod.clickException(money, "Adding Money");

				}

				if (objPojo.getEntityRunner().getBooleanValueForField("ConfigNote")) {

					GenericMethod.clearAndSendKeysCustomException(note,  objPojo.getEntityRunner().getStringValueForField("Notes"), "Note");
					objPojo.getObjUtilities().logReporter("Note text is-"+NoteTextValue, true);
					Thread.sleep(2000);
					GenericMethod.clickException(chooseCategory, "Choose Category");
					Thread.sleep(2000);
					//String Date = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewDate']")).getText();
					//objPojo.getObjUtilities().logReporter("Date presrent on income page is -"+Date, true);
					String CenterExpenseValueLabel = objPojo.getDriver().findElement(By.id("com.monefy.app.lite:id/expense_amount_text")).getText();
					objPojo.getObjUtilities().logReporter("Expense presrent on Home page is -"+CenterExpenseValueLabel, true);
				}

			}


		}
		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigHistory")) {
			Thread.sleep(2000);
			GenericMethod.clickException(leftHamburger, "leftHamburger");
			Thread.sleep(1000);
			GenericMethod.clickException(accounntsDropDown, "Accounts");
			Thread.sleep(1000);
			String AllAccountActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='All accounts']")).getText();
			String AllAccountExpected = AllAccount;
			String CashActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Cash']")).getText();
			String CashExpected = Cash;
			String CardPaymentActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Payment card']")).getText();
			String CardPaymentExpected = Card;

			if(AllAccountActual.equalsIgnoreCase(AllAccountExpected)){
				objPojo.getObjUtilities().logReporter("First option source is-"+AllAccountActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(CashActual.equalsIgnoreCase(CashExpected)){
				objPojo.getObjUtilities().logReporter("Second option  is-"+CashActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(CardPaymentActual.equalsIgnoreCase(CardPaymentExpected)){
				objPojo.getObjUtilities().logReporter("Third option  is-"+CardPaymentActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);

			GenericMethod.clickException(Allaccounnts, "Accounts");


			String DayActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='Day']")).getText();
			String DayExpected = Day;
			String WeekActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='Week']")).getText();
			String WeekExpected = Week;
			String MonthActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='Month']")).getText();
			String MonthExpected = Month;
			String YearActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='Year']")).getText();
			String YearkExpected = Year;
			String AllActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='All']")).getText();
			String AllExpected = All;
			String IntervalActual = objPojo.getDriver().findElement(By.xpath("//android.widget.RadioButton[@text='Interval']")).getText();
			String IntervalExpected = Interval;


			if(DayActual.equalsIgnoreCase(DayExpected)){
				objPojo.getObjUtilities().logReporter("First income source is-"+DayActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(WeekActual.equalsIgnoreCase(WeekExpected)){
				objPojo.getObjUtilities().logReporter("First option  is-"+WeekActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(MonthActual.equalsIgnoreCase(MonthExpected)){
				objPojo.getObjUtilities().logReporter("Second option  is-"+MonthActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(YearActual.equalsIgnoreCase(YearkExpected)){
				objPojo.getObjUtilities().logReporter("Third option  is"+YearActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(AllActual.equalsIgnoreCase(AllExpected)){
				objPojo.getObjUtilities().logReporter("Fourth option  is-"+AllActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);
			if(IntervalActual.equalsIgnoreCase(IntervalExpected)){
				objPojo.getObjUtilities().logReporter("Fifth option  is-"+IntervalActual, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
			Thread.sleep(2000);

			GenericMethod.clickException(DayTab, "Day");
			String CenterExpenseValueLabel = objPojo.getDriver().findElement(By.id("com.monefy.app.lite:id/expense_amount_text")).getText();
			objPojo.getObjUtilities().logReporter("Expense presrent on Home page is -"+CenterExpenseValueLabel, true);
			String CenterIncomeValueLabel = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/income_amount_text']")).getText();
			objPojo.getObjUtilities().logReporter("Income presrent on Home page is -"+CenterIncomeValueLabel, true);

		}
		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigAddCategory")) {


			GenericMethod.clickException(SettingsDot, "Three Dots");
			Thread.sleep(1000);
			GenericMethod.clickException(AccountsSetting, "Accounts");
			Thread.sleep(1000);
			GenericMethod.clickException(addAccountsIcon, "Add Icon");
			Thread.sleep(1000);
			GenericMethod.clickException(amexCard, "Amex");
			Thread.sleep(1000);
			GenericMethod.clearAndSendKeysCustomException(AddName, objPojo.getEntityRunner().getStringValueForField("CategoryName"), "Category Name");
			Thread.sleep(1000);
			GenericMethod.clickException(AddCatogary, "Add");
			Thread.sleep(1000);
			objPojo.getObjUtilities().logReporter("Category Added Successfully ", true);

		}
		
		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigSettings")) {


			GenericMethod.clickException(SettingsDot, "Three Dots");
			Thread.sleep(1000);
			GenericMethod.clickException(settingButton, "Settings");
			Thread.sleep(1000);
			GenericMethod.clickException(budgetModeCheckbox, "Budget Mode");
			Thread.sleep(1000);
			GenericMethod.clearAndSendKeysCustomException(budgetAmountTextField, objPojo.getEntityRunner().getStringValueForField("BudgetAmount"), "Budget Amount");
			GenericMethod.clickException(okButton, "OK");
			objPojo.getObjUtilities().logReporter("Budget Mode Activated Successfully ", true);
			Thread.sleep(1000);
			GenericMethod.clickException(language, "Language");
			Thread.sleep(1000);
			GenericMethod.clickException(languageRadioButton, "Language Radio ");
			Thread.sleep(1000);
			GenericMethod.clickException(okButton, "OK");
			Thread.sleep(1000);
			objPojo.getObjUtilities().logReporter("Language Changed Successfully ", true);

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

	public void fillAndSubmitMonefyHomeExpensePage() throws InterruptedException {

		monefyHomeExpensePageDetails();


	}
}
