package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	
	@Given("The user in the home page")
	public void the_user_in_the_home_page() {
		homeObject = new HomePage(driver);
	}
	@When("I click on register link")
	public void i_click_on_register_link() {
		homeObject.openRegistrationPage();
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}
	@When("I entered {string}, {string}, {string}, {string}")
	public void i_entered(String firstname, String lastname, String email, String password) {
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstname, lastname, email, password);
	}
	/*
	@When("I entered the user data")
	public void i_entered_the_user_data() {
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("Ahmed", "Morsy", "testmail6@gmail.com", "12345678");
	}
	*/
	@Then("The registration page displayed successfully")
	public void the_registration_page_displayed_successfully() {
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your Registration completed"));
		registerObject.userLogout();
	}
}
