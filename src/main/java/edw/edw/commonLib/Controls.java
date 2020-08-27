package edw.edw.commonLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import edw.edw.frameLib.Driver;
import edw.edw.frameLib.Log;

public class Controls extends Driver {
	public WebDriver driver;
	public WebDriverWait wait;
	Actions builder;
	int databaseValues;

	public Controls(WebDriver driver) // create class with parameter
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, time);
	}
	
	// result of database query
	public String[] results(String query) {
		String[] data = new String[10];
		try {

			resultSet = stmt.executeQuery(query);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columns = rsmd.getColumnCount();
			resultSet.last();
			int count = resultSet.getRow();
			resultSet.beforeFirst();
			databaseValues = count * columns;
			data[0] = String.valueOf(count);
			int rowUpto = 0;
			while (resultSet.next()) {

				for (int i = 1; i <= columns; i++) {
					rowUpto++;
					data[rowUpto] = resultSet.getString(i);

				}

			}
			return data;
		} catch (SQLException e) {
			Log.error("Exception comes");
			return null;
		}
	}

	public String[] splitaArrayByComma(String value) {
		return value.split(",");
	}

	// click on element
	public void click(String fileName, String key) {

		wait.until(ExpectedConditions.elementToBeClickable(getElement(fileName, key)));
	}

	public void actionsClick(String fileName, String key) {
		// Configure the Action
		Actions action = new Actions(driver);

		// To click on the element
		action.moveToElement(getElement(fileName, key)).click().build().perform();

	}

	public void javaScriptClick(String fileName, String key) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(locatorIdentification(fileName, key)));
	}

	// clear an element
	public void clear(By identifier) {
		getElement(identifier).clear();
	}
	
	public void clear(String fileName, String key) {
		getElement(locatorIdentification(fileName, key)).clear();
	}

	// wait until element is visible
	public void waitUntilVisibilty(String fileName, String key) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorIdentification(fileName, key)));
	}

	// wait until element is to be clickable
	public void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// wait until element is to be clickable
	public void waitUntilClickable(String fileName, String key) {
		wait.until(ExpectedConditions.elementToBeClickable(locatorIdentification(fileName, key)));
	}

	// wait until staleness of element
	public void waitUntilStaleness(WebElement element) {
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	public void waitUntilInvisibility(String fileName, String key) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locatorIdentification(fileName, key)));
	}

	// when element is displayed as expected true
	public boolean isDisplayedExceptedTrue(String fileName, String key) {
		try {
			return getElement(fileName, key).isDisplayed();
		} catch (Exception e) {
			Log.error("Element specified by " + key + " is not visible.");
			return false;
		}
	}

	public boolean isDisplayed(String fileName, String key) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated((locatorIdentification(fileName, key)))).isDisplayed();
		} catch (Exception e) {
			Log.error("Element specified by " + key + " is not visible.");
			return false;
		}
	}
	

	public void waitUntilVisibilityForAllElements(String fileName, String key) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locatorIdentification(fileName, key)));
	}

	// when element is displayed as expected false
	public boolean isDisplayedExceptedFalse(String fileName, String key) {
		try {
			return getElementUnvisible(locatorIdentification(fileName, key)).isDisplayed();
		} catch (Exception e) {
			return true;
		}
	}

	// when element is displayed as expected false
	public boolean isDisplayedExceptedFalse(By identifier) {
		try {
			return getElementUnvisible(identifier).isDisplayed();
		} catch (Exception e) {
			return true;
		}
	}

	
	public boolean isEnabled(String fileName, String key)
	{
		try {
			return driver.findElement(locatorIdentification(fileName, key)).isEnabled();
		} catch (Exception e) {
			Log.error("Element specified by " + key + " is not enabled.");
			return false;
		}
	}
	// when element is enabled as expected true
	public boolean isEnabledExceptedFalse(String fileName, String key) {
		try {
			return getElement(locatorIdentification(fileName, key)).isEnabled();
		} catch (Exception e) {
			return true;
		}
	}

	// return element is enabled or not as expected false
	public boolean isEnabledExceptedTrue(String fileName, String key) {
		try {
			return getElement(locatorIdentification(fileName, key)).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	// verify value is selected
	public boolean isSelectedExceptedTrue(String fileName, String key) {
		try {
			return getElement(locatorIdentification(fileName, key)).isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	// verify value is not selected
	public boolean isSelectedExceptedFalse(String fileName, String key) {
		try {
			return getElement(locatorIdentification(fileName, key)).isSelected();
		} catch (Exception e) {
			return true;
		}
	}

	// get by drop down value
	public void selectDropDownByValue(String fileName, String key, String value) {
		Select dropdown = new Select(getElement(locatorIdentification(fileName, key)));
		dropdown.selectByValue(value);

	}

	// get drop down by text
	public void selectDropDownByText(String fileName, String key, String text) {
		Select dropdown = new Select(driver.findElement(locatorIdentification(fileName, key)));
		dropdown.selectByVisibleText(text);
	}

	// get drop down by text on wait
	public void selectDropDownWaitByText(String fileName, String key, String text) {
		Select dropdown = new Select(getElement(locatorIdentification(fileName, key)));

		dropdown.selectByVisibleText(text);
	}

	// get dropdown by index
	public void selectDropDownByIndex(String fileName, String key, int index) {
		Select dropdown = new Select(getElement(locatorIdentification(fileName, key)));
		dropdown.selectByIndex(index);
	}

	// get count of drop down
	public int getDropDownCount(String fileName, String key) {
		Select dropdown = new Select(getElement(locatorIdentification(fileName, key)));
		List<WebElement> list = dropdown.getOptions();
		return list.size() - 1;
	}

	public void type(String fileName, String key, String testData) {
		getElement(fileName, key).clear();
		getElement(fileName, key).sendKeys(testData);
	}

	public void typeWithoutVisibility(String fileName, String key) {
		// {
		WebElement textBox = driver.findElement(locatorIdentification(fileName, key));
		textBox.sendKeys("value");
	}

	// return text
	public String getText(String fileName, String key) {
		return getElement(locatorIdentification(fileName, key)).getText();
	}

	public String getTextWithoutVisibility(String fileName, String key) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver.findElement(locatorIdentification(fileName, key)).getText();
	}

	// return attribute value
	public String getAttribute(String fileName, String key, String attribute) {
		return getElement(locatorIdentification(fileName, key)).getAttribute(attribute);
	}

	// return css value
	public String getCssValue(String fileName, String key, String cssValue) {
		return getElement(locatorIdentification(fileName, key)).getCssValue(cssValue);
	}

	// return web element
	public WebElement getElement(By identifier) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));

	}

	public By locator(String locatorType, String identifier) {
		if (locatorType.toUpperCase().contains("CSS")) {
			return By.cssSelector(identifier);
		} else if (locatorType.toUpperCase().contains("XPATH")) {
			return By.xpath(identifier);
		}

		else if (locatorType.toUpperCase().contains("ID")) {
			return By.id(identifier);
		} else if (locatorType.toUpperCase().contains("NAME")) {
			return By.name(identifier);
		} else if (locatorType.toUpperCase().contains("TAG")) {
			return By.tagName(identifier);
		} else if (locatorType.toUpperCase().contains("LTEXT")) {
			return By.linkText(identifier);
		} else if (locatorType.toUpperCase().contains("PLTEXT")) {
			return By.partialLinkText(identifier);
		} else {
			return By.className(identifier);
		}

	}

	public WebElement getElement(String fileName, String key) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locatorIdentification(fileName, key)));

	}

	public WebElement getElementUnvisible(By identifier) {
		return driver.findElement(identifier);
	}

	// get pageTitle
	public String getPageTitle() {
		return driver.getTitle();
	}

	public String readProperty(String propertyFileName, String value) {
		try {
			Properties obj = new Properties();
			FileInputStream objfile = new FileInputStream(
					System.getProperty("user.dir") + "\\objectRepositry\\" + propertyFileName + ".properties");

			obj.load(objfile);
			return obj.getProperty(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";

		}
	}

	public By locatorIdentification(String fileName, String key) {
		return locator(key, readProperty(fileName, key));
	}

	public int numberOfElementsSimiliarIdentifier(String fileName, String key) {
		try {
			List<WebElement> elements = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locatorIdentification(fileName, key)));
			return elements.size();
		} catch (Exception e) {
			return 0;
		}
	}

	public void connectToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}
	
	public void switchToFrame(String fileName, String key) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locatorIdentification(fileName, key)));
	}

	public List<String> textOfElements(String fileName, String key, String furtherElementKey) {
		List<String> values = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(locatorIdentification(fileName, key));
		for (WebElement e : elements) {

			values.add(e.findElement(locatorIdentification(fileName, furtherElementKey)).getText());
		}

		return values;
	}

	public void clickOfElements(String fileName, String key, String furtherElementKey) {
		List<WebElement> elements = driver.findElements(locatorIdentification(fileName, key));
		for (WebElement e : elements) {

			WebElement e1 = e.findElement(locatorIdentification(fileName, furtherElementKey));
			clickByJavaScript(e1);
			Log.info("Clicked on element in list");
		}

	}

	public void clickOfElementOnTextBasis(String fileName, String key, String furtherElementKey, String text) {
		List<WebElement> elements = driver.findElements(locatorIdentification(fileName, key));
		for (WebElement e : elements) {

			WebElement e1 = e.findElement(locatorIdentification(fileName, furtherElementKey));
			if (e1.getText().equalsIgnoreCase(text))
				clickByJavaScript(e1);
			Log.info("Clicked on element in list");
		}

	}

	public void connectToLatestWindow() {

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

	public String getParentWindow() {
		return driver.getWindowHandle();
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void navigateToUrl(String url) {
		driver.get(url);
	}

	public void clickByJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickForSimilarList(String fileName, String key, String value) {
		List<WebElement> listOfElements = displayedElements(fileName, key);
		for (WebElement element : listOfElements) {
			if (element.getText().equalsIgnoreCase(value)) {

				clickByJavaScript(element);

			}
		}
	}

	public void clickForSimilarCheckBoxes(String fileName, String key) {
		List<WebElement> listOfElements = displayedElements(fileName, key);
		for (WebElement element : listOfElements) {
			clickByJavaScript(element);
			Log.info("Checked the required field");

		}
	}

	public void handleCalender(By locatorMainButton, By previous, By next, By mid, By months, By days, String dateTime)
			throws InterruptedException {
		WebElement calender = getElement(locatorMainButton);
		calender.click();

		// getElement(locatorMainButton).click();

		WebElement midLink = getElement(mid);
		// button to move previous month in calendar

		// Split the date time to get only the date part

		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

		// get the year difference between current year and year to set in
		// calander

		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);

		midLink.click();

		if (yearDiff != 0) {

			Thread.sleep(1000);

			// if you have to move next year

			if (yearDiff > 0) {

				for (int i = 0; i < yearDiff; i++) {

					getElement(next).click();

				}

			}

			// if you have to move previous year

			else if (yearDiff < 0) {

				for (int i = 0; i < (yearDiff * (-1)); i++) {

					getElement(previous).click();

				}

			}

		}

		// midLink.click();

		Thread.sleep(1000);

		// Get all months from calendar to select correct one

		List<WebElement> list_AllMonthToBook = driver.findElements(months);

		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();

		Thread.sleep(1000);

		// get all dates from calendar to select correct one

		List<WebElement> list_AllDateToBook = driver.findElements(days);

		for (int date = Integer.parseInt(date_dd_MM_yyyy[0]) - 1; date < 42; date++) {

			if ((((list_AllDateToBook.get(date)).getText()).equals(date_dd_MM_yyyy[0]))) {

				list_AllDateToBook.get(date).click();
				break;

			}
		}

	}

	public void uploadFile(String fileName, String key, String fileToUpload) {

		String filePath = System.getProperty("user.dir") + "\\testData\\uploadFiles\\" + fileToUpload;
		driver.findElement(locatorIdentification(fileName, key)).sendKeys(filePath);
	}

	public List<WebElement> displayedElements(String fileName, String key) {
		return driver.findElements(locatorIdentification(fileName, key));

	}

	public void acceptAlert() {

		// Get a handle to the open alert, prompt or confirmation
		Alert alert = driver.switchTo().alert();

		// Will Click on OK button.
		alert.accept();

	}

	public void rejectAlert() {

		Alert alert = driver.switchTo().alert();

		// Will Click on OK button.
		alert.dismiss();
	}

	public String getTextFromAlert() {

		Alert alert = driver.switchTo().alert();

		// will get the text which is present on th Alert.
		return alert.getText();
	}

	public void typeInAlert(String testData) {

		Alert alert = driver.switchTo().alert();

		// Will pass the text to the prompt popup
		alert.sendKeys(testData);
	}

	public String onlyDigits(String input) {

		return input.replaceAll("\\D+", "");
	}

}