package com.wipro.topgear.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Reusable components Class
 * 
 * @author Vijay
 *
 */
public class AssignmentClient {

	private String userId;
	private ChromeDriver driver;

	/**
	 * Initializing Chrome driver
	 */
	public void intializeChromeDriver() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/**
	 * Automating Selectable Page
	 */
	public void selectAllTimes() {

		driver.get("https://demoqa.com/selectable/");

		WebElement selectable = driver.findElementByXPath("//h1[@class='entry-title']");
		if (selectable.isDisplayed()) {
			System.out.println("Selectable element displayed in Page");
		} else {
			Assert.fail("Selectable element not displayed in Page");
		}

		// Selecting each item and printing each item name
		List<WebElement> items = driver.findElementsByXPath("//li[@class='ui-widget-content ui-selectee']");

		System.out.println("***** Printing Item Names *****");
		for (WebElement item : items) {
			item.click();
			// Get name of selected item
			WebElement selectedElement = driver
					.findElementByXPath("//li[@class='ui-widget-content ui-selectee ui-selected']");
			System.out.println(selectedElement.getText());
		}

	}

	/**
	 * Filling contact Form
	 */
	public void fillContactForm() {

		driver.get("https://demoqa.com/html-contact-form/");

		WebElement contactForm = driver.findElementByXPath("//h1[@class='entry-title']");
		if (contactForm.isDisplayed()) {
			System.out.println("Contact Form displayed in Page");
		} else {
			Assert.fail("Contact Form not displayed in Page");
		}

		// Fill First Name
		driver.findElementByXPath("//input[@class='firstname']").sendKeys("Vijay");
		// Fill Last Name
		driver.findElementByXPath("//input[@id='lname']").sendKeys("Galla");
		// Fill Country
		driver.findElementByXPath("//input[@name='country']").sendKeys("India");
		// Fill Subject
		driver.findElementByXPath("//textarea[@name='subject']").sendKeys("Automating Contact Form");
		// Click Submit
		driver.findElementByXPath("//input[@type='submit']").click();

		// Coming back to Contact Form Page
		driver.navigate().back();

		String openTabs = Keys.chord(Keys.CONTROL, Keys.ENTER);
		// Open 'Google Link' link in New Tab
		driver.findElementByXPath("//a[text()='Google Link']").sendKeys(openTabs);
		// Open 'Google Link is here' link in New Tab
		driver.findElementByXPath("//a[text()='Google Link is here']").sendKeys(openTabs);

		// Iterating through All windows -- Printing title of page
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			System.out.println(driver.switchTo().window(window).getTitle());
		}

	}

	/**
	 * selecting the Drag me to my target and drop on the target and verifying the
	 * text.
	 */
	public void dragAndDropTarget() {

		driver.get("https://demoqa.com/droppable/");

		WebElement droppable = driver.findElementByXPath("//h1[@class='entry-title']");
		if (droppable.isDisplayed()) {
			System.out.println("Droppable Element displayed in Page");
		} else {
			Assert.fail("Droppable Element not displayed in Page");
		}

		WebElement source = driver.findElementByXPath("//div[@id='draggable']");
		WebElement target = driver.findElementByXPath("//div[@id='droppable']");

		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();

		// Verifying Text
		String targetText = target.getText();
		Assert.assertEquals(targetText, "Dropped!", "Target Element Text:: ");

	}

	/**
	 * Selecting Date of Birth in a Calendar.
	 * 
	 * @param dateOfBirth
	 * @throws InterruptedException
	 */
	public void datePicker(String dateOfBirth) throws InterruptedException {

		driver.get("https://demoqa.com/datepicker/");
		WebElement datePicker = driver.findElementByXPath("//h1[@class='entry-title']");
		if (datePicker.isDisplayed()) {
			System.out.println("DatePicker Element displayed in Page");
		} else {
			Assert.fail("DatePicker Element not displayed in Page");
		}

		WebElement datePickerElement = driver.findElementByXPath("//input[@id='datepicker']");
		datePickerElement.click();

		WebElement previous = driver.findElementByXPath("//a[@class='ui-datepicker-prev ui-corner-all']");

		String date = dateOfBirth.split("-")[0];
		String month = dateOfBirth.split("-")[1];
		String year = dateOfBirth.split("-")[2];

		WebElement monthYearElement = driver.findElementByXPath("//div[@class='ui-datepicker-title']");

		while (!(month + " " + year).equalsIgnoreCase(monthYearElement.getText())) {
			System.out.println("Akhil");
			previous.click();
			Thread.sleep(1000);
		}

		WebElement dateElement = driver.findElementByXPath("//a[@class='ui-state-default'][text() = '" + date + "']");
		dateElement.click();

		Thread.sleep(5000);
	}

	public void datePickerSend(String dob) {
		
		driver.get("https://demoqa.com/datepicker/");
		WebElement datePicker = driver.findElementByXPath("//h1[@class='entry-title']");
		if (datePicker.isDisplayed()) {
			System.out.println("DatePicker Element displayed in Page");
		} else {
			Assert.fail("DatePicker Element not displayed in Page");
		}

		WebElement datePickerElement = driver.findElementByXPath("//input[@id='datepicker']");
		datePickerElement.sendKeys(dob);
		datePickerElement.sendKeys(Keys.ENTER);
		
		System.out.println("Date of Birth Entered");
		
	}
	/**
	 * selecting the All the menu options one by one
	 * 
	 * @throws InterruptedException
	 */
	public void selectMenu() throws InterruptedException {

		driver.get("https://demoqa.com/selectmenu/");

		WebElement selectMenu = driver.findElementByXPath("//h1[@class='entry-title']");
		if (selectMenu.isDisplayed()) {
			System.out.println("Selectable Menu Element displayed in Page");
		} else {
			Assert.fail("Selectable Menu Element not displayed in Page");
		}

		Thread.sleep(3000);

		// Selecting first element in Speed using index
		Select speed = new Select(driver.findElementByXPath("//select[@name='speed']"));
		speed.selectByIndex(4);

		// Selecting ui.jQuery.js element in Speed using Text
		Select file = new Select(driver.findElementByXPath("//select[@name='files']"));
		file.selectByVisibleText("ui.jQuery.js");

		// Selecting 5 element in Number using index
		Select number = new Select(driver.findElementByXPath("//select[@name='number']"));
		number.selectByIndex(5);

		// Selecting Dr. element in Speed using Text
		Select name = new Select(driver.findElementByXPath("//select[@name='salutation']"));
		name.selectByVisibleText("Dr.");

	}

	/**
	 * Automating Rental Car Block.
	 */
	public void controlGroup() {

		driver.get("https://demoqa.com/controlgroup/");

		// Select Car size from drop down - Horizontal
		Select carSize = new Select(driver.findElementByXPath("//select[@id='car-type']"));
		carSize.selectByIndex(4);

		// clicking standard - Horizontal
		WebElement standard = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][1]");
		standard.click();

		// clicking automatic - Horizontal
		WebElement automatic = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][2]");
		automatic.click();

		// clicking insurance - Horizontal
		WebElement insurance = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][3]");
		insurance.click();

		// enter number of cars - Horizontal
		WebElement cars = driver.findElementByXPath("//input[@id='horizontal-spinner']");
		cars.sendKeys("5");

		// clicking Book now - Horizontal
		WebElement bookNow = driver
				.findElementByXPath("//button[@class='ui-widget ui-controlgroup-item ui-button ui-corner-right']");
		bookNow.click();

		// Select Car size from drop down - vertical
		Select carSizeV = new Select(driver.findElementByXPath("//select[@id='ui-id-8']"));
		carSizeV.selectByIndex(4);

		// clicking standard - vertical
		WebElement standardV = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][4]");
		standardV.click();

		// clicking automatic - vertical
		WebElement automaticV = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][5]");
		automaticV.click();

		// clicking insurance - vertical
		WebElement insuranceV = driver.findElementByXPath(
				"//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank'][6]");
		insuranceV.click();

		// enter number of cars - vertical
		WebElement carsV = driver.findElementByXPath("//input[@id='vertical-spinner']");
		carsV.sendKeys("5");

		// clicking Book now - vertical
		WebElement bookNowV = driver.findElementByXPath("//button[@id='book']");
		bookNowV.click();

	}

	/**
	 * Opening Olay URL with different Countries
	 * 
	 * @param country
	 */
	public void openOlay(String country) {

		System.out.println("***** Opening Olay URL *****");
		userId = generateDynamicEmail();
		if ("UK".equalsIgnoreCase(country)) {

			driver.get("https://www.olay.co.uk/en-gb");
		} else if ("Germany".equalsIgnoreCase(country)) {

			driver.get("https://www.olaz.de/de-de");
		} else if ("Spain".equalsIgnoreCase(country)) {

			driver.get("https://www.olay.es/es-es");
		}
	}

	/**
	 * Olay Registration Page
	 * 
	 * @param country
	 * @throws IOException
	 * @throws ParseException
	 */
	public void registerOlay(String country) throws IOException, ParseException {

		System.out.println("***** Register User *****");

		// Register user
		WebElement register = driver.findElementByXPath("//a[@class='event_profile_register']");
		register.click();

		// fill Email
		WebElement email = driver.findElementByXPath("//input[@data-key='emailAddress']");
		email.sendKeys(userId);

		// fill password
		WebElement password = driver.findElementByXPath("//input[@data-key='newPassword']");
		password.sendKeys("olay@123");

		// fill confirm password
		WebElement conirmPassword = driver
				.findElementByXPath("//input[@id='phdesktopbody_0_grs_account[password][confirm]']");
		conirmPassword.sendKeys("olay@123");

		// fill date of birth
		Select date = new Select(driver.findElementByXPath("//select[@data-key='birthdate[dateselect_day]']"));
		date.selectByValue("19");

		// fill Month
		Select month = new Select(driver.findElementByXPath("//select[@data-key='birthdate[dateselect_month]']"));
		month.selectByValue("08");

		// fill year
		Select year = new Select(driver.findElementByXPath("//select[@data-key='birthdate[dateselect_year]']"));
		year.selectByValue("1995");

		if ("Germany".equalsIgnoreCase(country) || "Spain".equalsIgnoreCase(country)) {

			WebElement male = driver.findElementByXPath("//img[@id='phdesktopbody_0_imgmale']");
			male.click();

			Map<String, String> hmap;

			if ("Germany".equalsIgnoreCase(country)) {
				hmap = getDetailsFromExcel();

				Select countrySelect = new Select(driver.findElementByXPath("//select[@data-key='addressCountry']"));
				countrySelect.selectByValue(hmap.get("Country"));

				WebElement address = driver.findElementByXPath("//input[@data-key='addressStreet1']");
				address.sendKeys(hmap.get("Address"));

				WebElement postalCode = driver.findElementByXPath("//input[@data-key='addressPostalCode']");
				postalCode.sendKeys(hmap.get("PostalCode"));

				WebElement location = driver.findElementByXPath("//input[@data-key='addressCity']");
				location.sendKeys(hmap.get("Location"));
			} else {
				hmap = getDetailsFromJson();
			}

			WebElement firstName = driver.findElementByXPath("//input[@data-key='firstName']");
			firstName.sendKeys(hmap.get("FirstName"));

			WebElement surName = driver.findElementByXPath("//input[@data-key='lastName']");
			surName.sendKeys(hmap.get("LastName"));
		}

		// Register Profile
		WebElement submit = driver.findElementByXPath("//input[@type='submit']");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submit);

		if ("UK".equalsIgnoreCase(country)) {
			signOutOlay();
		}

		System.out.println("***** Registration Completed *****");

	}

	/**
	 * Olay Sign in Page
	 */
	public void signInOlay() {

		System.out.println("***** SignIn to Olay using User Credentials ******");
		// sign in
		WebElement signIn = driver.findElementByXPath("//a[@class='event_profile_login']");
		signIn.click();

		// Sign-In Email Address
		WebElement emailAddress = driver.findElementByXPath("//input[@data-key='signInEmailAddress']");
		emailAddress.sendKeys(userId);

		// Sign-In Password
		WebElement password = driver.findElementByXPath("//input[@data-key='currentPassword']");
		password.sendKeys("olay@123");

		// Sign-In Submit Profile
		WebElement submit = driver.findElementByXPath("//input[@type='submit']");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submit);

		// Sign-In Email Validation
		String signEmail = driver.findElementByXPath("//div[@class='pc_txtfld pc_Email']/span[2]").getText();
		Assert.assertEquals(signEmail, userId, "SignIn emails mismatching");

		signOutOlay();

		System.out.println("***** SignIn to Olay Completed ******");
	}

	/**
	 * Olay Sign in Page validations with invalid Password
	 */
	public void signInPageValidation() {
		
		// sign in
		WebElement signIn = driver.findElementByXPath("//a[@class='event_profile_login']");
		signIn.click();

		WebElement emailAddress = driver.findElementByXPath("//input[@data-key='signInEmailAddress']");
		WebElement password = driver.findElementByXPath("//input[@data-key='currentPassword']");

		// Sign-In Submit Profile
		WebElement submit = driver.findElementByXPath("//input[@type='submit']");
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		// Invalid Password
		emailAddress.sendKeys(userId);
		password.sendKeys("olay54@123");
		executor.executeScript("arguments[0].click();", submit);

		// Error Message validation
		WebElement errorMessage = driver.findElementByXPath("//span[@class='pc_error-message2']");
		Assert.assertTrue(errorMessage.isDisplayed(), "Error Message is not coming");

	}

	/**
	 * Signing out from Olay Sign in page
	 */
	public void signOutOlay() {

		// Sign out
		WebElement signOut = driver.findElementByXPath("//a[@class='logoutbtn']");
		signOut.click();

		// Confirm Logout
		WebElement confirmLogOut = driver.findElementByXPath("//a[@class='continue-btn btn event_profile_logout']");
		confirmLogOut.click();
	}

	/**
	 * Generating dynamic user id
	 * 
	 * @return
	 */
	public String generateDynamicEmail() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddssmmSSS");

		return "Olay" + sdf.format(date) + "@olay123.com";

	}

	/**
	 * Reading data from Excel file
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> getDetailsFromExcel() throws IOException {

		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Olay.xlsx");
		FileInputStream fis = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		Row row = sheet.getRow(1);

		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put("FirstName", row.getCell(0).getStringCellValue());
		hmap.put("LastName", row.getCell(1).getStringCellValue());
		hmap.put("Country", row.getCell(2).getStringCellValue());
		hmap.put("Address", row.getCell(3).getStringCellValue());
		String postalCode = String.valueOf(row.getCell(4).getNumericCellValue());
		hmap.put("PostalCode", postalCode.substring(0, postalCode.length() - 2));
		hmap.put("Location", row.getCell(5).getStringCellValue());

		return hmap;
	}

	/**
	 * Reading Data from Json File
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public Map<String, String> getDetailsFromJson() throws FileNotFoundException, IOException, ParseException {

		JSONParser json = new JSONParser();
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/Olay.json");
		Object obj = json.parse(new FileReader(file));

		JSONObject jsonObj = (JSONObject) obj;

		Map<String, String> map = new HashMap<String, String>();
		map.put("FirstName", jsonObj.get("FirstName").toString());
		map.put("LastName", jsonObj.get("LastName").toString());

		return map;

	}

	/**
	 * MakeMyTrip Booking
	 */
	public void bookTicketMakeMyTrip() {

		driver.get("https://www.makemytrip.com/");

		System.out.println("***** MakeMyTrip Booking *****");

		// click Round Trip
		WebElement oneWayTrip = driver.findElementByXPath("//li[@data-cy='oneWayTrip']");
		oneWayTrip.click();

		// Select From City
		WebElement fromCity = driver.findElementByXPath("//input[@id='fromCity']");
		fromCity.click();
		WebElement fromCityTxt = driver.findElementByXPath(
				"//input[@class='react-autosuggest__input react-autosuggest__input--open react-autosuggest__input--focused']");
		fromCityTxt.sendKeys(System.getProperty("fromcity"));
		WebElement fromSuggest = driver.findElementByXPath("//li[@id='react-autowhatever-1-section-0-item-0']");
		fromSuggest.click();

		// Select To city
		WebElement toCityTxt = driver.findElementByXPath(
				"//input[@class='react-autosuggest__input react-autosuggest__input--open react-autosuggest__input--focused']");
		toCityTxt.sendKeys(System.getProperty("toCity"));
		WebElement toSuggest = driver.findElementByXPath("//li[@id='react-autowhatever-1-section-0-item-0']");
		toSuggest.click();

		// Departure date
		driver.findElementByXPath("//span[@class='lbl_input latoBold appendBottom10']").click();

		int size = driver
				.findElementsByXPath(
						"//div[@class='DayPicker-Month'][1]/div[@class='DayPicker-Body']/div[@class='DayPicker-Week']")
				.size();

		for (int i = 1; i <= size; i++) {

			String date = "Apr 18 2020";

			try {
				WebElement departuredate = driver.findElementByXPath(
						"//div[@class='DayPicker-Month'][1]/div[@class='DayPicker-Body']/div[@class='DayPicker-Week']["
								+ i + "]/div[contains(@aria-label, '" + date + "')]");
				departuredate.click();
			} catch (NoSuchElementException e) {

			}
		}

		WebElement search = driver.findElementByXPath("//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']");
		search.click();

		// selecting cheap price
		WebElement fare = driver.findElementByXPath(
				"//div[@class='fli-list one-way'][1]/div/div/div/div/div/div[@class='pull-left make_relative']/button");
		fare.click();

		// Book Now
		WebElement bookNow = driver
				.findElementByXPath("//div[@class='viewFaresOuter collapse in']/div[1]/div[2]/div[2]/button");
		bookNow.click();

		// Goto Review Page
		Set<String> windows = driver.getWindowHandles();

		for (String w : windows) {

			driver.switchTo().window(w);
			if (driver.getCurrentUrl().contains("review")) {
				WebElement review = driver.findElementByXPath("//h4[@class='font22 latoBold whiteText headerTitle']");
				Assert.assertTrue(review.getText().equals("Review your booking"));
			}
		}

	}

	public void closeDriver() {

		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window).close();
		}
	}

}
