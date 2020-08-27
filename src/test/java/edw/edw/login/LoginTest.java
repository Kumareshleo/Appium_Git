package edw.edw.login;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import edw.edw.frameLib.Driver;
import edw.edw.frameLib.Log;
import edw.edw.frameLib.ReadExcel;
import edw.edw.pages.LoginPage;

public class LoginTest extends Driver {
	LoginPage loginPage;

	@Override
	public void PageSetup() {
		loginPage = new LoginPage(driver);
		//ReadExcel.setExcelFile("loginApplication");
	}

	@DataProvider
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ReadExcel.getMultipleData();
		return (testObjArray);

	}

	@Test
	public void loginTest() throws Exception {

		Log.startTestCase("Test_Case_ID : 001 : Testing FORGOT PASSWORD");

		/*loginPage.clickOnAlarm();
		Log.info("Clicked on Alarm");

		loginPage.clickOnAddNewButton();
		Log.info("Clicked on Add New Button");

		loginPage.clickOn7();
		Log.info("Clicked on 7 hour");

		loginPage.clickOn15();
		Log.info("Clicked on 15 minutes");

		loginPage.selectAmPM();
		Log.info("Clicked on AM");

		loginPage.clickOnOkButton();
		Log.info("Clicked on Ok button");*/

		loginPage.clickOnSettingsButton();
		Log.info("Clicked on settings button");

		loginPage.clickOnSignInButton();
		Log.info("Clicked on signIn button");

		loginPage.clickOnForgotPassword();
		Log.info("Clicked on forgot password button");

		loginPage.enterEmail();
		Log.info("Entered email");

		loginPage.clickOnSendButton();
		Log.info("Clicked on send  button.");


		Log.endTestCase("Test_Case_ID : 001 :Verified FORGOT PASSWORD");
		// Log.endTestCase("Test_Case_ID : 001 :Verified Alarm");
	}
}
