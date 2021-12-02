package com.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.monefy.EntityRunner;
import com.monefy.Pojo;
import com.monefy.TestScenarios;
import com.monefy.WrapperFunctions;

public class MonefyExpensePage {

	TestScenarios TestScanerio;
	Pojo objPojo;
	EntityRunner EntityRunner;


	By monefyLabel;
	By ExpenseButton;
	By ExpenseSource;
	By cash;
	By paymentCard;
	By money;
	By note;
	By chooseCategory;
	By date;
	By centerIncomeLable;
	By savings;
	By BillsCat;



	public MonefyExpensePage(Pojo objPojo) {
		this.objPojo = objPojo;
		EntityRunner = objPojo.getEntityRunner();

		monefyLabel = By.xpath("//android.widget.TextView[@text='Monefy']");
		ExpenseButton=By.id("com.monefy.app.lite:id/expense_button_title");
		ExpenseSource=By.xpath("//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/icon']");
		cash = By.xpath("//android.widget.TextView[@text='Cash']");
		paymentCard = By.xpath("//android.widget.TextView[@text='Payment card']");
		money= By.xpath("//android.widget.Button[@text='1']");
		note= By.xpath("//android.widget.EditText[@text='Note']");
		//chooseCategory =By.xpath("//aandroid.widget.Button[@resource-id='com.monefy.app.lite:id/keyboard_action_button']");
		chooseCategory =By.id("com.monefy.app.lite:id/keyboard_action_button");
		date =By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewDate']");
		centerIncomeLable =By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/income_amount_text']");
		savings=By.xpath("//android.widget.TextView[@text='Savings']");
		 BillsCat=By.xpath("//android.widget.TextView[@text='Bills']");
		
	}

	public void  monefyExpensePageDetails() throws InterruptedException {

		WrapperFunctions GenericMethod = objPojo.getObjWrapperFunctions();
		String HomePageLabel = objPojo.getEntityRunner().getStringValueForField("HomePageLabel");
		String IncomeCashValue = objPojo.getEntityRunner().getStringValueForField("IncomeCashValue");
		String IncomePaymentCardValue = objPojo.getEntityRunner().getStringValueForField("IncomePaymentCardValue");
		String NoteTextValue = objPojo.getEntityRunner().getStringValueForField("Notes");
		String Bills = objPojo.getEntityRunner().getStringValueForField("Bills");
		String Car = objPojo.getEntityRunner().getStringValueForField("Car");
		String Cloths = objPojo.getEntityRunner().getStringValueForField("Cloths");
		String Communication = objPojo.getEntityRunner().getStringValueForField("Communication");
		String EatingOut = objPojo.getEntityRunner().getStringValueForField("EatingOut");
		String Entertainment = objPojo.getEntityRunner().getStringValueForField("Entertainment");
		String Add = objPojo.getEntityRunner().getStringValueForField("Add");
		//String ConfigModule = objPojo.getEntityRunner().getStringValueForField("ConfigSearchModule");

		//Clicking on Search Bar

		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigMonefyHome")) {
			

			String Actual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Monefy']")).getText();
			String Expected = HomePageLabel;

			if(Actual.equalsIgnoreCase(Expected)){
				objPojo.getObjUtilities().logReporter("Monefy Home Label is-"+HomePageLabel, true);
			}else
			{
				Assert.assertEquals(false, true,"HomePage is not visible !");	

			}
		}
		
		
		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigIncome")) {
			
			GenericMethod.clickException(ExpenseButton, "Incoome");
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
			
		}
		
		if (objPojo.getEntityRunner().getBooleanValueForField("ConfigEnterIncome")) {
			if (objPojo.getEntityRunner().getBooleanValueForField("ConfigCash")) {
				GenericMethod.clickException(cash, "Cash Income");
				GenericMethod.clickException(money, "Adding Money");
				GenericMethod.clickException(money, "Adding Money");
				GenericMethod.clickException(money, "Adding Money");
				GenericMethod.clickException(money, "Adding Money");
				
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
			String Date = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewDate']")).getText();
			objPojo.getObjUtilities().logReporter("Date presrent on income page is -"+Date, true);
			}
		}
		
			if (objPojo.getEntityRunner().getBooleanValueForField("ConfigCategory")) {
			
				Thread.sleep(2000);
			String billsActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Bills']")).getText();
			String billsExpected = Bills;
			String carActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Car']")).getText();
			String carExpected = Car;
			String clothsActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Clothes']")).getText();
			String clothsExpected = Cloths;
			String CommsActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Communications']")).getText();
			String CommsExpected = Communication;
			String eatingActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Eating out']")).getText();
			String eatingExpected = EatingOut;
			String entertainmentActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Entertainment']")).getText();
			String entertainmnetExpected = Entertainment;
			GenericMethod.scrollDown();
			String AddActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='ADD']")).getText();
			String AddExpected = Add;
			Thread.sleep(2000);
			if(billsActual.equalsIgnoreCase(billsExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+billsActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Bills category is not available  !");	

			}
			Thread.sleep(2000);
			if(carActual.equalsIgnoreCase(carExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+carActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Salary category is not available  !");	

			}
			Thread.sleep(2000);
			if(clothsActual.equalsIgnoreCase(clothsExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+clothsActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Clothes category is not available  !");	

			}
			Thread.sleep(2000);
			if(CommsActual.equalsIgnoreCase(CommsExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+CommsActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Communications category is not available  !");	

			}
			Thread.sleep(2000);
			if(eatingActual.equalsIgnoreCase(eatingExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+eatingActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Eating out category is not available  !");	

			}
			Thread.sleep(2000);
			if(entertainmentActual.equalsIgnoreCase(entertainmnetExpected)){
				objPojo.getObjUtilities().logReporter("Expense categories are-"+entertainmentActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Entertainment category is not available  !");	

			}
			Thread.sleep(2000);
			if(AddActual.equalsIgnoreCase(AddExpected)){
				objPojo.getObjUtilities().logReporter("Income categories are-"+AddActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Add category option is not available  !");	

			}
			
		}
			if (objPojo.getEntityRunner().getBooleanValueForField("ConfigSelectCategory")) {
				
				GenericMethod.clickException(BillsCat, "Bills Category");
				Thread.sleep(1000);
				String CenterExpenseValueLabel = objPojo.getDriver().findElement(By.id("com.monefy.app.lite:id/expense_amount_text")).getText();
				objPojo.getObjUtilities().logReporter("Expense presrent on Home page is -"+CenterExpenseValueLabel, true);
				

				
			}
	}

	public void fillAndSubmitMonefyExpensePage() throws InterruptedException {

		monefyExpensePageDetails();


	}
}
