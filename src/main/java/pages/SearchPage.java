package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBase{

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="small-searchterms")
	WebElement searchTxtBox;
	
	@FindBy(css="button.button-1.search-box-button")
	WebElement searchBtn;
	
	@FindBy(id="ui-id-1")
	List<WebElement> productList;
	
	@FindBy(linkText="Apple MacBook Pro")
	public WebElement productTitle;
	
	public void productsearch(String productname) {
		setTextElementText(searchTxtBox, productname);
		clickButton(searchBtn);
	}
	
	public void openProductDetailsPage() {
		clickButton(productTitle);
	}
	
	public void productSearchUsingAutoSuggest(String searchTxt) {
		setTextElementText(searchTxtBox, searchTxt);
		try {
			Thread.sleep(Duration.ofSeconds(3));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productList.get(0).click();
	}
}
