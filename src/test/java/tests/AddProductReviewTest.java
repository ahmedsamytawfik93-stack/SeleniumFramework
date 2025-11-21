package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.EmailaFriendPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class AddProductReviewTest extends TestBase{
	HomePage homeObject;
	LoginPage loginObject;
	SearchPage searchObject;
	EmailaFriendPage emailObject;
	ContactUsPage contactUsObject;
	UserRegistrationPage registerObject;
	ProductReviewPage productReviewObject;
	ProductDetailsPage productDetailsObject;
	
	String firstName = "Ahmed";
	String lastName = "Morsy";
	String password = "123456";
	String personalEmail = "testmail6@gmail.com";
	String friendEmail = "test@gmail.com";
	String message = "Hello my friend, check this product"; 
	String productName = "Apple MacBook Pro";
	String reviewTilte = "New Review";
	String reviewMessage = "The product is very good";
	
	// 1. User Registration
	@Test(priority = 1, alwaysRun = true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();

		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, personalEmail, password);
		
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
	}
	
	// 2. Search For Product
	@Test(priority = 2)
	public void userCanSearchForProduct() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		searchObject.productsearch(productName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
	}
	
	// 3. Add Product Review
	@Test(priority = 3)
	public void registeredUserCanAddReview() {
		productDetailsObject.openReviewForm();
		productReviewObject = new ProductReviewPage(driver);
		productReviewObject.addProductReview(reviewTilte, reviewMessage);
		Assert.assertTrue(productReviewObject.reviewNotification.getText().contains("Product review is successfully added"));
	}
	
	// 4. User Logout
	@Test(priority = 4)
	public void registeredUserCanLogot() {
		registerObject.userLogout();
	}
}
