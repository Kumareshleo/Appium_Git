package edw.edw.frameLib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.collections.Lists;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

/**
 * Reporter that generates a single-page HTML report of the suite test results.
 * <p>
 * Based on TestNG built-in implementation:
 * org.testng.reporters.EmailableReporter2
 * </p>
 */
public class CustomReport extends Driver  implements IReporter {

	public static File lastModifiedFile;
	private static final Logger LOG = Logger.getLogger(CustomReport.class);
	private static String timeZone = "GMT+5:30";
	private static SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
	private static SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss a");
	private static String outFilename = "custom-report.html";
	private static NumberFormat integerFormat = NumberFormat.getIntegerInstance();
	private static NumberFormat decimalFormat = NumberFormat.getNumberInstance();
	protected PrintWriter writer;
	protected List<SuiteResult> suiteResults = Lists.newArrayList();
	private StringBuilder buffer = new StringBuilder();
	static int totalPassedTests = 0;
	static int totalSkippedTests = 0;
	static int totalFailedTests = 0;
	static long totalDuration = 0;
	static int totalTests = 0;
	static int totalExecutedTests = 0;
	static int testIndex = 0;
	static String environmentName;
	static String suiteType;
	static String time;
	static String application = "unknown";
	static String buildNumber = "for Sprint 40, Build 1.40.168";
	public String target;
	public static WebDriverWait wait;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		try {
			writer = createWriter(outputDirectory);
		} catch (IOException e) {
			LOG.error("Unable to create output file", e);
			return;
		}
		for (ISuite suite : suites) {
			suiteResults.add(new SuiteResult(suite));
		}

		writeDocumentStart();
		writeHead();
		writeBody();
		writeDocumentEnd();

		writer.close();

		try {
			launchreport();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//javamail();
		mail();
	}

