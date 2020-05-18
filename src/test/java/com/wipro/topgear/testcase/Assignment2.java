package com.wipro.topgear.testcase;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wipro.topgear.client.AssignmentClient;

public class Assignment2 {

	AssignmentClient assign = new AssignmentClient();

	@BeforeMethod(alwaysRun = true)
	public void intialize() {

		assign.intializeChromeDriver();
	}

	@Test(dataProvider = "CountryList", groups = "Regression")
	public void olayRegistrationTest(String country) throws IOException, ParseException {

		assign.openOlay(country);
		assign.registerOlay(country);
		assign.signInOlay();
	}
	
	@Test(dataProvider = "CountryList", groups = "Regression")
	public void signInPageValidationTest(String country) {
		
		assign.openOlay(country);
		assign.signInPageValidation();
	}

	@DataProvider(name = "CountryList")
	public Object[] getCountries() {

		return new Object[] {"UK", "Germany", "Spain"};
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		assign.closeDriver();
	}
}
