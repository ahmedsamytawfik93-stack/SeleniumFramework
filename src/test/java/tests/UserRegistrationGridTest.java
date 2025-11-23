package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationGridTest extends TestBaseGrid{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(getDriver());
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(getDriver());
		registerObject.userRegistration("Ahmed", "Morsy", "testmail6@gmail.com", "12345678");
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
	}
	
	@Test(dependsOnMethods = {"userCanRegisterSuccessfully"})
	public void registeredUserCanLogout() {
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods = {"registeredUserCanLogout"})
	public void registeredUserCanLogin() {
		homeObject.openLoginPage();
		loginObject = new LoginPage(getDriver());
		loginObject.userLogin("testmail6@gmail.com", "12345678");
		
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
}
