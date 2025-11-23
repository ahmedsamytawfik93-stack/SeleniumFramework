package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends PageBase{

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="BillingNewAddress_FirstName")
	WebElement firstNameTxtBox;
	
	@FindBy(id="BillingNewAddress_LastName")
	WebElement lastNameTxtBox;
	
	@FindBy(id="BillingNewAddress_Email")
	WebElement emailTxtBox;
	
	@FindBy(id="BillingNewAddress_CountryId")
	WebElement countryDDL;
	
	@FindBy(id="BillingNewAddress_City")
	WebElement cityTxtBox;
	
	@FindBy(id="BillingNewAddress_Address1")
	WebElement address1TxtBox;
	
	@FindBy(id="BillingNewAddress_ZipPostalCode")
	WebElement zipPostalCodeTxtBox;
	
	@FindBy(id="BillingNewAddress_PhoneNumber")
	WebElement phoneNumberTxtBox;
	
	@FindBy(css="button-1.new-address-next-step-button")
	WebElement billingAddressContinueBtn;
	
	@FindBy(id="shippingoption_0")
	WebElement shippingOption0Check;
	
	@FindBy(css="button-1.shipping-method-next-step-button")
	WebElement shippingMethodContinueBtn;
	
	@FindBy(id="paymentmethod_0")
	WebElement paymentMethod0Check;
	
	@FindBy(css="button-1.payment-info-next-step-button")
	WebElement paymentInfoContinueBtn;
	
	@FindBy(css="button-1.payment-method-next-step-button")
	WebElement paymentMethodContinueBtn;
	
	@FindBy(css="a.product-name")
	public WebElement productName;
	
	@FindBy(css="button-1.confirm-order-next-step-button")
	WebElement confirmOrderContinueBtn;

	@FindBy(css="h1")
	public WebElement thankYouLbl;
	
	@FindBy(css="div.title")
	public WebElement successMessage;
	
	@FindBy(linkText="Click here for order details.")
	WebElement orderDetailLink;

	public void guestUserCanAddNameAndEmailToOrder(String firstName, String lastName, String email) {
		// Billing Address To Be Filled Only By Guest
		setTextElementText(firstNameTxtBox,firstName);
		setTextElementText(lastNameTxtBox, lastName);
		setTextElementText(emailTxtBox, email);
	}
	
	public void userCanFillBillingAddressForm(String countryName, String city, String address, String postcode, String phone) {
		// Billing Address Form
		select = new Select(countryDDL);
		select.selectByVisibleText(countryName);
		setTextElementText(cityTxtBox, city);
		setTextElementText(address1TxtBox, address);
		setTextElementText(zipPostalCodeTxtBox, postcode);
		setTextElementText(phoneNumberTxtBox, phone);
		clickButton(billingAddressContinueBtn);
	}
	
	public void userCanChooseShippingMethod() {
		// Shipping Method
		clickButton(shippingOption0Check);
		clickButton(shippingMethodContinueBtn);
	}
	
	public void userCanChoosePaymentMethod() {	
		// Payment Method
		clickButton(paymentMethod0Check);
		clickButton(paymentMethodContinueBtn);
	}
	
	public void userCanConfirmPaymentInfo() {	
		// Payment Info Confirmation
		clickButton(paymentInfoContinueBtn);
	}
	
	public void userCanConfirmOrder() {	
		//  Confirm Order
		clickButton(confirmOrderContinueBtn);
	}
	
	public void userCanShowConfirmedOrderDetails() {
		clickButton(orderDetailLink);
	}
}
