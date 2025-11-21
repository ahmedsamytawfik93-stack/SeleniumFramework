package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends TestBase{
	HomePage homeObject;
	ContactUsPage contactUsObject;
	
	String fullName = "Test user";
	String email = "test@gmail.com";
	String enquiry = "Hello Admin, this is for test"; 
	
	@Test
	public void userCanUseContactUs() {
		homeObject = new HomePage(driver);
		contactUsObject = new ContactUsPage(driver);
		homeObject.openContactUsPage();
		contactUsObject.contactUs(fullName, email, enquiry);
		Assert.assertTrue(contactUsObject.successMessage.getText()
				.contains("Your enquiry has been successfully sent to the store owner."));
	}
}
