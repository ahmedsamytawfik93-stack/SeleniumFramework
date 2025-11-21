package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase{

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="gender")
	WebElement genderdoBtn;
	
	@FindBy(id="gender-male")
	WebElement genderMaleRdoBtn;
	
	@FindBy(id="gender-female")
	WebElement genderFemaleRdoBtn;
	
	@FindBy(id="FirstName")
	WebElement firstNameTxtBox;
	
	@FindBy(id="LastName")
	WebElement lastNameTxtBox;
	
	@FindBy(id="Email")
	WebElement emailTxtBox;
	
	@FindBy(id="Company")
	WebElement CompanyTxtBox;
	
	@FindBy(id="NewsLetterSubscriptions_0__IsActive")
	WebElement newsLetterSubscriptionsRdoBtn;
	
	@FindBy(id="Password")
	WebElement passwordTxtBox;
	
	@FindBy(id="ConfirmPassword")
	WebElement confirmPasswordTxtBox;
	
	@FindBy(id="register-button")
	WebElement registerBtn;
	
	@FindBy(css="dic.result")
	public WebElement successMessage;
	
	@FindBy(linkText="Log out")
	public WebElement logutLink;
	
	@FindBy(linkText="My account")
	WebElement myAccountLink;
	
	public void userRegistration(String firstName, String LastName, String email, String password) {
		clickButton(genderMaleRdoBtn);
		setTextElementText(firstNameTxtBox, firstName);
		setTextElementText(lastNameTxtBox, LastName);
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		clickButton(registerBtn);
	}
	
	public void userLogout() {
		clickButton(logutLink);
	}
	
	public void openMyAccountPage() {
		clickButton(myAccountLink);
	}
}
