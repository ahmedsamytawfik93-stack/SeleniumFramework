package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase{
	SearchPage searchObject;
	ProductDetailsPage productDetailsObject;
	
	String productName = "Apple MacBook Pro";
	
	@Test
	public void userCanSearchForProduct() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		searchObject.productsearch(productName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(productName));
	}
}
