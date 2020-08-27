package edw.edw.frameLib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter  {
	WebDriver driver;
	private static String fileSeperator = System.getProperty("file.separator");


	@Override
	public void onTestSuccess(ITestResult result) {
		Log.error(result.getName() + " Passed");

		driver = edw.edw.frameLib.Driver.listenerDriver;

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";

		if (driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots" + fileSeperator + "Results" + fileSeperator
					+ testClassName + fileSeperator + takeScreenShot(driver, screenShotName, testClassName);
			Log.info("Screenshot can be found : " + imagePath);
		}
	}

	public static String takeScreenShot(WebDriver driver, String screenShotName, String testName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				Log.info("File created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName,
					screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			Log.error("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}
}