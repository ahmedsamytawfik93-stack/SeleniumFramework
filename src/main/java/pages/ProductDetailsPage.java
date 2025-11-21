package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase{

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="h1.Apple.MacBook.Pro")
	public WebElement productNameBreadCrumb;

	@FindBy(css="button-2.email-a-friend-button")
	WebElement emailFriendBtn;
	
	@FindBy(css="span.price-value-4")
	public WebElement productPriceLbl;
	
	@FindBy(linkText="Add your review")
	WebElement productReviewLink;
	
	@FindBy(id="add-to-wishlist-button-4")
	WebElement productAddToWishListBtn;
	
	@FindBy(css="input.button-2.add-to-compare-list-button")
	WebElement addToCompareBtn;
	
	@FindBy(id="add-to-cart-button-4")
	WebElement productAddToCart;
	
	public void openSendEmail() {
		clickButton(emailFriendBtn);
	}
	
	public void openReviewForm() {
		clickButton(productReviewLink);
	}
	
	public void addProductToWishList() {
		clickButton(productAddToWishListBtn);
	}
	
	public void addProductToCompare() {
		clickButton(addToCompareBtn);
	}
	
	public void addProductToCart() {
		clickButton(productAddToCart);
	}
}