	protected PrintWriter createWriter(String outdir) throws IOException {
		new File(outdir).mkdirs();
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, outFilename))));
	}

	protected void writeDocumentStart() {
		writer.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		writer.print("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
	}

	protected void writeHead() {
		writer.print("<head>");
		// writer.print("<title>EDW-UI Execution Report</title>");
		writer.print("<title>Zoho CRM App Execution Report</title>");
		writeStylesheet();
		writer.print("</head>");
	}

	protected void writeStylesheet() {
		writer.print("<style type=\"text/css\">");
		writer.print("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
		writer.print("th,td {border:1px solid #009;padding:.25em .5em}");
		writer.print("th {vertical-align:bottom}");
		writer.print("td {vertical-align:top}");
		writer.print("table a {font-weight:bold}");
		writer.print(".stripe td {background-color: #E6EBF9}");
		writer.print(".num {text-align:right}");
		writer.print(".passedodd td {background-color: #3F3}");
		writer.print(".passedeven td {background-color: #0A0}");
		writer.print(".skippedodd td {background-color: #DDD}");
		writer.print(".skippedeven td {background-color: #CCC}");
		writer.print(".failedodd td,.attn {background-color: #F33}");
		writer.print(".failedeven td,.stripe .attn {background-color: #D00}");
		writer.print(".stacktrace {white-space:pre;font-family:monospace}");
		writer.print(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
		writer.print("</style>");
	}
	protected void writeBody() {
		writer.print("<body>");
		writeReportTitle("UI Automation Execution Results");
		writeConfigurationDetails();
		writeSuiteSummary();
		writeScenarioSummary();
		//writeScenarioDetails();
		writer.print("</body>");
	}

	protected void writeReportTitle(String title) {
		writer.print("<a name = \"summary\"> </a>");
		writer.print("<center><h1 style=\"color:#5D7B9D\">" + title + "</h1></center>");
		writer.print(
				"<center><h2><img src=\"https://img.sandboxcernerhealth.com/34a2fa94f83d40f1bad87a3945d037fe/Zoho_CRM_App_RGB-Color.png\" title='EDW' alt='EDW' name='graphics1' align='CENTER' border='0' height='100' width='300'></h2></center>");
		//	writer.print("<center><h3 style=\"color:#5D7B9D\">" + " @ All copyrights are reserved." + "</h3></center>");
		writer.print("<center><h3 style=\"color:#5D7B9D\">" + "Executed On" + " : " + getDateAsString() + "</h3></center>");

	}

	protected void writeDocumentEnd() {
		writer.print("</html>");
	}

	public void writeConfigurationDetails()

	{
		String url = getAppUrl();
		application = url;
		int index=0;

		for (SuiteResult suiteResult : suiteResults) {

			for (TestResult testResult : suiteResult.getTestResults()) {

				//				String browserName =  browserName().get(index);
				//				String browserVersion =  browserVersion().get(index);
				//				String operatingSystem = System.getProperty("os.name");
				//	String javaRunTimeVersion = System.getProperty("java.runtime.version");
				suiteType = Utils.escapeHtml(suiteResult.getSuiteName());
				environmentName = Utils.escapeHtml(testResult.getTestName());
				writer.print("<table align=\"center\">");
				writer.print(
						"<tr><th colspan=\"6\" bgcolor=\"#5D7B9D\"><font color=\"FFFFFF\">Configuration Details</font></th></tr>");
				writer.print("<tr>");
				writer.print("<th>Suite Type</th>");
				writer.print("<th>Environment</th>");
				writer.print("<th>Application URL</th>");
				writer.print("<th>Operating System</th>");
				writer.print("<th>Browser</th>");
				writer.print("<th>Browser Version</th>");
				//	writer.print("<th>Java RunTime Version</th>");
				writer.print("</tr>");
				writer.print("<tr>");
				writer.print("<th>");
				writer.print(Utils.escapeHtml(suiteResult.getSuiteName()));
				writer.print("</th>");
				writer.print("<th>");
				writer.print(Utils.escapeHtml(testResult.getTestName()));
				writer.print("</th>");
				//				writer.print("<th>" + url + "</th>");
				//				writer.print("<th>" + operatingSystem + "</th>");
				//				writer.print("<th>" + browserName + "</th>");
				//				writer.print("<th>" + browserVersion + "</th>");
				//	writer.print("<th>" + javaRunTimeVersion + "</th>");
				writer.print("</tr>");
				writer.print("</table>");
				index++;
			}
		}
	}

	protected void writeSuiteSummary() {

		writer.print("<table align=\"center\">");
		writer.print("<tr bgcolor=\"#5D7B9D\"><font color=\"FFFFFF\">");
		writer.print("<th>Test</th>");
		writer.print("<th>TotalTests</th>");
		writer.print("<th># Passed</th>");
		writer.print("<th># Skipped</th>");
		writer.print("<th># Failed</th>");
		writer.print("<th>Minutes</th>");
		//writer.print("<th>Included Groups</th>");
		//writer.print("<th>Excluded Groups</th>");
		writer.print("</font></tr>");

		for (SuiteResult suiteResult : suiteResults) {

			for (TestResult testResult : suiteResult.getTestResults()) {
				int passedTests = testResult.getPassedTestCount();
				int totalTestCount = testResult.getPassedTestCount() + testResult.getFailedTestCount()
				+ testResult.getSkippedTestCount();
				int skippedTests = testResult.getSkippedTestCount();
				int failedTests = testResult.getFailedTestCount();
				int executedTests = testResult.getPassedTestCount() + testResult.getFailedTestCount();
				long duration = testResult.getDuration();

				writer.print("<tr");
				if ((testIndex % 2) == 1) {
					writer.print(" class=\"stripe\"");
				}
				writer.print(">");

				buffer.setLength(0);
				writeTableData(buffer.append("<a href=\"#t").append(testIndex).append("\">")
						.append(Utils.escapeHtml(testResult.getTestName())).append("</a>").toString());
				writeTableData(integerFormat.format(totalTestCount), "num");
				writeTableData(integerFormat.format(passedTests), "num");
				writeTableData(integerFormat.format(skippedTests), (skippedTests > 0 ? "num attn" : "num"));
				writeTableData(integerFormat.format(failedTests), (failedTests > 0 ? "num attn" : "num"));
				writeTableData(decimalFormat.format(secondsToMinutes(millisecondsToSeconds(duration))), "num");
				//	writeTableData(testResult.getIncludedGroups());
				//	writeTableData(testResult.getExcludedGroups());

				writer.print("</tr>");

				totalPassedTests += passedTests;
				totalSkippedTests += skippedTests;
				totalFailedTests += failedTests;
				totalDuration += duration;
				totalTests += totalTestCount;
				totalExecutedTests += executedTests;

				testIndex++;
			}
		}

		// Print totals if there was more than one test
		if (testIndex >= 1) {
			writer.print("<tr>");
			writer.print("<th>Total</th>");
			writeTableHeader(integerFormat.format(totalTests), "num");
			writeTableHeader(integerFormat.format(totalPassedTests), "num");
			writeTableHeader(integerFormat.format(totalSkippedTests), (totalSkippedTests > 0 ? "num attn" : "num"));
			writeTableHeader(integerFormat.format(totalFailedTests), (totalFailedTests > 0 ? "num attn" : "num"));
			writeTableHeader(decimalFormat.format(secondsToMinutes(millisecondsToSeconds(totalDuration))), "num");
			writer.print("<th colspan=\"2\"></th>");
			writer.print("</tr>");
			time = decimalFormat.format(secondsToMinutes(millisecondsToSeconds(totalDuration)));
		}

		writer.print("</table>");
	}

	/**
	 * Writes a summary of all the test scenarios.
	 */
	protected void writeScenarioSummary() {
		writer.print("<table align=\"center\">");
		writer.print("<thead>");
		writer.print("<tr bgcolor=\"#5D7B9D\"><font color=\"FFFFFF\">");
		writer.print("<th>Class</th>");
		writer.print("<th>Method</th>");
		writer.print("<th>Name</th>");
		writer.print("<th>Start</th>");
		writer.print("<th>Seconds</th>");
		writer.print("</font></tr>");
		writer.print("</thead>");

		int testIndex = 0;
		int scenarioIndex = 0;
		for (SuiteResult suiteResult : suiteResults) {

			for (TestResult testResult : suiteResult.getTestResults()) {
				writer.print("<tbody id=\"t");
				writer.print(testIndex);
				writer.print("\">");

				String testName = Utils.escapeHtml(testResult.getTestName());

				scenarioIndex += writeScenarioSummary(testName + " &#8212; failed (configuration methods)",
						testResult.getFailedConfigurationResults(), "failed", scenarioIndex);
				scenarioIndex += writeScenarioSummary(testName + " &#8212; failed", testResult.getFailedTestResults(),
						"failed", scenarioIndex);
				scenarioIndex += writeScenarioSummary(testName + " &#8212; skipped (configuration methods)",
						testResult.getSkippedConfigurationResults(), "skipped", scenarioIndex);
				scenarioIndex += writeScenarioSummary(testName + " &#8212; skipped", testResult.getSkippedTestResults(),
						"skipped", scenarioIndex);
				scenarioIndex += writeScenarioSummary(testName + " &#8212; passed", testResult.getPassedTestResults(),
						"passed", scenarioIndex);

				writer.print("</tbody>");

				testIndex++;
			}
		}

		writer.print("</table>");
	}

	/**
	 * Writes the scenario summary for the results of a given state for a single
	 * test.
	 */
	private int writeScenarioSummary(String description, List<ClassResult> classResults, String cssClassPrefix,
			int startingScenarioIndex) {
		int scenarioCount = 0;
		if (!classResults.isEmpty()) {
			writer.print("<tr><th colspan=\"5\">");
			writer.print(description);
			writer.print("</th></tr>");

			int scenarioIndex = startingScenarioIndex;
			int classIndex = 0;
			for (ClassResult classResult : classResults) {
				String cssClass = cssClassPrefix + ((classIndex % 2) == 0 ? "even" : "odd");

				buffer.setLength(0);

				int scenariosPerClass = 0;
				int methodIndex = 0;
				for (MethodResult methodResult : classResult.getMethodResults()) {
					List<ITestResult> results = methodResult.getResults();
					int resultsCount = results.size();
					assert resultsCount > 0;

					ITestResult aResult = results.iterator().next();
					String methodName = Utils.escapeHtml(aResult.getMethod().getMethodName());
					long start = aResult.getStartMillis();
					long duration = aResult.getEndMillis() - start;

					// The first method per class shares a row with the class
					// header
					if (methodIndex > 0) {
						buffer.append("<tr class=\"").append(cssClass).append("\">");

					}

					// Write the timing information with the first scenario per
					// method
					buffer.append("<td><a href=\"#m").append(scenarioIndex).append("\">")
					.append(methodName + "</a></td>").append("<td rowspan=\"1\">" + aResult.getName() + "</td>")
					.append("<td rowspan=\"").append(resultsCount).append("\">")
					.append(parseUnixTimeToTimeOfDay(start)).append("</td>").append("<td rowspan=\"")
					.append(resultsCount).append("\">")
					.append(decimalFormat.format(millisecondsToSeconds(duration))).append("</td></tr>");
					scenarioIndex++;

					// Write the remaining scenarios for the method
					for (int i = 1; i < resultsCount; i++) {
						buffer.append("<tr class=\"").append(cssClass)
						.append("\">").append("<td><a href=\"#m")
						.append(scenarioIndex).append("\">")
						.append(methodName + "</a></td>")
						.append("<td rowspan=\"1\">" + aResult.getName() + "</td></tr>");
						scenarioIndex++;
					}

					scenariosPerClass += resultsCount;
					methodIndex++;
				}

				// Write the test results for the class
				writer.print("<tr class=\"");
				writer.print(cssClass);
				writer.print("\">");
				writer.print("<td rowspan=\"");
				writer.print(scenariosPerClass);
				writer.print("\">");
				writer.print(Utils.escapeHtml(classResult.getClassName()));
				writer.print("</td>");
				writer.print(buffer);

				classIndex++;
			}
			scenarioCount = scenarioIndex - startingScenarioIndex;
		}
		return scenarioCount;
	}

	/**
	 * Writes the details for all test scenarios.
	 */
	protected void writeScenarioDetails() {
		int scenarioIndex = 0;
		for (SuiteResult suiteResult : suiteResults) {
			for (TestResult testResult : suiteResult.getTestResults()) {
				writer.print("<h2>");
				writer.print(Utils.escapeHtml(testResult.getTestName()));
				writer.print("</h2>");

				scenarioIndex += writeScenarioDetails(testResult.getFailedConfigurationResults(), scenarioIndex);
				scenarioIndex += writeScenarioDetails(testResult.getFailedTestResults(), scenarioIndex);
				scenarioIndex += writeScenarioDetails(testResult.getSkippedConfigurationResults(), scenarioIndex);
				scenarioIndex += writeScenarioDetails(testResult.getSkippedTestResults(), scenarioIndex);
				scenarioIndex += writeScenarioDetails(testResult.getPassedTestResults(), scenarioIndex);
			}
		}
	}

	/**
	 * Writes the scenario details for the results of a given state for a single
	 * test.
	 */
	private int writeScenarioDetails(List<ClassResult> classResults, int startingScenarioIndex) {
		int scenarioIndex = startingScenarioIndex;
		for (ClassResult classResult : classResults) {
			String className = classResult.getClassName();
			for (MethodResult methodResult : classResult.getMethodResults()) {
				List<ITestResult> results = methodResult.getResults();
				assert!results.isEmpty();

				ITestResult mResult = results.iterator().next();
				String label = Utils.escapeHtml(
						className + "#" + mResult.getMethod().getMethodName() + " ( " + mResult.getName() + " )");
				for (ITestResult result : results) {
					writeScenario(scenarioIndex, label, result);
					scenarioIndex++;
				}
			}
		}

		return scenarioIndex - startingScenarioIndex;
	}

	/**
	 * Writes the details for an individual test scenario.
	 */
	private void writeScenario(int scenarioIndex, String label, ITestResult result) {
		writer.print("<h3 id=\"m");
		writer.print(scenarioIndex);
		writer.print("\">");
		writer.print(label);
		writer.print("</h3>");

		writer.print("<table class=\"result\">");

		// Write test parameters (if any)
		Object[] parameters = result.getParameters();
		int parameterCount = (parameters == null ? 0 : parameters.length);
		if (parameterCount > 0) {
			writer.print("<tr class=\"param\">");
			for (int i = 1; i <= parameterCount; i++) {
				writer.print("<th>Parameter #");
				writer.print(i);
				writer.print("</th>");
			}
			writer.print("</tr><tr class=\"param stripe\">");
			for (Object parameter : parameters) {
				writer.print("<td>");
				writer.print(Utils.escapeHtml(Utils.toString(parameter, Object.class)));
				writer.print("</td>");
			}
			writer.print("</tr>");
		}

		// Write reporter messages (if any)
		List<String> reporterMessages = Reporter.getOutput(result);
		if (!reporterMessages.isEmpty()) {
			writer.print("<tr><th");
			if (parameterCount > 1) {
				writer.print(" colspan=\"");
				writer.print(parameterCount);
				writer.print("\"");
			}
			writer.print(">Messages</th></tr>");

			writer.print("<tr><td");
			if (parameterCount > 1) {
				writer.print(" colspan=\"");
				writer.print(parameterCount);
				writer.print("\"");
			}
			writer.print(">");
			writeReporterMessages(reporterMessages);
			writer.print("</td></tr>");
		}

		// Write exception (if any)
		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			writer.print("<tr><th");
			if (parameterCount > 1) {
				writer.print(" colspan=\"");
				writer.print(parameterCount);
				writer.print("\"");
			}
			writer.print(">");
			writer.print((result.getStatus() == ITestResult.SUCCESS ? "Expected Exception" : "Exception"));
			writer.print("</th></tr>");

			writer.print("<tr><td");
			if (parameterCount > 1) {
				writer.print(" colspan=\"");
				writer.print(parameterCount);
				writer.print("\"");
			}
			writer.print(">");
			writeStackTrace(throwable);
			writer.print("</td></tr>");
		}

		writer.print("</table>");
		writer.print("<p class=\"totop\"><a href=\"#summary\">Back To Summary</a></p>");
	}

	protected void writeReporterMessages(List<String> reporterMessages) {
		writer.print("<div class=\"messages\">");
		Iterator<String> iterator = reporterMessages.iterator();
		assert iterator.hasNext();
		writer.print(Utils.escapeHtml(iterator.next()));
		while (iterator.hasNext()) {
			writer.print("<br/>");
			writer.print(Utils.escapeHtml(iterator.next()));
		}
		writer.print("</div>");
	}

	protected void writeStackTrace(Throwable throwable) {
		writer.print("<div class=\"stacktrace\">");
		writer.print(Utils.stackTrace(throwable, true)[0]);
		writer.print("</div>");
	}

	/**
	 * Writes a TH element with the specified contents and CSS class names.
	 *
	 * @param html  the HTML contents
	 * @param cssClasses
	 *            the space-delimited CSS classes or null if there are no
	 *            classes to apply
	 */
	protected void writeTableHeader(String html, String cssClasses) {
		writeTag("th", html, cssClasses);
	}

	/**
	 * Writes a TD element with the specified contents.
	 *
	 * @param html
	 *            the HTML contents
	 */
	protected void writeTableData(String html) {
		writeTableData(html, null);
	}

	/**
	 * Writes a TD element with the specified contents and CSS class names.
	 *
	 * @param html
	 *            the HTML contents
	 * @param cssClasses
	 *            the space-delimited CSS classes or null if there are no
	 *            classes to apply
	 */
	protected void writeTableData(String html, String cssClasses) {
		writeTag("td", html, cssClasses);
	}

	/**
	 * Writes an arbitrary HTML element with the specified contents and CSS
	 * class names.
	 *
	 * @param tag
	 *            the tag name
	 * @param html
	 *            the HTML contents
	 * @param cssClasses
	 *            the space-delimited CSS classes or null if there are no
	 *            classes to apply
	 */
	protected void writeTag(String tag, String html, String cssClasses) {
		writer.print("<");
		writer.print(tag);
		if (cssClasses != null) {
			writer.print(" class=\"");
			writer.print(cssClasses);
			writer.print("\"");
		}
		writer.print(">");
		writer.print(html);
		writer.print("</");
		writer.print(tag);
		writer.print(">");
	}

	/**
	 * Groups {@link TestResult}s by suite.
	 */
	protected static class SuiteResult {
		private final String suiteName;
		private final List<TestResult> testResults = Lists.newArrayList();

		public SuiteResult(ISuite suite) {
			suiteName = suite.getName();
			for (ISuiteResult suiteResult : suite.getResults().values()) {
				testResults.add(new TestResult(suiteResult.getTestContext()));
			}
		}

		public String getSuiteName() {
			return suiteName;
		}

		/**
		 * @return the test results (possibly empty)
		 */
		public List<TestResult> getTestResults() {
			return testResults;
		}
	}

	/**
	 * Groups {@link ClassResult}s by test, type (configuration or test), and
	 * status.
	 */
	protected static class TestResult {
		/**
		 * Orders test results by class name and then by method name (in
		 * lexicographic order).
		 */
		protected static final Comparator<ITestResult> RESULT_COMPARATOR = new Comparator<ITestResult>() {
			// @Override
			public int compare(ITestResult o1, ITestResult o2) {
				int result = o1.getTestClass().getName().compareTo(o2.getTestClass().getName());
				if (result == 0) {
					result = o1.getMethod().getMethodName().compareTo(o2.getMethod().getMethodName());
				}
				return result;
			}
		};

		private final String testName;
		private final List<ClassResult> failedConfigurationResults;
		private final List<ClassResult> failedTestResults;
		private final List<ClassResult> skippedConfigurationResults;
		private final List<ClassResult> skippedTestResults;
		private final List<ClassResult> passedTestResults;
		private final int failedTestCount;
		private final int skippedTestCount;
		private final int passedTestCount;
		private final long duration;
		private final String includedGroups;
		private final String excludedGroups;

		public TestResult(ITestContext context) {
			testName = context.getName();

			Set<ITestResult> failedConfigurations = context.getFailedConfigurations().getAllResults();
			Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
			Set<ITestResult> skippedConfigurations = context.getSkippedConfigurations().getAllResults();
			Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
			Set<ITestResult> passedTests = context.getPassedTests().getAllResults();

			failedConfigurationResults = groupResults(failedConfigurations);
			failedTestResults = groupResults(failedTests);
			skippedConfigurationResults = groupResults(skippedConfigurations);
			skippedTestResults = groupResults(skippedTests);
			passedTestResults = groupResults(passedTests);

			failedTestCount = failedTests.size();
			skippedTestCount = skippedTests.size();
			passedTestCount = passedTests.size();

			duration = context.getEndDate().getTime() - context.getStartDate().getTime();

			includedGroups = formatGroups(context.getIncludedGroups());
			excludedGroups = formatGroups(context.getExcludedGroups());
		}

		/**
		 * Groups test results by method and then by class.
		 */
		protected List<ClassResult> groupResults(Set<ITestResult> results) {
			List<ClassResult> classResults = Lists.newArrayList();
			if (!results.isEmpty()) {
				List<MethodResult> resultsPerClass = Lists.newArrayList();
				List<ITestResult> resultsPerMethod = Lists.newArrayList();

				List<ITestResult> resultsList = Lists.newArrayList(results);
				Collections.sort(resultsList, RESULT_COMPARATOR);
				Iterator<ITestResult> resultsIterator = resultsList.iterator();
				assert resultsIterator.hasNext();

				ITestResult result = resultsIterator.next();
				resultsPerMethod.add(result);

				String previousClassName = result.getTestClass().getName();
				String previousMethodName = result.getMethod().getMethodName();
				while (resultsIterator.hasNext()) {
					result = resultsIterator.next();

					String className = result.getTestClass().getName();
					if (!previousClassName.equals(className)) {
						// Different class implies different method
						assert!resultsPerMethod.isEmpty();
						resultsPerClass.add(new MethodResult(resultsPerMethod));
						resultsPerMethod = Lists.newArrayList();

						assert!resultsPerClass.isEmpty();
						classResults.add(new ClassResult(previousClassName, resultsPerClass));
						resultsPerClass = Lists.newArrayList();

						previousClassName = className;
						previousMethodName = result.getMethod().getMethodName();
					} else {
						String methodName = result.getMethod().getMethodName();
						if (!previousMethodName.equals(methodName)) {
							assert!resultsPerMethod.isEmpty();
							resultsPerClass.add(new MethodResult(resultsPerMethod));
							resultsPerMethod = Lists.newArrayList();

							previousMethodName = methodName;
						}
					}
					resultsPerMethod.add(result);
				}
				assert!resultsPerMethod.isEmpty();
				resultsPerClass.add(new MethodResult(resultsPerMethod));
				assert!resultsPerClass.isEmpty();
				classResults.add(new ClassResult(previousClassName, resultsPerClass));
			}
			return classResults;
		}

		public String getTestName() {
			return testName;
		}

		/**
		 * @return the results for failed configurations (possibly empty)
		 */
		public List<ClassResult> getFailedConfigurationResults() {
			return failedConfigurationResults;
		}

		/**
		 * @return the results for failed tests (possibly empty)
		 */
		public List<ClassResult> getFailedTestResults() {
			return failedTestResults;
		}

		/**
		 * @return the results for skipped configurations (possibly empty)
		 */
		public List<ClassResult> getSkippedConfigurationResults() {
			return skippedConfigurationResults;
		}

		/**
		 * @return the results for skipped tests (possibly empty)
		 */
		public List<ClassResult> getSkippedTestResults() {
			return skippedTestResults;
		}

		/**
		 * @return the results for passed tests (possibly empty)
		 */
		public List<ClassResult> getPassedTestResults() {
			return passedTestResults;
		}

		public int getFailedTestCount() {
			return failedTestCount;
		}

		public int getSkippedTestCount() {
			return skippedTestCount;
		}

		public int getPassedTestCount() {
			return passedTestCount;
		}

		public long getDuration() {
			return duration;
		}

		public String getIncludedGroups() {
			return includedGroups;
		}

		public String getExcludedGroups() {
			return excludedGroups;
		}

		/**
		 * Formats an array of groups for display.
		 */
		protected String formatGroups(String[] groups) {
			if (groups.length == 0) {
				return "";
			}

			StringBuilder builder = new StringBuilder();
			builder.append(groups[0]);
			for (int i = 1; i < groups.length; i++) {
				builder.append(", ").append(groups[i]);
			}
			return builder.toString();
		}
	}

	/**
	 * Groups {@link MethodResult}s by class.
	 */
	protected static class ClassResult {
		private final String className;
		private final List<MethodResult> methodResults;

		/**
		 * @param className
		 *            the class name
		 * @param methodResults
		 *            the non-null, non-empty {@link MethodResult} list
		 */
		public ClassResult(String className, List<MethodResult> methodResults) {
			this.className = className;
			this.methodResults = methodResults;
		}

		public String getClassName() {
			return className;
		}

		/**
		 * @return the non-null, non-empty {@link MethodResult} list
		 */
		public List<MethodResult> getMethodResults() {
			return methodResults;
		}
	}

	/**
	 * Groups test results by method.
	 */
	protected static class MethodResult {
		private final List<ITestResult> results;

		/**
		 * @param results
		 *            the non-null, non-empty result list
		 */
		public MethodResult(List<ITestResult> results) {
			this.results = results;
		}

		/**
		 * @return the non-null, non-empty result list
		 */
		public List<ITestResult> getResults() {
			return results;
		}
	}

	/*
	 * Methods to improve time display on report
	 */
	protected String getDateAsString() {
		Date date = new Date();
		sdfdate.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdfdate.format(date);
	}

	protected String parseUnixTimeToTimeOfDay(long unixSeconds) {
		Date date = new Date(unixSeconds);
		sdftime.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdftime.format(date);
	}

	protected double millisecondsToSeconds(long ms) {
		return new BigDecimal(ms / 1000.00).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	protected double secondsToMinutes(double s) {
		return new BigDecimal(s / 60.00).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public static void SendMail()
	{
		/*// Recipient's email ID needs to be mentioned.
		String cc = "aman.garg@novaturetech.com";
       String to = "Raft Team0908@gmail.com";
       // Sender's email ID needs to be mentioned
       String from = "Raft Team@novaturetech.com";
       // Assuming you are sending email from local host
       String host = "192.168.0.167";   //local host ipv4 address
       // Get system properties
       Properties properties = System.getProperties();
       // Setup mail server
       properties.setProperty("smtp.gmail.com", host);
       // Get the default Session object.
       Session session = Session.getDefaultInstance(properties);*/

		//String host="smtp.gmail.com";  
		String to = "kumaresh@novaturebusiness.com";
		final String username = "careers@novaturetech.com";

		final String password = "welcome@123";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,

				new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);

			}

		});

		// message contains HTML mark-ups
		String msg = "<font> Hi All, </font><br><br>";
		msg+= "<font>We have executed automated "+suiteType+" Suite on Web Application "+buildNumber+ " deployed on "+application+" on "+ environmentName+" environment.</font><br><br>";
		msg+= "<table style='width:100%'><tr bgcolor=\"#D8D8D8\"><font color=\"FFFFFF\"><th>Suite Type</th><th>Environment</th><th>Total Tests</th><th>Total Executed</th><th>Passed</th><th>Failed</th><th>Skipped</th><th>Time(minutes)</th></font></tr><tr><th>"+suiteType+"</th><th><font color = \"#006633\">"+ environmentName + "</font></th><th><font color = \"#330000\">"+totalTests+"</font></th><th><font color = \"#9900FF\">"+totalExecutedTests+"</font></th><th><font color=\"#00CC00\">"+totalPassedTests+"</font></th><th><font color=\"#FF0000\">"+ totalFailedTests + "</font></th><th><font color = \"#3300CC\">"+totalSkippedTests+"</font></th><th><font color = \"#FF9900\">"+time+"</font></th></table><br>";
		msg+= "<i>" + suiteType +" report (custom-report.html) attached for more details.</i><br><br>";
		msg+="<font>--</font><br>";	  
		msg +="<i>Thanks,</i><br>";
		msg += "<b>RAFT Team</b><br>";
		msg += "<b>Test Engineer</b><br>";
		msg += "<b>NovatureTech Pvt Ltd, Chennai</b><br>";
		//	msg += "<a href='mailto:jaleel@novaturetech.com'>jaleel@novaturetech.com</a>";

		msg +=
				"<a href='mailto:kumaresh@novaturebusiness.com'>kumaresh@novaturebusiness.com</a>";

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));
			// Set To: header field of the header.
			/*message.addRecipient(Message.RecipientType.CC,
                                   new InternetAddress(cc));*/
			//   message.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Web application: "+ suiteType +"Suite (automated) - Passed on "+ environmentName + " environment "+buildNumber);

			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setContent(msg, "text/html");

			// Create a multi-part message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			String projectPath = System.getProperty("user.dir");	

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			WebDriverWait wait = new WebDriverWait(listenerDriver,50);
			String filename = projectPath +"\\test-output\\Zoho_CRM_App_Execution_Report.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			System.out.println("FileNamePath--->"+filename);

			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void javamail()
	{

		final String username = "careers@novaturetech.com";

		final String password = "welcome@123";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,

				new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);

			}

		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("careers@novaturetech.com" ));

			message.setRecipients(Message.RecipientType.TO,

					InternetAddress.parse("kumaresh@novaturebusiness.com"));

			message.setSubject("Appium Test Automation Report");

			BodyPart messageBodyPart=new MimeBodyPart();

			messageBodyPart.setText("Hi," + "\n\n" +

                "Zoho CRM Test Report" + "\n\n" + "Please find attachment for the detailed report\n\n\n\n\n\n" + "Thanks,\n" + "Raft Team\n" + "Test Engineer\n" + "NovatureTech Pvt Ltd");

			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			WebDriverWait wait = new WebDriverWait(listenerDriver, 15);
			String filename = "K:\\EDW_Appium\\test-output\\Zoho_CRM_App_Execution_Report.html";
			wait.until(ExpectedConditions.titleContains(filename));
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 
	 */
	public void configreport()
	{

		BufferedWriter bw = null;
		File file = new File("K:\\EDW_Appium\\test-output\\Zoho_CRM_App_Execution_Report.html");
		StringBuilder contentBuilder = new StringBuilder();
		try {
			//C:\\Users\\Nova011\\Desktop\\mypage.html
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		System.out.println("The Content is : \n" +content);

		//String data = "<a class='logo-content' href='http://extentreports.relevantcodes.com'>    <span>ExtentReports</span>    </a>";
		//String data1 = "<span>RaftReports</span>";

		target = content.replaceFirst("(?i)<a([^>]+)>(.+?)</a>", "<span>RAFT 2.0<span>");

		System.out.println("The updated html content is : \n" + target);
		FileWriter fw;
		try {
			File file1 = new File("K:\\EDW_Appium\\test-output\\Zoho_CRM_App_Execution_Report.html");
			fw = new FileWriter(file1);
			//fw = new FileWriter(System.getProperty("user.dir")+"/ExtentReport/MyReport.html");

			bw = new BufferedWriter(fw);
			bw.write(target);
			System.out.println("File written Successfully");
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void launchreport() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "K:\\EDW_Appium\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.get("file:///E://Project//jar//InfoFace//test-output//custom-report.html");
		File dir = new File("K:\\Appium_EDW\\test-output\\");
		File[] files = dir.listFiles();
		// System.out.println(files.length);
		lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++){
			// System.out.println(files[i].getName());
			BasicFileAttributes attrs1;
			BasicFileAttributes attrs;
			try {
				attrs1 = Files.readAttributes(lastModifiedFile.toPath(), BasicFileAttributes.class);
				FileTime time_lastFile = attrs1.creationTime();
				long time_lastFile_Millis = time_lastFile.toMillis();
				// System.out.println(time_lastFile_Millis);
				attrs = Files.readAttributes(files[i].toPath(), BasicFileAttributes.class);
				FileTime time_file = attrs.creationTime();
				long time_file_Millis = time_file.toMillis();
				// System.out.println(time_file_Millis);
				if (time_lastFile_Millis < time_file_Millis && files[i].getName().startsWith("Zoho") ) {
					lastModifiedFile = files[i];
					System.out.println(lastModifiedFile);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
		System.out.println("Current File :" + lastModifiedFile);
		Thread.sleep(2000);
		driver.get("K:\\Appium_EDW\\test-output\\" + lastModifiedFile.getName());
		driver.manage().window().maximize();
		driver.navigate().refresh();
		System.out.println("Launched the Report");
	}

	public void sendMailWithAuth(String host, String user, String password, 
			String port, String to, String htmlBody, 
			String subject) throws Exception 
	{

		Properties props = System.getProperties();

		props.put("mail.smtp.user",user); 
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		//props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");

		Session session = Session.getInstance(props, null);
		//session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));

		// To get the array of addresses

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(htmlBody, "text/html");

		Transport transport = session.getTransport("smtp");
		try {
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
		} finally {
			transport.close();
		}
	}

	//one more mail config
	public void send(           
			final String username,
			final String password,
			final String recipients,
			final String subject,
			final String body)
					throws Exception
	{
		final Session session = Session.getInstance(System.getProperties(), null);
		final Message msg = new MimeMessage(session);
		final String senderEmail = username.contains("@") ? username : (username + "@gmail.com");
		msg.setFrom(new InternetAddress(senderEmail));

		final Address[] recipientAddresses = InternetAddress.parse(recipients);
		msg.setRecipients(Message.RecipientType.TO, recipientAddresses);

		msg.setSentDate(new Date());
		msg.setSubject(subject);
		msg.setText(body);

		final Transport transport = session.getTransport("SMTPj");
		transport.connect("smtp.gmail.com", 587, username, password);
		transport.sendMessage(msg, recipientAddresses);
		transport.close();
	}

	public static void mail()
	{

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");

		props.put("mail.smtp.socketFactory.port", "465");

		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("careers@novaturetech.com","welcome@123");
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("careers@novaturetech.com"));

			message.setRecipients(Message.RecipientType.TO,

					InternetAddress.parse("kumaresh@novaturebusiness.com"));

			message.setSubject("Zoho CRM App Test Automation Report");

			BodyPart messageBodyPart=new MimeBodyPart();

			messageBodyPart.setText("Hi," + "\n\n" +

              "Please find attachment for the detailed report" + "\n\n" + "Regards" + "\n" + "Raft Team");

			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// String projectPath = System.getProperty("user.dir"); 

			String projectPath = "K:\\Appium_EDW\\test-output\\";
			// driver.get("K:\\EDW_Appium\\test-output\\" + lastModifiedFile.getName());
			messageBodyPart = new MimeBodyPart();

			String filename = projectPath + lastModifiedFile.getName();
			
			System.out.println("Mail Location :" + filename);
			
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Mail sent succesfully!");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

}
