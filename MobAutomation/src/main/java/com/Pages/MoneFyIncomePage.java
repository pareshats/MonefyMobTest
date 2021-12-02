package com.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.MonefyTest.TestLauncher;
import com.monefy.EntityRunner;
import com.monefy.Pojo;
import com.monefy.TestScenarios;
import com.monefy.WrapperFunctions;

public class MoneFyIncomePage {

	TestScenarios TestScanerio;
	Pojo objPojo;
	EntityRunner EntityRunner;


	By monefyLabel;
	By incomeButton;
	By incomeSource;
	By cash;
	By paymentCard;
	By money;
	By note;
	By chooseCategory;
	By date;
	By centerIncomeLable;
	By savings;



	public MoneFyIncomePage(Pojo objPojo) {
		this.objPojo = objPojo;
		EntityRunner = objPojo.getEntityRunner();

		monefyLabel = By.xpath("//android.widget.TextView[@text='Monefy']");
		incomeButton=By.xpath("//android.widget.TextView[@text='INCOME']");
		incomeSource=By.xpath("//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/icon']");
		cash = By.xpath("//android.widget.TextView[@text='Cash']");
		paymentCard = By.xpath("//android.widget.TextView[@text='Payment card']");
		money= By.xpath("//android.widget.Button[@text='1']");
		note= By.xpath("//android.widget.EditText[@text='Note']");
		//chooseCategory =By.xpath("//aandroid.widget.Button[@resource-id='com.monefy.app.lite:id/keyboard_action_button']");
		chooseCategory =By.id("com.monefy.app.lite:id/keyboard_action_button");
		date =By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewDate']");
		centerIncomeLable =By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/income_amount_text']");
		savings=By.xpath("//android.widget.TextView[@text='Savings']");
		
	}

	public void  monefyHomePageDetails() throws InterruptedException {

		WrapperFunctions GenericMethod = objPojo.getObjWrapperFunctions();
		String HomePageLabel = objPojo.getEntityRunner().getStringValueForField("HomePageLabel");
		String IncomeCashValue = objPojo.getEntityRunner().getStringValueForField("IncomeCashValue");
		String IncomePaymentCardValue = objPojo.getEntityRunner().getStringValueForField("IncomePaymentCardValue");
		String NoteTextValue = objPojo.getEntityRunner().getStringValueForField("Notes");
		String Deposite = objPojo.getEntityRunner().getStringValueForField("Deposite");
		String Salary = objPojo.getEntityRunner().getStringValueForField("Salary");
		String Savings = objPojo.getEntityRunner().getStringValueForField("Savings");
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
			
			GenericMethod.clickException(incomeButton, "Incoome");
			GenericMethod.clickException(incomeSource, "Incoome Source");
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
				GenericMethod.clickException(money, "Adding Money");
				GenericMethod.clickException(money, "Adding Money");
				
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
			
			
			String DepositeActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Deposits']")).getText();
			String DepositeExpected = Deposite;
			String SalaryActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Salary']")).getText();
			String SalaryExpected = Salary;
			String SavingsActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Savings']")).getText();
			String SavingsExpected = Savings;
			String AddActual = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@text='ADD']")).getText();
			String AddExpected = Add;
			Thread.sleep(2000);
			if(DepositeActual.equalsIgnoreCase(DepositeExpected)){
				objPojo.getObjUtilities().logReporter("Income categories are-"+DepositeActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Deposit category is not available  !");	

			}
			Thread.sleep(2000);
			if(SalaryActual.equalsIgnoreCase(SalaryExpected)){
				objPojo.getObjUtilities().logReporter("Income categories are-"+SalaryActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Salary category is not available  !");	

			}
			Thread.sleep(2000);
			if(SavingsActual.equalsIgnoreCase(SavingsExpected)){
				objPojo.getObjUtilities().logReporter("Income categories are-"+SavingsActual, true);
			}else
			{
				Assert.assertEquals(false, true,"Savings category is not available  !");	

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
				
				GenericMethod.clickException(savings, "Savings Category");
				Thread.sleep(1000);
				String CenterIncomeValueLabel = objPojo.getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/income_amount_text']")).getText();
				objPojo.getObjUtilities().logReporter("Income presrent on Home page is -"+CenterIncomeValueLabel, true);
				
			}
	}

	public void fillAndSubmitMonefyHomePage() throws InterruptedException {

		monefyHomePageDetails();


	}
}
