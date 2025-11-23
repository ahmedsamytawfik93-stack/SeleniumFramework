package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends PageBase{

	public ContactUsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="FriendEmail")
	WebElement fullNameTxt;
	
	@FindBy(id="YourEmailAddress")
	WebElement emailTxt;
	
	@FindBy(id="PersonalMessage")
	WebElement enquiryTxt;
	
	@FindBy(name="send-email")
	WebElement submitBtn;
	
	@FindBy(css="div.result")
	public WebElement successMessage;
	
	public void contactUs(String fullName, String email, String message) {
		setTextElementText(fullNameTxt, fullName);
		setTextElementText(emailTxt, email);
		setTextElementText(enquiryTxt, message);
		clickButton(submitBtn);
	}
}
