package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.EmailaFriendPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase {
	HomePage homeObject;
	LoginPage loginObject;
	SearchPage searchObject;
	EmailaFriendPage emailObject;
	ContactUsPage contactUsObject;
	UserRegistrationPage registerObject;
	ProductDetailsPage productDetailsObject;
	
	String firstName = "Ahmed";
	String lastName = "Morsy";
	String password = "123456";
	String personalEmail = "testmail6@gmail.com";
	String friendEmail = "test@gmail.com";
	String message = "Hello my friend, check this product"; 
	String productName = "Apple MacBook Pro";
	
	// 1.User Registration
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, personalEmail, password);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
	}
	
	// 2.Search For Product
	@Test(priority = 2)
	public void userCanSearchForProduct() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		searchObject.productsearch(productName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
	}
	
	// 3. Email To Friend
	@Test(priority = 3)
	public void RegisteredUserCanSendEmailToFriend() {
		productDetailsObject.openSendEmail();
		emailObject = new EmailaFriendPage(driver);
		emailObject.sendEmail(friendEmail, message);
		Assert.assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent."));
	}
	
	// 4. User Logout
	@Test(priority = 4)
	public void registeredUserCanLogot() {
		registerObject.userLogout();
	}
}
