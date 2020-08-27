package edw.edw.frameLib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListener implements IReporter {

	private static final String OUTPUT_FOLDER = System.getProperty("user.dir") + "\\test-output\\";
	private static final String FILE_NAME = "Zoho_CRM_App_Execution_Report_";
	private static final String FORMAT = ".html";

	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		init();

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);

			}
		}

		for (String s : Reporter.getOutput()) {
			extent.setTestRunnerOutput(s);
		}

		extent.flush();
	}

	private void init() {
		//
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		//

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER+FILE_NAME+formattedDate+FORMAT);
		// String kumaresh = OUTPUT_FOLDER+FILE_NAME+formattedDate+FORMAT;
		// System.out.println("Final File : " + kumaresh);
		// ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME + FORMAT);
		htmlReporter.config().setDocumentTitle("Zoho CRM App Project");
		htmlReporter.config().setReportName("Zoho CRM App Project Execution");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

		/*try {
			Thread.sleep(3000);
			System.setProperty("webdriver.chrome.driver", "K:\\EDW_Appium\\resources\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			// driver.get(kumaresh);
			
			if (driver instanceof JavascriptExecutor) {
			    ((JavascriptExecutor)driver).executeScript(kumaresh);
			} else {
			    throw new IllegalStateException("This driver does not support JavaScript!");
			}
			
			driver.navigate().to(kumaresh);
			driver.navigate().refresh();
			
			System.out.println("Launched the Report");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Launch Report Error : " +e);
		}*/
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) { 
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}