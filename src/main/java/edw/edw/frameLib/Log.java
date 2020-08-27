
package edw.edw.frameLib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Log {

	// Initialize Log4j logs

	private static Logger Log = Logger.getLogger(Log.class.getName());//

	// This is to print log for the beginning of the test case, as we usually
	// run so many test cases as a test suite

	public static void startTestCase(String sTestCaseName) {

		Log.info("****************************************************************************************");

		Log.info("***               " + sTestCaseName + "       ***");
		Reporter.log("Test Case : " + sTestCaseName);
		Log.info("****************************************************************************************");
	}

	// This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName) {

		Log.info("****************************************************************************************");

		Log.info("***                 " + sTestCaseName + "      ***");
		Reporter.log("Test Case : " + sTestCaseName);
		Log.info("****************************************************************************************");

	}

	// Need to create these methods, so that they can be called

	public static void info(String message) {

		Log.info(message);

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log(message);

	}

	public static void warn(String message) {

		Log.warn(message);
		Reporter.log(message);

	}

	public static void error(String message) {

		Log.error(message);
		Reporter.log(message);

	}

	public static void fatal(String message) {

		Log.fatal(message);
		Reporter.log(message);

	}

	public static void debug(String message) {

		Log.debug(message);
		Reporter.log(message);

	}

	public static void failed() {
	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}
}
