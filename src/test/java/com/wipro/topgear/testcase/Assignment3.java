package com.wipro.topgear.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wipro.topgear.client.AssignmentClient;

public class Assignment3 {

	AssignmentClient assign = new AssignmentClient();

	@BeforeMethod
	public void intialize() {

		assign.intializeChromeDriver();
	}
	
	@Test(groups="Regression")
	public void makeMyTripTest() {
		
		assign.bookTicketMakeMyTrip();
	}
	
	@AfterMethod
	public void closeDriver() {
		assign.closeDriver();
	}
}
