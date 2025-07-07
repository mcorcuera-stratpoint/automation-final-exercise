package stepdefinitions;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.Directory;
import static utilities.CommonFunctions.*;

public class HomeStepDef {
	
	@Then("I verify that home page is visible")
	public void I_verify_that_home_page_is_visible() throws IOException {
		
		waitForElement(Directory.homePage.getSliderCarousel());
		Assert.assertTrue(Directory.homePage.getSliderCarousel().isDisplayed());
	}
	
	@And("^I verify that logged in as (.+) is visible$")
	public void I_verify_that_logged_in_as_username_is_visible(String username) {
		
		waitForElement(Directory.homePage.loggedInAsUser(username));
		Assert.assertTrue(Directory.homePage.loggedInAsUser(username).isDisplayed());
	}
	
	@And("I delete the account")
	public void I_delete_user_account() {
		
		waitForElement(Directory.homePage.getDeleteAccountBtn());
		Directory.homePage.getDeleteAccountBtn().click();
	}
	
	@Then("I verify that account deleted message is visible")
	public void I_verify_that_account_deleted_is_visible() throws InterruptedException {
		
		waitForElement(Directory.homePage.getAccountDeleted());
		Assert.assertTrue(Directory.homePage.getAccountDeleted().isDisplayed());
		
		Directory.homePage.getContinueBtn().click();
		Thread.sleep(Duration.ofSeconds(1));
	}
	
	@And("^I add to cart a recommended item (.+)$")
	public void I_add_to_cart_a_recommended_item(String product) throws InterruptedException {
		Directory.homePage.addToCartRecommendedItem(product);
	}
	
	@And("I logout from the account")
	public void I_logout_from_the_account() {
		waitForElement(Directory.homePage.getLogoutBtn());
		Directory.homePage.getLogoutBtn().click();
	}
	
	@And("I click the scroll up arrow")
	public void I_click_the_scroll_up_arrow() {
		waitForElement(Directory.homePage.getScrollUpButton());
		Directory.homePage.getScrollUpButton().click();
	}
	
	@And("I verify that full-fledged text is displayed")
	public void I_verify_that_full_fledged_text_is_displayed() throws InterruptedException{
		waitForElement(Directory.homePage.getFullFledgedText());
		Assert.assertTrue(Directory.homePage.getFullFledgedText().isDisplayed());
		Thread.sleep(Duration.ofSeconds(1));
	}
	
	@And("I verify that recommended items are visible")
	public void I_verify_that_recommended_items_are_visible() throws IOException {		
		scrollToElement(Directory.homePage.getRecomItemsHeading());
		Assert.assertTrue(Directory.homePage.getRecomItemsHeading().isDisplayed());
	}
	
}
