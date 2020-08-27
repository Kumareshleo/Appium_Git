package edw.edw.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import edw.edw.commonLib.Controls;
import edw.edw.frameLib.Driver;
import edw.edw.frameLib.StringEncrypt;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends Driver {
	public AndroidDriver driver;
	public WebDriverWait wait;
	Controls controls;

	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, time);
		controls = new Controls(driver);

	}

	/*public void clickOnAlarm() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/tab_menu_alarm")).click();
		Thread.sleep(1000);
	}

	public void clickOnAddNewButton() throws InterruptedException {
		driver.findElement(By.id("com.google.android.deskclock:id/fab")).click();
		Thread.sleep(1000);
	}

	public void clickOn7() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='7']")).click();
		Thread.sleep(1000);
	}

	public void clickOn15() throws InterruptedException {
		driver.findElement(By.xpath("//*[@content-desc='15']")).click();
		Thread.sleep(1000);
	}

	public void selectAmPM() throws InterruptedException {
		driver.findElement(By.id("android:id/am_label")).click();
		Thread.sleep(1000);
	}

	public void clickOnOkButton() throws InterruptedException {
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(5000);
	}*/

	// Click on Sign In Button
	public void clickOnSkipButton() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/GetStart")).click();
		Thread.sleep(1000);
	}


	public void selectEnglishLanguage() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/btnEnglish")).click();
		Thread.sleep(1000);
	}

	public void clickUseMyLocation() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/useMyLocation")).click();
		Thread.sleep(1000);
	}

	public void clickWhileUsingApp() throws InterruptedException {
		driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
		Thread.sleep(1000);
	}

	public void clickOnDoneButton() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/btnDone")).click();
		Thread.sleep(1000);
	}

	public void clickOnSettingsButton() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/settings")).click();
		Thread.sleep(1000);
	}

	public void clickOnSignInButton() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.className("android.widget.TextView"));
		li.get(1).click();
		Thread.sleep(1000);
	}

	public void clickOnForgotPassword() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.className("android.widget.TextView"));
		li.get(0).click();
		Thread.sleep(10000);
	}

	public void enterEmail() throws InterruptedException {
		List<WebElement> li = driver.findElements(By.className("android.widget.EditText"));
		li.get(0).sendKeys("kumaresh@novaturebusiness.com");
	}

	public void clickOnSendButton() throws InterruptedException {
		driver.findElement(By.id("om.gov.mara.maracal:id/btnLogin")).click();
		Thread.sleep(1000);
	}

}
