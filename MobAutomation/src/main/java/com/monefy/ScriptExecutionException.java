package com.monefy;

@SuppressWarnings("serial")
public class ScriptExecutionException extends RuntimeException {
	
	private String AppiumScreenName;
	
    public String getAppiumScreenName() {
		return AppiumScreenName;
	}

	public void setAppiumScreenName(String appiumScreenName) {
		AppiumScreenName = appiumScreenName;
	}

	public ScriptExecutionException(String message, String screenName) {
        super(message);
       // AutomationDriverScript.sroForReference.setReasonIfFailed(message);
    }

    public ScriptExecutionException(String message, Exception cause,String screenName) {
        super(message, cause);
       // AutomationDriverScript.sroForReference.setReasonIfFailed(message);
        setAppiumScreenName(screenName);
    }
}
