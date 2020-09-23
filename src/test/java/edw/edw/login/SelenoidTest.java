package edw.edw.login;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import edw.edw.frameLib.Driver;
import edw.edw.frameLib.Log;
import edw.edw.frameLib.ReadExcel;
import edw.edw.pages.LoginPage;
import edw.edw.pages.SelendroidTestApp;

public class SelenoidTest extends Driver {
	SelendroidTestApp test;

	@Override
	public void PageSetup() {
		test = new SelendroidTestApp(driver);
		//ReadExcel.setExcelFile("loginApplication");
	}

	@DataProvider
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ReadExcel.getMultipleData();
		return (testObjArray);
	}

	@Test
	public void loginTest() throws Exception {

		// Zoho App
		Log.startTestCase("Test_Case_ID : 001 : Testing Zoho CRM App");

		test.clickOnSignUp();
		Log.info("Clicked on Sign Up Button");

		test.clickOnGoogle();
		Log.info("Clicked on Google Button");

		test.clickOnMail();
		Log.info("Clicked on Mail");
		
		test.clickOnContinue();
		Log.info("Clicked on Continue");

//		test.clickOnAccept();
//		Log.info("Clicked on Accept Button");
//
//		test.swipeAndClick();
//		Log.info("Swipe and Clicked");

		test.clickOnAccept1();
		Log.info("Clicked on Accept Button");

		test.clickOnManage();
		Log.info("Clicked on Manage");

		test.clickOnBack();
		Log.info("Clicked on Back Button");

		// Create Leads
		test.clickOnMenu();
		Log.info("Clicked on Menu");

		test.clickOnLeads();
		Log.info("Clicked on Leads");

		// 1st Lead
		test.clickOnAddNew();
		Log.info("Clicked on Add New");

		test.clickOnNewLeads();
		Log.info("Clicked on New Leads");

		test.clickOnSwitchToSmartView();
		Log.info("Clicked on Switch To Smart View Button");

		test.enterCompany();
		Log.info("Entered Company Name");

		test.enterFirstName();
		Log.info("Entered First Name");

		test.clickOnBack1();
		Log.info("Clicked on Back Button");

		test.enterLastName();
		Log.info("Entered Last Name");

		test.clickOnBack2();
		Log.info("Clicked on Back Button");

		test.enterEmail();
		Log.info("Entered Email ID");

		test.clickOnBack3();
		Log.info("Clicked on Back Button");

		test.enterPhoneNo();
		Log.info("Entered Phone Number");

		test.clickOnBack4();
		Log.info("Clicked on Back Button");

		test.clickOnSave();
		Log.info("Clicked on Save Button");

		// 2nd Lead
		test.clickOnAddNew1();
		Log.info("Clicked on Add New");

		test.clickOnNewLeads1();
		Log.info("Clicked on New Leads");

		test.clickOnSwitchToSmartView1();
		Log.info("Clicked on Switch To Smart View Button");

		test.enterCompany1();
		Log.info("Entered Company Name");

		test.enterFirstName1();
		Log.info("Entered First Name");

		test.clickOnBack5();
		Log.info("Clicked on Back Button");

		test.enterLastName1();
		Log.info("Entered Last Name");

		test.clickOnBack6();
		Log.info("Clicked on Back Button");

		test.enterEmail1();
		Log.info("Entered Email ID");

		test.clickOnBack7();
		Log.info("Clicked on Back Button");

		test.enterPhoneNo1();
		Log.info("Entered Phone Number");

		test.clickOnBack8();
		Log.info("Clicked on Back Button");

		test.clickOnSave1();
		Log.info("Clicked on Save Button");

		//
		test.clickOnMenu1();
		Log.info("Clicked on Menu Icon");

		test.clickOnHome();
		Log.info("Clicked on Home Icon");

		// Meeting
		test.clickOnMeeting();
		Log.info("Clicked on Meeting");

		test.enterTitle();
		Log.info("Entered Title");

		test.enterLocation();
		Log.info("Entered Location");

		// From :
		test.clickOnFromDate();
		Log.info("Clicked on From Date");

		test.clickOnFromDate1();
		Log.info("Clicked on From Date1");

		test.clickOnTime();
		Log.info("Clicked on Time");

		test.clickOn3pm();
		Log.info("Clicked on 3:00 PM");

		test.clickOnDone();
		Log.info("Clicked on Done Button");

		// To :
		test.clickOnToDate();
		Log.info("Clicked on To Date");

		test.clickOnToTime();
		Log.info("Clicked on To Time");

		test.clickOn4pm();
		Log.info("Clicked on 4:00 PM");

		test.clickOnToDone();
		Log.info("Clicked on Done");

		test.clickOnParticipants();
		Log.info("Clicked on Participants");

		// Selecting 1st Lead for Meeting
		test.enterName();
		Log.info("Entered Name");

		// Selecting 2nd Lead for Meeting
		test.enterName1();
		Log.info("Entered Another Name");

		//
		test.clickOnSave2();
		Log.info("Clicked on Save Button");

		test.clickOnSave3();
		Log.info("Clicked on Save Button");

		test.clickOnDate();
		Log.info("Clicked on Date");

		test.clickOnMeetings();
		Log.info("Clicked on Meetings");

		test.clickOnDetails();
		Log.info("Clicked on Details");

		test.clickOnBack9();
		Log.info("Clicked on Back Button");

		// Delete Scenario

		test.clickOnMeetings1();
		Log.info("Clicked on Meetings");

		test.clickOnIcon();
		Log.info("Clicked on Icon");

		test.clickOnDelete();
		Log.info("Clicked on Delete Button");

		test.clickOnMenu2();
		Log.info("Clicked on Menu Icon");

		test.clickOnLeads1();
		Log.info("Clicked on Leads");

		test.clickOnNakul();
		Log.info("Clicked on Name : Nakul");

		test.clickOnIcon1();
		Log.info("Clicked on Icon");

		test.clickOnDelete1();
		Log.info("Clicked on Delete Button");

		test.clickOnAlex();
		Log.info("Clicked on Name : Alex");

		test.clickOnIcon2();
		Log.info("Clicked on Icon");

		test.clickOnDelete2();
		Log.info("Clicked on Delete Button");

		// Sign Out
		test.clickOnMenu3();
		Log.info("Clicked on Menu Icon");

		test.clickOnSettings();
		Log.info("Clicked on Settings Icon");

		test.clickOnSignOut();
		Log.info("Clicked on Sign Out");

		test.clickOnSignOutButton();
		Log.info("Clicked on Sign Out Button");

		Log.endTestCase("Test_Case_ID : 001 :Verified Zoho CRM App");
	}
}