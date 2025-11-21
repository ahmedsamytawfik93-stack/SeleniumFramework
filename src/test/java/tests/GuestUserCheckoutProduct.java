package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;

public class GuestUserCheckoutProduct extends TestBase{
	HomePage homeObject;
	SearchPage searchObject;
	CheckoutPage checkoutObject;
	ShoppingCartPage shoppingCartObject;
	OrderDetailsPage orderDetailsObject;
	ProductDetailsPage productDetailsObject;
	UserRegistrationPage userRegistrationObject;
	
	String lastName = "Morsy";
	String firstName = "Ahmed";
	String password = "123456";
	String email = "testmail6@gmail.com";
	String productName = "Apple MacBook Pro";
	String country = "Egypt";
	String city = "Cairo";
	String address = "test address";
	String postcode = "123456";
	String phone = "321654978";
	
	// 1. Search For Product
	@Test(priority =1)
	public void userCanSearchWithAutoSuggest() {
		try {
			searchObject = new SearchPage(driver);
			productDetailsObject = new ProductDetailsPage(driver);
			
			searchObject.productsearch(productName);
			searchObject.openProductDetailsPage();
			Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
		} catch(Exception e) {
			System.out.println("Eroor occurred" + e.getMessage());
		}
	}
	
	// 2. Add To Cart
	@Test(priority =2)
	public void userCanAddProductToShoppingCart() {
		productDetailsObject = new ProductDetailsPage(driver);
		shoppingCartObject = new ShoppingCartPage(driver);
		
		productDetailsObject.addProductToCart();
		driver.navigate().to("https://demo.nopcommerce.com"+"/cart");
		
		Assert.assertTrue(shoppingCartObject.totalLbl.getText().contains("3,600"));
	}
	
	// 3. Checkout
	@Test(priority =3)
	public void userCanCheckoutProduct() {
		checkoutObject = new CheckoutPage(driver);
		
		shoppingCartObject.openCheckoutPage();
		checkoutObject.guestUserCanAddNameAndEmailToOrder(firstName, lastName, email);
		checkoutObject.userCanFillBillingAddressForm(country, city, address, postcode, phone);
		checkoutObject.userCanChooseshippingMethod();
		checkoutObject.userCanChoosePaymentMethod();
		checkoutObject.userCanConfirmPaymentInfo();
		
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		
		checkoutObject.userCanConfirmOrder();
		
		Assert.assertTrue(checkoutObject.thankYouLbl.isDisplayed());
		Assert.assertTrue(checkoutObject.successMessage.getText().contains("Your order has been successfully processed!"));
	}
	
	// 4. Show Invoice
	@Test(priority =4)
	public void userCanShowOrderDetails() {
		orderDetailsObject = new OrderDetailsPage(driver);
		orderDetailsObject.DownloadPDFInvoice();
		orderDetailsObject.PrintOrderDetails();
	}
}
