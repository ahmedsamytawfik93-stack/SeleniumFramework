package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndExcel extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@DataProvider(name = "ExcelData")
	public Object[][] userRegisterData() throws IOException {
		// get data from Excel Reader class
		ExcelReader er = new ExcelReader();
		return er.getExcelData();
	}
	
	@Test(priority = 1, alwaysRun = true, dataProvider = "ExcelData")
	public void userCanRegisterSuccessfully(String firstName, String lastName, String email, String password) {
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
		
		// User Logout Again
		registerObject.userLogout();
	}
}
