package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase{
	SearchPage searchObject;
	ShoppingCartPage shoppingCartObject;
	ProductDetailsPage productDetailsObject;
	
	String productName = "Apple MacBook Pro";
	
	@Test(priority=1)
	public void userCanSearchWithAutoSuggest() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
			
		searchObject.productsearch(productName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
	}
	
	@Test(priority=2)
	public void userCanAddProductToShoppingCart() {
		productDetailsObject = new ProductDetailsPage(driver);
		shoppingCartObject = new ShoppingCartPage(driver);
		
		productDetailsObject.addProductToCart();
		driver.navigate().to("https://demo.nopcommerce.com"+"/cart");
		
		Assert.assertTrue(shoppingCartObject.totalLbl.getText().contains("3,600"));
	}
	
	@Test(priority=2)
	public void userCanRemoveProductFromShoppingCart() {
		shoppingCartObject = new ShoppingCartPage(driver);
		
		shoppingCartObject.removeProductFromCart();
	}
}
