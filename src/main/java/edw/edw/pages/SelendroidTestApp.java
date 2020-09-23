package edw.edw.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edw.edw.commonLib.Controls;
import edw.edw.frameLib.Driver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.MobileDriver;

public class SelendroidTestApp extends Driver {
	public AndroidDriver<WebElement> driver;
	public WebDriver webdriver;
	public WebDriverWait wait;
	public MobileDriver<?> driver1;
	Controls controls;

	public SelendroidTestApp(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, time);
		controls = new Controls(driver);
	}

	// Zoho App
	public void clickOnSignUp() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign in with Google']")).click();
		Thread.sleep(2000);
	}

	public void clickOnGoogle() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@content-desc='Google']")).click();
		// driver.findElement(By.xpath("//android.view.View[@text='Google']")).click();
		Thread.sleep(1000);
	}

	public void clickOnMail() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@content-desc='Jaffar Ali jaff.1.2.3.4.ali@gmail.com']")).click();
		Thread.sleep(1000);
	}
	
	public void clickOnContinue() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@content-desc='Continue']")).click();
		Thread.sleep(1000);
	}

	public void clickOnAccept() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@content-desc='I agree to the Terms of Service and Privacy Policy']")).click();
		Thread.sleep(1000);
	}

	public void swipeAndClick() throws InterruptedException, IOException {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"create\"))").click();
		Thread.sleep(2000);
	}

	public void clickOnAccept1() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='ALLOW']")));
		driver.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();
		Thread.sleep(1000);
	}

	public void clickOnManage() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/setPasscodeNow")).click();
		Thread.sleep(1000);
	}

	public void clickOnBack() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	// Create Leads

	public void clickOnMenu() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@index=0]"))).click();
		Thread.sleep(1000);
	}

	public void clickOnLeads() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Leads']")).click();
		Thread.sleep(2000);
	}

	// 1st Lead
	public void clickOnAddNew() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/fling_fab_main")).click();
		Thread.sleep(1000);
	}

	public void clickOnNewLeads() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='New Lead']")).click();
		Thread.sleep(2000);
	}

	public void clickOnSwitchToSmartView() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/switch_view")).click();
		Thread.sleep(2000);
	}

	public void enterCompany() throws InterruptedException {
		// List<WebElement> li = driver.findElements(By.xpath("//android.widget.EditText[@bounds='[399,788][1038,925]']"));
		WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][1]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("TCS");
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterFirstName() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,926][1038,1063]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][2]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("Alex");
		Thread.sleep(1000);
	}

	public void clickOnBack1() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterLastName() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1064][1038,1201]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][3]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("N");
		Thread.sleep(1000);
	}

	public void clickOnBack2() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterEmail() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1202][1038,1339]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][4]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("alex12@gmail.com");
		Thread.sleep(1000);
	}

	public void clickOnBack3() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterPhoneNo() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1340][1038,1477]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][5]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("9677234561");
		Thread.sleep(1000);
	}

	public void clickOnBack4() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnSave() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	// 2nd Lead

	public void clickOnAddNew1() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/fling_fab_main")).click();
		Thread.sleep(1000);
	}

	public void clickOnNewLeads1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='New Lead']")).click();
		Thread.sleep(2000);
	}

	public void clickOnSwitchToSmartView1() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/switch_view")).click();
		Thread.sleep(2000);
	}

	public void enterCompany1() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][1]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("CTS");
		Thread.sleep(1000);
	}

	public void enterFirstName1() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,926][1038,1063]']"));
		//WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][2]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("Nakul");
		Thread.sleep(1000);
	}

	public void clickOnBack5() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterLastName1() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1064][1038,1201]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][3]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("C");
		Thread.sleep(1000);
	}

	public void clickOnBack6() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterEmail1() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1202][1038,1339]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][4]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("nakul70@gmail.com");
		Thread.sleep(1000);
	}

	public void clickOnBack7() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterPhoneNo1() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.EditText[@bounds='[399,1340][1038,1477]']"));
		// WebElement li = driver.findElement(By.xpath("//*[@class='android.widget.EditText'][5]"));
		li.click();
		Thread.sleep(1000);
		li.sendKeys("9677451280");
		Thread.sleep(1000);
	}

	public void clickOnBack8() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnSave1() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	//
	public void clickOnMenu1() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='Open']")).click();
		Thread.sleep(1000);
	}

	public void clickOnHome() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).click();
		Thread.sleep(2000);
	}

	// Meeting

	public void clickOnMeeting() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='+ Meeting']")).click();
		Thread.sleep(1000);
	}

	public void enterTitle() throws InterruptedException {
		List<WebElement> li= driver.findElements(By.xpath("//*[@class='android.widget.EditText'][1]"));
		li.get(0).click();
		Thread.sleep(1000);
		li.get(0).sendKeys("Meeting with CEO");
		Thread.sleep(1000);
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void enterLocation() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.xpath("//android.widget.EditText[@bounds='[399,474][1038,611]']"));
		li.get(0).click();
		Thread.sleep(1000);
		li.get(0).sendKeys("Chennai");
		Thread.sleep(1000);
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	// From :
	public void clickOnFromDate() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[399,739][1038,865]']"));
		// driver.findElement(By.xpath("//*[@class='android.widget.TextView'][1]")).click();
		li.click();
		Thread.sleep(1000);
	}

	public void clickOnFromDate1() throws InterruptedException {
		driver.findElement(By.xpath("//android.view.View[@text='24']")).click();
		Thread.sleep(1000);
	}

	public void clickOnTime() throws InterruptedException {
		driver.findElement(By.id("com.zoho.crm:id/time")).click();
		Thread.sleep(1000);
	}

	public void clickOn3pm() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='3']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@content-desc='0']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("android:id/pm_label")).click();
		Thread.sleep(1000);
	}

	public void clickOnDone() throws InterruptedException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(1000);
	}

	// To :
	public void clickOnToDate() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.xpath("//android.widget.TextView[@bounds='[399,866][1038,992]']"));
		// driver.findElement(By.xpath("//*[@class='android.widget.TextView'][1]")).click();
		li.get(0).click();
		Thread.sleep(1000);
	}

	public void clickOnToTime() throws InterruptedException {
		driver.findElement(By.id("com.zoho.crm:id/time")).click();
		Thread.sleep(1000);
	}

	public void clickOn4pm() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='4']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@content-desc='0']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("android:id/pm_label")).click();
		Thread.sleep(1000);
	}

	public void clickOnToDone() throws InterruptedException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(1000);
	}

	public void clickOnParticipants() throws InterruptedException {
		WebElement li = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[399,1120][1038,1246]']"));
		// driver.findElement(By.xpath("//*[@class='android.widget.TextView'][3]")).click();
		li.click();
		Thread.sleep(1000);
	}

	// Selecting 1st Lead for Meeting
	public void enterName() throws InterruptedException {
		driver.findElements(By.id("com.zoho.crm:id/participantsEditText"));
		driver.findElement(By.id("com.zoho.crm:id/addParticipants")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Leads']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='alex12@gmail.com']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	// Selecting 2nd Lead for Meeting
	public void enterName1() throws InterruptedException {
		driver.findElements(By.id("com.zoho.crm:id/participantsEditText"));
		driver.findElement(By.id("com.zoho.crm:id/addParticipants")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Leads']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='nakul70@gmail.com']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	//
	public void clickOnSave2() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	public void clickOnSave3() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/action_save")).click();
		Thread.sleep(1000);
	}

	public void clickOnDate() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='24']")).click();
		Thread.sleep(2000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\Appium_EDW\\screenshots\\FixedMeetings.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnMeetings() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/defaultField1")).click();
		Thread.sleep(2000);
	}

	public void clickOnDetails() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='DETAILS']")).click();
		Thread.sleep(2000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\Appium_EDW\\screenshots\\MeetingInformation.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack9() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}
	
	// Delete Scenario
	
	public void clickOnMeetings1() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/defaultField1")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnIcon() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/action_search")).click();
		Thread.sleep(1000);
	}
	
	public void clickOnDelete() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Delete']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnMenu2() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='Open']")).click();
		Thread.sleep(1000);
	}
	
	public void clickOnLeads1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Leads']")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnNakul() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Nakul C']")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnIcon1() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/module_settings")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnDelete1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Delete']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnAlex() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Alex N']")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnIcon2() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/module_settings")).click();
		Thread.sleep(2000);
	}
	
	public void clickOnDelete2() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Delete']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(2000);
	}
	
	// Sign Out
	public void clickOnMenu3() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='Open']")).click();
		Thread.sleep(1000);
	}

	public void clickOnSettings() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/settingsIcon")).click();
		Thread.sleep(1000);
	}

	public void clickOnSignOut() throws InterruptedException, IOException {
		driver.findElement(By.id("com.zoho.crm:id/signout")).click();
		Thread.sleep(1000);
	}

	public void clickOnSignOutButton() throws InterruptedException, IOException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(2000);
	}

	/*// Scientific Calculator App

	//Sin 30
	public void clickOnGotIt() throws InterruptedException, IOException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(1000);
	}

	public void clickOnSinFn() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='SIN']")).click();
		Thread.sleep(1000);
	}

	public void clickOn3() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='3']")).click();
		Thread.sleep(1000);
	}

	public void clickOn0() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='0']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Sin30.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}

	//Sin 45
	public void clickOnSinFn1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='SIN']")).click();
		Thread.sleep(1000);
	}

	public void clickOn4() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='4']")).click();
		Thread.sleep(1000);
	}

	public void clickOn5() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='5']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Sin45.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack1() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}

	//Cos45
	public void clickOnCosFn() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='COS']")).click();
		Thread.sleep(1000);
	}

	public void clickOnNum4() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='4']")).click();
		Thread.sleep(1000);
	}

	public void clickOnNum5() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='5']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals2() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Cos45.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack2() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear2() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}

	//Cos 60
	public void clickOnCosFn1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='COS']")).click();
		Thread.sleep(1000);
	}

	public void clickOnNum6() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='6']")).click();
		Thread.sleep(1000);
	}

	public void clickOnNum0() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='0']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals3() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Cos60.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack3() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear3() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}

	//Tan 60
	public void clickOnTanFn() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='TAN']")).click();
		Thread.sleep(1000);
	}

	public void clickOn6Num() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='6']")).click();
		Thread.sleep(1000);
	}

	public void clickOn0Num() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='0']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals4() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Tan60.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack4() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear4() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}

	//Tan 90
	public void clickOnTanFn1() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='TAN']")).click();
		Thread.sleep(1000);
	}

	public void clickOn9() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='9']")).click();
		Thread.sleep(1000);
	}

	public void clickOnNumZero() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='0']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEquals5() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@index=11]")).click();
		Thread.sleep(1000);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Tan90.jpg"));
		Thread.sleep(2000);
	}

	public void clickOnBack5() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnClear5() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//android.widget.Button[@text='AC']")).click();
		Thread.sleep(1000);
	}*/

	/*// Calculator App

	// Addition 
	public void clickOn98() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
		Thread.sleep(1000);
	}

	public void clickOnAddButton() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		Thread.sleep(1000);
	}

	public void clickOn25() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		Thread.sleep(1000);
	}

	public void takeAdditionScreenshot() throws InterruptedException, IOException {
		String result = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(" Addition Result : " + result);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Additon.jpg"));
		Thread.sleep(1000);
	}

	public void clickOnclear() throws InterruptedException, IOException 
	{
		WebElement clear = driver.findElement(By.id("com.google.android.calculator:id/del"));
		TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(clear))
				.withDuration(Duration.ofMillis(10000))).release().perform();
		Thread.sleep(5000);
	}

	// Subtraction 
	public void clickOn45() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_4")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		Thread.sleep(1000);
	}

	public void clickOnSubtractButton() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/op_sub")).click();
		Thread.sleep(1000);
	}

	public void clickOn20() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_0")).click();
		Thread.sleep(1000);
	}

	public void takeSubtractionScreenshot() throws InterruptedException, IOException {
		String result1 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(" Subtraction Result : " + result1);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Subtraction.jpg"));
		Thread.sleep(1000);
	}

	public void clickOnclear1() throws InterruptedException, IOException 
	{
		WebElement clear1 = driver.findElement(By.id("com.google.android.calculator:id/del"));
		TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(clear1))
				.withDuration(Duration.ofMillis(10000))).release().perform();
		Thread.sleep(5000);
	}

	// Multiplication
	public void clickOn38() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
		Thread.sleep(1000);
	}

	public void clickOnMultiplyButton() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		Thread.sleep(1000);
	}

	public void clickOn70() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_0")).click();
		Thread.sleep(1000);
	}

	public void takeMultiplicationScreenshot() throws InterruptedException, IOException {
		String result2 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println("Multiplication Result : " + result2);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Multiplication.jpg"));
		Thread.sleep(1000);
	}

	public void clickOnclear2() throws InterruptedException, IOException 
	{
		WebElement clear2 = driver.findElement(By.id("com.google.android.calculator:id/del"));
		TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(clear2))
				.withDuration(Duration.ofMillis(10000))).release().perform();
		Thread.sleep(5000);
	}

	// Division
	public void clickOn85() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		Thread.sleep(1000);
	}

	public void clickOnDivideButton() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
		Thread.sleep(1000);
	}

	public void clickOn5() throws InterruptedException, IOException {
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		Thread.sleep(1000);

	}

	public void takeDivisionScreenshot() throws InterruptedException, IOException {
		String result3 = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(" Division Result : " + result3);
		File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("K:\\EDW_Appium\\screenshots\\Division.jpg"));
		Thread.sleep(1000);
	}

	public void clickOnclear3() throws InterruptedException, IOException 
	{
		WebElement clear3 = driver.findElement(By.id("com.google.android.calculator:id/del"));
		TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(clear3))
				.withDuration(Duration.ofMillis(10000))).release().perform();
		Thread.sleep(5000);
	}*/

	/*// Dailyhunt News App

	public void clickOnLanguage() throws InterruptedException {
		driver.findElement(By.xpath("//android.view.ViewGroup[@index=5]")).click();
		Thread.sleep(1000);
	}

	capabilities.setCapability("appActivity", "com.newshunt.appview.common.ui.activity.HomeActivity");

	public void clickOnProfile() throws InterruptedException {
		driver.findElement(By.id("com.eterno:id/profile_image']")).click();
		Thread.sleep(1000);
	}

	public void clickOnAccount() throws InterruptedException {
		driver.findElement(By.id("com.google.android.gms:id/account_display_name']")).click();
		Thread.sleep(1000);
	}

	public void clickOnDone() throws InterruptedException {
		driver.findElement(By.id("com.eterno:id/discover_done_action']")).click();
		Thread.sleep(1000);
	}

	public void clickOnBack() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnDesiredNews() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Coronavirus']")).click();
		Thread.sleep(1000);
	}*/

	/*// Messages App

	public void clickOnNew() throws InterruptedException {
		driver.findElement(By.id("com.sms.messenger.allinone:id/compose")).click();
		Thread.sleep(1000);
	}

	public void enterName() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.xpath("//android.widget.EditText[@text='Type a name or number']"));
		li.get(0).sendKeys("Alex");
		Thread.sleep(1000);
	}

	public void clickOnNameContact() throws InterruptedException {
		driver.findElement(By.id("com.sms.messenger.allinone:id/primary")).click();
		Thread.sleep(1000);
	}

	public void enterMessage() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.sms.messenger.allinone:id/message"));
		li.get(0).sendKeys("Hi How are you? Can I call you @ 2 pm tomorrow??");
		Thread.sleep(1000);
	}

	public void clickOnSend() throws InterruptedException {
		driver.findElement(By.id("com.sms.messenger.allinone:id/send")).click();
		Thread.sleep(1000);
	}

	public void clickOnBack() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnBack1() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}*/

	/*// Time of Different Countries

	public void clickOnGlobe() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/fab")).click();
		Thread.sleep(1000);
	}

	public void enterState() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.google.android.deskclock:id/search_src_text"));
		li.get(0).sendKeys("California");
		Thread.sleep(1000);
	}

	public void clickOnState() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/city_name")).click();
		Thread.sleep(1000);
	}

	public void clickOnGlobe1() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/fab")).click();
		Thread.sleep(1000);
	}

	public void enterState1() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.google.android.deskclock:id/search_src_text"));
		li.get(0).sendKeys("Paris");
		Thread.sleep(1000);
	}

	public void clickOnState1() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/city_name")).click();
		Thread.sleep(1000);
	}

	public void clickOnGlobe2() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/fab")).click();
		Thread.sleep(1000);
	}

	public void enterState2() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.google.android.deskclock:id/search_src_text"));
		li.get(0).sendKeys("Berlin");
		Thread.sleep(1000);
	}

	public void clickOnState2() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/city_name")).click();
		Thread.sleep(1000);
	}

	public void clickOnGlobe3() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/fab")).click();
		Thread.sleep(1000);
	}

	public void enterState3() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.google.android.deskclock:id/search_src_text"));
		li.get(0).sendKeys("London");
		Thread.sleep(1000);
	}

	public void clickOnState3() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/city_name")).click();
		Thread.sleep(1000);
	}*/

	/*// Weather App

	public void clickOnDone() throws InterruptedException {
		driver.findElement(By.id("com.graph.weather.forecast.channel:id/tvDone")).click();
		Thread.sleep(2000);
	}

	public void clickOnTitle() throws InterruptedException {
		driver.findElement(By.id("com.graph.weather.forecast.channel:id/tvTitle")).click();
		Thread.sleep(1000);
	}

	public void clickOnAddLocation() throws InterruptedException {
		driver.findElement(By.id("com.graph.weather.forecast.channel:id/ll_add_location")).click();
		Thread.sleep(3000);
	}

	public void enterState() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("com.graph.weather.forecast.channel:id/et_search_location"));
		li.get(0).sendKeys("Chennai");
		Thread.sleep(1000);
		li.get(0).clear();
		Thread.sleep(1000);
		li.get(0).sendKeys("Chennai");
	}

	public void clickOnChennai() throws InterruptedException {
		driver.findElement(By.id("com.graph.weather.forecast.channel:id/ll_item_search")).click();
		Thread.sleep(1000);
	}

	public void clickOnChennaiTN() throws InterruptedException {
		driver.findElement(By.id("com.graph.weather.forecast.channel:id/tvInfoLocation")).click();
		Thread.sleep(5000);
	}*/

	// Calendar App

	/*public void clickOnNew() throws InterruptedException {
		driver.findElement(By.id("com.google.android.calendar:id/floating_action_button")).click();
		Thread.sleep(1000);
	}

	public void clickOnReminder() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.TextView[@text='Reminder']")).click();
		Thread.sleep(1000);
	}

	public void enterEvent() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.xpath("//android.widget.EditText[@text='Remind me to…']"));
		li.get(0).sendKeys("Recharge TataSky");
	}

	public void switchOffAllDay() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.Switch[@text='ON']")).click();
		Thread.sleep(1000);
	}

	public void clickOnTime() throws InterruptedException {
		driver.findElement(By.xpath("android.widget.Button[@text='6:00 PM']")).click();
		Thread.sleep(1000);
	}

	public void clickOnEight() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='8']")).click();
		Thread.sleep(1000);
	}

	public void clickOn00() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='00']")).click();
		Thread.sleep(1000);
	}

	public void clickOnPM() throws InterruptedException {
		driver.findElement(By.id("android:id/pm_label")).click();
		Thread.sleep(1000);
	}

	public void clickOnOK() throws InterruptedException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(1000);
	}

	public void clickOnDate() throws InterruptedException {
		driver.findElement(By.id("com.google.android.calendar:id/first_line_text")).click();
		Thread.sleep(1000);
	}

	public void clickOnNextMonth() throws InterruptedException {
		driver.findElement(By.id("android:id/next")).click();
		Thread.sleep(1000);
	}

	public void clickOn2nd() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='02 August 2020']")).click();
		Thread.sleep(1000);
	}

	public void clickOnOKMonth() throws InterruptedException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(1000);
	}

	public void clickOnSave() throws InterruptedException {
		driver.findElement(By.id("com.google.android.calendar:id/save")).click();
		Thread.sleep(1000);
	}*/

	// Selendroid App
	/*public void clickUserRegistrationButton() throws InterruptedException {
		driver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration")).click();
		Thread.sleep(1000);
	}

	public void clickOnAlarm() throws InterruptedException {
		driver.findElement(By.id("io.selendroid.testapp:id/inputUsername")).sendKeys("Naresh");
		Thread.sleep(1000);
	}

	public void enterUserName() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("io.selendroid.testapp:id/inputUsername"));
		li.get(0).sendKeys("Naresh");
	}

	public void enterEMailID() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("io.selendroid.testapp:id/inputEmail"));
		li.get(0).sendKeys("naresh01@gmail.com");
	}

	public void enterPassword() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("io.selendroid.testapp:id/inputPassword"));
		li.get(0).sendKeys("Lion@123");
	}

	public void enterName() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.id("io.selendroid.testapp:id/inputName"));
		li.get(0).clear();
		Thread.sleep(1000);
		li.get(0).sendKeys("Mr. Naresh");
	}

	public void clickOnBack() throws InterruptedException {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		Thread.sleep(1000);
	}

	public void clickOnPrgLang() throws InterruptedException {
		driver.findElement(By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage")).click();
		Thread.sleep(1000);
	}

	public void clickOnJava() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Java']")).click();
		Thread.sleep(1000);
	}

	public void clickOnAccept() throws InterruptedException {
		driver.findElement(By.id("io.selendroid.testapp:id/input_adds")).click();
		Thread.sleep(1000);
	}

	public void clickOnVerifyButton() throws InterruptedException {
		driver.findElement(By.id("io.selendroid.testapp:id/btnRegisterUser")).click();
		Thread.sleep(1000);
	}*/

}
