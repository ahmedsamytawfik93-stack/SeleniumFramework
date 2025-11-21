package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText="Change password")
	WebElement changePasswordLink;
	
	@FindBy(id="OldPassword")
	WebElement oldPasswordTxtBox;
	
	@FindBy(id="NewPassword")
	WebElement newPasswordTxtBox;
	
	@FindBy(id="ConfirmNewPassword")
	WebElement confirmPasswordTxtBox;
	
	@FindBy(css="input.button-1.change-password-button")
	WebElement changePasswordBtn;
	
	@FindBy(css="div.result")
	public WebElement resultLbl;
	
	public void openChangePasswordPage() {
		clickButton(changePasswordLink);
	}
	
	public void changePassword(String oldPass, String newPass ) {
		setTextElementText(oldPasswordTxtBox, oldPass);
		setTextElementText(newPasswordTxtBox, newPass);
		setTextElementText(confirmPasswordTxtBox, newPass);
		clickButton(changePasswordBtn);
	}
}
