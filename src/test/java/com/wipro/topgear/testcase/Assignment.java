package com.wipro.topgear.testcase;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.wipro.topgear.client.AssignmentClient;

public class Assignment {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		AssignmentClient assign = new AssignmentClient();

		assign.intializeChromeDriver();

		// Assignment - I
		assign.selectAllTimes();
		assign.fillContactForm();
		assign.dragAndDropTarget();
		assign.datePicker("12-January-1995");
		assign.selectMenu();
		assign.controlGroup();
		assign.closeDriver();

		// Assignment - II
		assign.openOlay("UK");
		assign.registerOlay("UK");
		assign.signInOlay();
		assign.signInPageValidation();
		assign.closeDriver();

		assign.openOlay("Germany");
		assign.registerOlay("Germany");
		assign.signInOlay();
		assign.closeDriver();

		assign.openOlay("Spain");
		assign.registerOlay("Spain");
		assign.signInOlay();
		assign.closeDriver();

		// Assignment - III
		assign.bookTicketMakeMyTrip();
		assign.closeDriver();
	}

}
