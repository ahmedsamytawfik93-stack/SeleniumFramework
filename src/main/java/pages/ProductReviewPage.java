package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductReviewPage extends PageBase{

	public ProductReviewPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="AddProductReview_Title")
	WebElement reviewTitleTxtBox;
	
	@FindBy(id="AddProductReview_ReviewText")
	WebElement reviewTextTxtBox;
	
	@FindBy(id="addproductrating_4")
	WebElement Rating4RdoBtn;
	
	@FindBy(id="add-review")
	WebElement submitReviewBtn;
	
	@FindBy(css="div.result")
	public WebElement reviewNotification;
	
	public void addProductReview(String reviewTiltle, String reviewMessage) {
		setTextElementText(reviewTitleTxtBox, reviewTiltle);
		setTextElementText(reviewTextTxtBox, reviewMessage);
		clickButton(Rating4RdoBtn);
		clickButton(submitReviewBtn);
	}

}
