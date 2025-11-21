package tests;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import data.JsonDataReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndJSON extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() throws IOException, ParseException {
		// Read Data from JSON file
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.jsonReader();
		
		// User Register
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(jsonReader.firstname, jsonReader.lastname, jsonReader.email, jsonReader.password);
			
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
			
		// User Logout
		registerObject.userLogout();
			
		// User Login Again
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(jsonReader.email, jsonReader.password);
			
		Assert.assertTrue(registerObject.logutLink.getText().contains("Log out"));
			
		// User Logout Again
		registerObject.userLogout();
	}
}
