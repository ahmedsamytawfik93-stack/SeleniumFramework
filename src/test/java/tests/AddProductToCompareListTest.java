package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ComparePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AddProductToCompareListTest extends TestBase{
	SearchPage searchObject;
	ProductDetailsPage productDetailsObject;
	ComparePage compareObject;

	String firstProductName = "Apple MacBook Pro";
	String secondProductName = "Asus";
	
	// 1. Search For First Product And Add it To CompareList
	@Test(priority = 1)
	public void userCanSearchForProduct() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		
		searchObject.productSearch(firstProductName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(firstProductName));
		
		productDetailsObject.addProductToCompare();
	}
	
	// 2. Search For Second Product And Add it To CompareList
	@Test(priority = 2)
	public void userCanAddProductToWishlist() {
		searchObject = new SearchPage(driver);
		productDetailsObject = new ProductDetailsPage(driver);
		
		searchObject.productSearch(secondProductName);
		searchObject.openProductDetailsPage();
		Assert.assertTrue(productDetailsObject.productNameBreadCrumb.getText().contains(secondProductName));
		
		productDetailsObject.addProductToCompare();
	}
	
	// 3. Compare Between Products
	@Test(priority = 3)
	public void userCanCompareProducts() {
		compareObject = new ComparePage(driver);
		
		driver.navigate().to("https://demo.nopcommerce.com"+"/compareproducts");
		Assert.assertTrue(compareObject.firstProductName.isDisplayed());
		Assert.assertTrue(compareObject.secondProductName.isDisplayed());
		compareObject.compareProducts();
	}
	
	// 3. Compare Between Products
	@Test(priority = 4)
	public void userCanClearCompareList() {
		compareObject.clearCompareTable();
		Assert.assertTrue(compareObject.noDataLbl.getText().contains("You have no items to compare"));
	}
}
