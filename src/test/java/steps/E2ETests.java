package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import tests.TestBase;

public class E2ETests extends TestBase{
	HomePage homeObject;
	SearchPage searchObject;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartObject;
	CheckoutPage checkoutObject;
	OrderDetailsPage orderObject;
	String productName = "Apple MacBook Pro";
	
	@Given("The user in the home page")
	public void the_user_in_the_home_page() {
		homeObject = new HomePage(driver);
		Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}
	@When("he searches for {string}")
	public void he_searches_for(String string) {
		searchObject = new SearchPage(driver);
		searchObject.productSearchUsingAutoSuggest(productName);
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNameBreadCrumb.getText().contains(productName));
	}
	@When("choose to buy items")
	public void choose_to_buy_items() {
		cartObject = new ShoppingCartPage(driver);
		productDetails.addProductToCart();
		driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}
	@When("moves to checkout cart and enter personal details on checkout page and place the order")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() {
		checkoutObject = new CheckoutPage(driver);
		cartObject.openCheckoutPage();
		checkoutObject.guestUserCanAddNameAndEmailToOrder("test", "user", "ahmed22222@test.com");
		checkoutObject.userCanFillBillingAddressForm("Egypt", "Cairo", "test address", "123456", "32445566677");
		checkoutObject.userCanChooseshippingMethod();
		checkoutObject.userCanChoosePaymentMethod();
		checkoutObject.userCanConfirmPaymentInfo();
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.userCanConfirmOrder();
		Assert.assertTrue(checkoutObject.thankYouLbl.isDisplayed());
	}
	@Then("he can view the order and download the invoice")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
		orderObject = new OrderDetailsPage(driver);
		checkoutObject.userCanShowConfirmedOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject.DownloadPDFInvoice();
		Thread.sleep(3000);
		orderObject.PrintOrderDetails();
	}
}
