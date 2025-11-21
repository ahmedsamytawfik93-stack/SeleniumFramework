package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailaFriendPage extends PageBase{

	public EmailaFriendPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="FriendEmail")
	WebElement friendEmailTxtBox;
	
	@FindBy(id="PersonalMessage")
	WebElement personalMessageTxtBox;
	
	@FindBy(name="send-email")
	WebElement sendEmailBtn;
	
	@FindBy(css="div.result")
	public WebElement messageNotification;
	
	public void sendEmail(String friendEmail, String message) {
		setTextElementText(friendEmailTxtBox, friendEmail);
		setTextElementText(personalMessageTxtBox, message);
		clickButton(sendEmailBtn);
	}
}
