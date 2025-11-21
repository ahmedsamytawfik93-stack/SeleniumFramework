package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SaerchProductUsingAutoSuggestTest extends TestBase{
	String productName = "Apple MacBook Pro";
	SearchPage searchObject;
	ProductDetailsPage productDetailsObject;
	
	@Test
	public void userCanSearchWithAutoSuggest() {
		try {
			searchObject = new SearchPage(driver);
			productDetailsObject = new ProductDetailsPage(driver);
			searchObject.productSearchUsingAutoSuggest("MacB");
			Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
		} catch(Exception e) {
			System.out.println("Error occured " + e.getMessage());
		}
	}
}
