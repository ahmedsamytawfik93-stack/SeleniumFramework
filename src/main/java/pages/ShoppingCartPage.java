package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="removefromcart")
	WebElement removeBtn;
	
	@FindBy(name="updatecart")
	WebElement updateCartBtn;
	
	@FindBy(css="input.qty-input valid")
	WebElement quantityTxt;
	
	@FindBy(css="td.subtotal")
	public WebElement totalLbl;
	
	@FindBy(id="termsofservice")
	WebElement termsOfserviceCheckBox;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	public void removeProductFromCart() {
		clickButton(removeBtn);
	}

	public void updateProductQuantityInCart(String quantity) {
		clearText(quantityTxt);
		setTextElementText(quantityTxt, quantity);
	}
	
	public void checkTermsOfService() {
		clickButton(termsOfserviceCheckBox);
	}
	
	public void openCheckoutPage() {
		clickButton(checkoutBtn);
	}

}
