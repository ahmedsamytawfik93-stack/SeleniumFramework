package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;
import pages.WishListPage;

public class AddProductToWishListTest extends TestBase{
	HomePage homeObject;
	SearchPage searchObject;
	WishListPage wishlistObject;
	UserRegistrationPage registerObject;
	ProductDetailsPage productDetailsObject;

	
	String firstName = "Ahmed";
	String lastName = "Morsy";
	String password = "123456";
	String personalEmail = "testmail6@gmail.com";
	String productName = "Apple MacBook Pro";
	
	// 1. User Registration
	@Test(priority = 1)
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
	
	// 3. Add Product To WishList
	@Test(priority = 2)
	public void userCanAddProductToWishlist() {
		productDetailsObject = new ProductDetailsPage(driver);
		productDetailsObject.addProductToWishList();
		driver.navigate().to("https://demo.nopcommerce.com"+"/wishlist");
		wishlistObject = new WishListPage(driver);
		Assert.assertTrue(wishlistObject.WishlistHeader.isDisplayed());
		Assert.assertTrue(wishlistObject.productCell.getText().contains(productName));
	}
	
	// 4. Remove Product From WishList
	@Test(priority = 4)
	public void userCanRemoveProductFromCart() {
		wishlistObject = new WishListPage(driver);
		wishlistObject.removeProductFromCart();
		Assert.assertTrue(wishlistObject.emptyCartLbl.getText().contains("The wishlist is empty!"));
	}
}
