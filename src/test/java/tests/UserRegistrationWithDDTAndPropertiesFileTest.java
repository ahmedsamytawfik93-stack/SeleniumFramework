package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTAndPropertiesFileTest extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String firstname = LoadProperties.userData.getProperty("firstname");
	String lastname = LoadProperties.userData.getProperty("lastname");
	String email = LoadProperties.userData.getProperty("email");
	String password = LoadProperties.userData.getProperty("password");
	
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname, lastname, email, password);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
	}
	
	@Test(dependsOnMethods = {"userCanRegisterSuccessfully"})
	public void registeredUserCanLogout() {
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods = {"registeredUserCanLogout"})
	public void registeredUserCanLogin() {
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, password);
		
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
