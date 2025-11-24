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
	
	@FindBy(css="td.subtotal")
	public WebElement totalLbl;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	public void removeProductFromCart() {
		clickButton(removeBtn);
	}

	public void openCheckoutPage() {
		clickButton(checkoutBtn);
	}

}
