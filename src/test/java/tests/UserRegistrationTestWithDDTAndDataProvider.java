package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndDataProvider extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@DataProvider(name = "testData")
	public static Object[][] userData() {
		return new Object[][] {
			{"Ahmed" , "Morsy" , "test@test.com" , "123456"},
			{"Mohamed","Ali","asd@gmail.com","365421"}};
	}
	
	@Test(priority = 1, alwaysRun = true, dataProvider = "testData")
	public void userCanRegisterSuccessfully(String firstName, String lastName, String email, String password) {
		// User Register
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
		
		// User Logout
		registerObject.userLogout();
		
		// User Login Again
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, password);
		
		Assert.assertTrue(registerObject.logutLink.getText().contains("Log out"));
	}
}
