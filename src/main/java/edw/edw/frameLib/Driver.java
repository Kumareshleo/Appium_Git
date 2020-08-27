package edw.edw.frameLib;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType.ForSeleniumServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.net.URL;

public class Driver {

	protected AndroidDriver driver;
	protected static WebDriver listenerDriver;
	protected static WebDriverWait wait;
	public static List<String> browsers= new ArrayList<String>();
	public static List<String> browserVersions=new ArrayList<String>();
	protected String appURL;
	protected String mainWindowHandle;
	public static Connection connection;
	public static Statement stmt;
	public static ResultSet resultSet;
	public static String appUrl;
	public int time = 30;

	@BeforeMethod
	@Parameters({ "deviceType","appType","apkFileName" })
	public void Initialize(
			@Optional("mobile") String deviceType,@Optional("mobileApp") String appType, String apkFile) throws MalformedURLException {
		//		this.appURL = url;
		//		this.time = maxWaitTimeToFindElement;
		String projectPath = System.getProperty("user.dir"); // dynamic project
		// path

		startAppiumServer();
		File classpathRoot = new File(System.getProperty("user.dir"));

		// Path to <project folder>/Apps -> Amazon
		File appDir = new File(classpathRoot, "/app/");

		// Path to <project folder>/Apps -> Amazon/Amozon apk file
		File app = new File(appDir, "Zoho CRM Sales Marketing_v3.4.9.1_apkpure.com.apk");
		// File app = new File(appDir, "swiggy-3-39-3.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		// capabilities.setCapability("deviceName", "14322bc9");
		// capabilities.setCapability(CapabilityType.VERSION, "10");
		capabilities.setCapability("deviceName", "Demo");
		capabilities.setCapability(CapabilityType.VERSION, "8.1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		// capabilities.setCapability("appPackage", "om.gov.mara.maracal");
		// capabilities.setCapability("appPackage", "com.google.android.deskclock");
		// capabilities.setCapability("appPackage", "io.selendroid.testapp");
		// capabilities.setCapability("appPackage", "com.google.android.calendar");
		// capabilities.setCapability("appPackage", "com.graph.weather.forecast.channel");
		// capabilities.setCapability("appPackage", "com.sms.messenger.allinone");
		// capabilities.setCapability("appPackage", "com.eterno");
		// capabilities.setCapability("appActivity", "com.newshunt.onboarding.view.activity.OnBoardingActivity");
		// capabilities.setCapability("appPackage", "com.google.android.calculator");
		// capabilities.setCapability("appPackage", "cz.hipercalc");
		capabilities.setCapability("appPackage", "com.zoho.crm");
		capabilities.setCapability("appWaitActivity", "com.zoho.crm.*");
		/*capabilities.setCapability("appPackage", "in.swiggy.android");
		capabilities.setCapability("appWaitActivity", "in.swiggy.android.*");*/
		// capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);

		// capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PageSetup();

		if (deviceType.equalsIgnoreCase("desktop")) {
			// Maximize the Window
			driver.manage().window().maximize();
		}

		listenerDriver = driver;
	}

	@BeforeSuite
	public void Database() throws Exception {
		try {
			DOMConfigurator.configure("log4j.xml");
			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// connection =
			// DriverManager.getConnection("jdbc:sqlserver://ARLMSAGQA02:10433;user=webusercima;password=webusercima;database=DBS4PORTAL");
			// stmt =
			// connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			// ResultSet.CONCUR_READ_ONLY);
			Log.info("Environment Configuration Ready");
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Environment Configuration error");
		}
	}

	public void PageSetup() {
	}

	public void WaitForExecution(Long timeInMicroSeconds) {
		try {
			Thread.sleep(timeInMicroSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() throws Exception {
		stopAppiumServer();
	}

	public List<String> browserName() {
		return browsers;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public List<String> browserVersion() {
		return browserVersions;
	}

	/*@BeforeClass
	public void beforeClass() {

		ReadExcel.setExcelFile("loginApplication");
	}*/

	@AfterMethod
	public void drop() {
		try {
			driver.quit();
		} catch (Exception e) {
			Log.info("Session already closed.");
		}

	}

	public static void startAppiumServer() {
		CommandLine cmd = new CommandLine("C:\\Program Files\\Appium\\node.exe");
		cmd.addArgument("C:\\Program Files\\Appium\\node_modules\\appium\\bin\\Appium.js");
		cmd.addArgument("--address");
		cmd.addArgument("127.0.0.1");
		cmd.addArgument("--port");
		cmd.addArgument("4723");

		DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(cmd, handler);
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopAppiumServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//	public static void main(String args[]) throws MalformedURLException
	//	{
	//		startAppiumServer();
	//		File classpathRoot = new File(System.getProperty("user.dir"));
	//
	//		// Path to <project folder>/Apps -> Amazon
	//		File appDir = new File(classpathRoot, "/app/");
	//
	//		// Path to <project folder>/Apps -> Amazon/Amozon apk file
	//		File app = new File(appDir, "Omani Calendar_v8.22_apkpure.com.apk");
	//
	//		 DesiredCapabilities capabilities = new DesiredCapabilities();
	//		 
	//		capabilities.setCapability("deviceName", "Pixel_2_API_30");
	//		capabilities.setCapability(CapabilityType.VERSION, "11.0");
	//		capabilities.setCapability("platformName", "Android");
	//		 capabilities.setCapability("app", app.getAbsolutePath());
	//		capabilities.setCapability("appPackage", "om.gov.mara.maracal");
	//		// capabilities.setCapability("appActivity", "om.gov.mara.maracal.ui.home.HomeActivity");
	//
	//		
	//			 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
	//			 AndroidDriver<?> driverm = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	//		
	//		
	//	
	//		 driverm.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	//		 try {
	//			Thread.sleep(10000);
	//		} catch (InterruptedException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//
	//	}

}
