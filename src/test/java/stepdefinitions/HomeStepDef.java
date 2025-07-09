package stepdefinitions;

import java.time.Duration;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageactions.HomePageActions;

import static utilities.CommonFunctions.*;

public class HomeStepDef {
	HomePageActions homePageActions = new HomePageActions();

	@Then("I verify that home page is visible")
	public void I_verify_that_home_page_is_visible() {
		waitForElement(homePageActions.homePage.sliderCarousel);
		Assert.assertTrue(homePageActions.homePage.sliderCarousel.isDisplayed());
	}
	
	@And("^I verify that logged in as (.+) is visible$")
	public void I_verify_that_logged_in_as_username_is_visible(String username) {
		waitForElement(homePageActions.homePage.loggedInAsUser(username));
		Assert.assertTrue(homePageActions.homePage.loggedInAsUser(username).isDisplayed());
	}
	
	@And("I delete the account")
	public void I_delete_user_account() {
		waitForElement(homePageActions.homePage.deleteAccountBtn);
		homePageActions.homePage.deleteAccountBtn.click();
	}
	
	@Then("I verify that account deleted message is visible")
	public void I_verify_that_account_deleted_is_visible() throws InterruptedException {
		waitForElement(homePageActions.homePage.accountDeleted);
		Assert.assertTrue(homePageActions.homePage.accountDeleted.isDisplayed());
		homePageActions.homePage.continueBtn.click();
		Thread.sleep(Duration.ofSeconds(1));
	}
	
	@And("^I add to cart a recommended item (.+)$")
	public void I_add_to_cart_a_recommended_item(String product) throws InterruptedException {
		homePageActions.addToCartRecommendedItem(product);
	}
	
	@And("I logout from the account")
	public void I_logout_from_the_account() {
		waitForElement(homePageActions.homePage.logoutBtn);
		homePageActions.homePage.logoutBtn.click();
	}
	
	@And("I click the scroll up arrow")
	public void I_click_the_scroll_up_arrow() {
		waitForElement(homePageActions.homePage.scrollUpBtn);
		homePageActions.homePage.scrollUpBtn.click();
	}
	
	@And("I verify that full-fledged text is displayed")
	public void I_verify_that_full_fledged_text_is_displayed() throws InterruptedException{
		waitForElement(homePageActions.homePage.fullFledgedText);
		Assert.assertTrue(homePageActions.homePage.fullFledgedText.isDisplayed());
		Thread.sleep(Duration.ofSeconds(1));
	}
	
	@And("I verify that recommended items are visible")
	public void I_verify_that_recommended_items_are_visible() {
		scrollToElement(homePageActions.homePage.recomItemsHeading);
		Assert.assertTrue(homePageActions.homePage.recomItemsHeading.isDisplayed());
	}
}
