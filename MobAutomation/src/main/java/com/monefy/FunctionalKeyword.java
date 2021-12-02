package com.monefy;

import java.util.ArrayList;

public class FunctionalKeyword extends KeywordHelper {

	protected void executeStep(MasterAutomationScriptSteps masterAutomationScriptSteps) throws Exception 
	{

		ArrayList<String> StepKeyword = masterAutomationScriptSteps.getStepKeyword();
		Integer stepNumber = masterAutomationScriptSteps.getExecutionSequence();

		ArrayList<String> StepGroup = masterAutomationScriptSteps.getStepGroup();

		int sizee = StepKeyword.size();
		for (int i = 0; i < sizee; i++) {
			int a =i+1;
			String string = StepKeyword.get(i);
			String stepGroup = StepGroup.get(i);
			this.getObjUtilities().logReporter("<B><u>StepNumber-</u>" + a
					+ "<i><Font Color = /'#00008B/'> Executing : " + string + " </Font></i></B>", true);

			switch (string) {
			
			case "CreateMonefyHome":
				MonefyHomePage(stepGroup);
				break;
				
			case "CreateExpensePage":
				ExpensePage(stepGroup);
				break;
			case "CreateHomeExpensePage":
				HomeExpensePage(stepGroup);
				break;
				
				

			}

		}
		stepNumber++;
	}

}
