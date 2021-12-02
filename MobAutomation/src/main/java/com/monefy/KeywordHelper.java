package com.monefy;



import com.Pages.MoneFyIncomePage;
import com.Pages.MonefyExpensePage;
import com.Pages.MonefyHomeScreenPage;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.monefy.EntityRunner;

public class KeywordHelper extends Pojo {
	Pojo objpojo;
	TestScenarios TestScanerio;
	
	@SuppressWarnings({ "static-access", "deprecation" })
		public String MonefyHomePage(String stepGroup) throws FilloException, InterruptedException {
			Recordset recordset = EntityRunner.getEntityObject("DT_MonefyHome", this,stepGroup);
			while (recordset.next()) {
				if (recordset.getField("Action").equals("add")) {
					this.setEntityRunner(recordset);		
					MoneFyIncomePage obj = new MoneFyIncomePage(this);
					obj.fillAndSubmitMonefyHomePage();
					this.setEntityRunner(recordset);
				}

			}
			return recordset.getField("Action").toString();
	}
		
		public String ExpensePage(String stepGroup) throws FilloException, InterruptedException {
			Recordset recordset = EntityRunner.getEntityObject("DT_MonefyExpense", this,stepGroup);
			while (recordset.next()) {
				if (recordset.getField("Action").equals("add")) {
					this.setEntityRunner(recordset);		
					MonefyExpensePage obj = new MonefyExpensePage(this);
					obj.fillAndSubmitMonefyExpensePage();
					this.setEntityRunner(recordset);
				}

			}
			return recordset.getField("Action").toString();
	}
	
		public String HomeExpensePage(String stepGroup) throws FilloException, InterruptedException {
			Recordset recordset = EntityRunner.getEntityObject("DT_MonefyHomeExpense", this,stepGroup);
			while (recordset.next()) {
				if (recordset.getField("Action").equals("add")) {
					this.setEntityRunner(recordset);		
					MonefyHomeScreenPage obj = new MonefyHomeScreenPage(this);
					obj.fillAndSubmitMonefyHomeExpensePage();
					this.setEntityRunner(recordset);
				}

			}
			return recordset.getField("Action").toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
