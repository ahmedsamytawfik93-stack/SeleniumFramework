package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

public class ProductHoverMenuTest extends TestBase{
	HomePage homeObject;
	
	@Test
	public void userCanSelectSubCategoryFromMainMenu() {
		homeObject = new HomePage(driver);
		homeObject.selectNotebooksMenu();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null");
		Assert.assertTrue(currentUrl.contains("notebooks"));
	}
}
