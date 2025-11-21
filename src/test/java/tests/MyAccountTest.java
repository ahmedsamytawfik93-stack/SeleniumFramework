package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	MyAccountPage myAccountObject;
	String oldPassword = "12345678";
	String newPassword = "123456";
	String firstName = "Ahmed";
	String lastName = "Morsy";
	String email = "testmail6@gmail.com";
			
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, oldPassword);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
	}
	
	@Test(dependsOnMethods = {"userCanRegisterSuccessfully"})
	public void registeredUserCanChangePassword() {
		myAccountObject = new MyAccountPage(driver);
		registerObject.openMyAccountPage();
		myAccountObject.openChangePasswordPage();
		myAccountObject.changePassword(oldPassword, newPassword);
		Assert.assertTrue(myAccountObject.resultLbl.getText().contains("Password was changed"));
	}
	
	@Test(dependsOnMethods = {"registeredUserCanChangePassword"})
	public void registeredUserCanLogot() {
		registerObject.userLogout();
	}
	
	@Test(dependsOnMethods = {"registeredUserCanLogot"})
	public void registeredUserCanLogin() {
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.userLogin(email, newPassword);
		
		Assert.assertTrue(registerObject.logutLink.getText().contains("Log out"));
	}
}
