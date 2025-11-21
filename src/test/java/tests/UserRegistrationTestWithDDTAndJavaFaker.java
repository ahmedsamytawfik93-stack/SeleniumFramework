package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndJavaFaker extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	Faker fakeData = new Faker();
	
	String firstname = fakeData.name().firstName();
	String lastname = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(8).toString();
	
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() throws  IOException {
			
		// User Register
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname, lastname, email, password);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
			
		// User Logout
		registerObject.userLogout();
			
		// User Login Again
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, password);
			
		Assert.assertTrue(registerObject.logutLink.getText().contains("Log out"));
			
		// User Logout Again
		registerObject.userLogout();
	}
}
