package appTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.lang.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class App {
	AndroidDriver<AndroidElement> driver;

	@Test
	public void test() throws MalformedURLException {
		File appDir = new File("src");
		File app = new File(appDir, "app-test-apk.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,
				MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		//Launch appium
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		WebDriverWait wd = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// send blank mobile number
		driver.findElementById("com.techbins.niki.beta:id/edtTxtPhone").sendKeys("");
		driver.findElementById("com.techbins.niki.beta:id/btnSubmit").click();
		// Send valid phone number
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		int count = driver.findElementsById("com.techbins.niki.beta:id/editTxtName").size();
		if (count == 0) {
			driver.findElementById("com.techbins.niki.beta:id/edtTxtPhone").sendKeys("Valid phone numer");
			driver.findElementById("com.techbins.niki.beta:id/btnSubmit").click();
		}
		/*
		 * We can fetch OTP by going in to Message app open message of DM-NikiAi
		 * but if user is using same mpbile app is directly taking OTP
		 */

		// Fill Sign up form
		// Provide Name,email address. 
		try {
			driver.findElementById("com.techbins.niki.beta:id/editTxtName").sendKeys("User_name");
			driver.findElementById("com.techbins.niki.beta:id/editTxtEmail").sendKeys("User_mailid");
			driver.findElementById("com.techbins.niki.beta:id/btnSubmit").click();
		} catch (Exception e) {
			System.out.println("Only OTP is sufficient");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//If we are running script more than twice it will take OTP automatically
		// click on Skip
		driver.findElementById("com.techbins.niki.beta:id/btn_skip").click();
		// Tap on Menu bar
		TouchAction t1 = new TouchAction(driver);
		MobileElement el1 = (MobileElement) driver.findElementById("com.techbins.niki.beta:id/nikiMenuBlink");
		t1.tap(el1).perform();
		// Click on My orders
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"My Orders\")").click();
		driver.pressKeyCode(AndroidKeyCode.BACK);
		// Click on Notificaations
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Notifications\")").click();
		driver.pressKeyCode(AndroidKeyCode.BACK);
		// Click on Improve My Experience
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Improve My Experience\")").click();
		java.util.List<AndroidElement> linkAccount = driver.findElements(By.className("android.widget.ImageView"));
		// Check LINK YOUR ACCOUNT. Click on UBER link
		linkAccount.get(3).click();
		String Uber = "Get moving with Uber";
		java.util.List<AndroidElement> ubercab = driver.findElementsById("com.ubercab:id/header_text");
		String str = ubercab.get(0).getText();
		if (str.equals(Uber)) {
			System.out.println("It is redirecting to UBER app successfully");
		}
		ubercab.get(1).click();
		driver.findElementById("com.ubercab:id/mobile_text_field").sendKeys("mobile number");
		// click on progess bar ->
		// driver.findElement(By.className("android.widget.ProgressBar")).click();
		// User can linked OLA account in same manner
		// Return to Improve My Experience page
		/*int counts = driver.findElementsByAndroidUIAutomator("new UiSelector().text(\"Improve My Experience\")").size();
		while (counts == 1) {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		}*/
		for(int i=1;i<=3;i++)
		{
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Link wallets
		driver.scrollTo("LINK MY WALLETS");
		java.util.List<AndroidElement> edit = driver.findElements(By.id("com.techbins.niki.beta:id/editWalletIcon"));
		edit.get(0).click();
		driver.findElementById("com.techbins.niki.beta:id/paytmContactNumber").sendKeys("mobile number");
		driver.findElement(By.className("android.widget.Button")).click();
		//User can link freecharge account in same manner
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Add home address
		driver.findElementById("com.techbins.niki.beta:id/ivHomeAddress").click();
		// If location is off it will ask to turn it on, press back key
		driver.pressKeyCode(AndroidKeyCode.BACK);
		java.util.List<AndroidElement> address = driver.findElements(By.className("android.widget.EditText"));
		address.get(0).sendKeys("House no");
		address.get(1).sendKeys("Street no");
		address.get(2).sendKeys("Location");
		address.get(0).sendKeys("Landmark");
		driver.findElement(By.className("android.widget.Button")).click();
		// we can add office address like above

	}
}
