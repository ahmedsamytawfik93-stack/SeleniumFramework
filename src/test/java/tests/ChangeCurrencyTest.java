package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase{
	HomePage homeObject;
	SearchPage searchObject;
	ProductDetailsPage productDetailsObject;
	
	String productName = "Apple MacBook Pro";
	
	@Test(priority=1)
	public void UserCanChangeCurrency() {
		homeObject = new HomePage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		
		homeObject.changeCurrency();
	}
	
	@Test(priority=2)
	public void userCanSearchWithAutoSuggest() {
		try {
			searchObject = new SearchPage(driver);
			productDetailsObject = new ProductDetailsPage(driver);
			searchObject.productSearchUsingAutoSuggest("MacB");
			Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
			Assert.assertTrue(productDetailsObject.productPriceLbl.getText().contains("€"));
		} catch(Exception e) {
			System.out.println("Error occured " + e.getMessage());
		}
	}
}
