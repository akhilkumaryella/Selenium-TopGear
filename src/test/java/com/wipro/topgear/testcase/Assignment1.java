package com.wipro.topgear.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wipro.topgear.client.AssignmentClient;

public class Assignment1 {

	AssignmentClient assign = new AssignmentClient();

	@BeforeMethod(alwaysRun = true)
	public void intialize() {

		assign.intializeChromeDriver();
	}

	@Test(groups="Regression")
	public void selectAllItemsTest() {

		assign.selectAllTimes();
	}

	@Test(groups="Regression")
	public void fillContactFormTest() {

		assign.fillContactForm();
	}

	@Test(groups="Regression")
	public void dragAndDropTragetTest() throws InterruptedException {

		assign.datePicker(System.getProperty("DateOfBirth"));
	}

	@Test(groups="Regression")
	public void selectMenuTest() throws InterruptedException {

		assign.selectMenu();
	}

	@Test(groups="Regression")
	public void controlGroupTest() {

		assign.controlGroup();
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		assign.closeDriver();
	}

}
