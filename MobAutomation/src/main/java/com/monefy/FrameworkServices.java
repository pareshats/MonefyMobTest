package com.monefy;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FrameworkServices {

	//private static String packageName;
	public static AndroidDriver<MobileElement> driver;




	public static AppiumDriver<MobileElement> getWebDriverInstance() throws Exception {
		
		
		
	DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("deviceName", "DRGID18082101756");
		cap.setCapability("browser_Name", "Android");
		cap.setCapability("appPackage", "com.monefy.app.lite");
		cap.setCapability("appActivity", "com.monefy.activities.main.MainActivity_");
		cap.setCapability("gpsEnabled", true);
		cap.setCapability("newCommandTimeout", 150);	
		cap.setCapability("noReset", false);
		cap.setCapability("–session-override", true);
		cap.setCapability("testdroid_testTimeout", 1200);
		String gridURL = Uploader.prop.getProperty("GridURL");
		
	
		driver = new AndroidDriver<MobileElement>(new URL(gridURL), cap);

		return driver;

	}

	public static void pageWait(int value) throws InterruptedException {
		try {
			Thread.sleep(value);
		} catch (Exception e) {
		}
	}

}
