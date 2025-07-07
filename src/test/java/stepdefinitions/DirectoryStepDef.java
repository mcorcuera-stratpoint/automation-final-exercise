package stepdefinitions;

import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;
import components.BaseTest;

import static utilities.CommonFunctions.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class DirectoryStepDef extends BaseTest{
	
	@Given("I launched website")
	public void I_launched_website() throws IOException {
		launchBrowser();
	}
	
	@Given("^I landed on (.+) page$")
	public void I_landed_on_header_page(String header) {
		goToHeader(header);
	}
	
	@And("I scroll to the bottom of the page")
	public void I_scroll_to_the_bottom_of_the_page() throws InterruptedException{
		scrollToBottomOfPage();
	}
	
	@And("I scroll to the top of the page")
	public void I_scroll_to_the_top_of_the_page() throws InterruptedException {
		scrollToTopOfPage();
	}
	
	@Given("I close the browser")
	public void I_close_the_browser() {
		closeBrowser();
	}
	
	@And("^I subscribe to the website with (.+)$")
	public void I_subscribe_to_the_website_with_email(String email){
		scrollToElement(directory.getSubscriptionEmail());
		directory.subscribeToWebsite(email);
	}
	
	@And("I verify that subscription heading is displayed")
	public void I_verify_that_subscription_heading_is_displayed() throws InterruptedException {
		scrollToElement(directory.getSubscriptionHeading());
		Assert.assertTrue(directory.getSubscriptionHeading().isDisplayed());
	}
	
	@And("I verify that successful subscription message is displayed")
	public void I_verify_that_successful_subscription_message_is_displayed() throws InterruptedException{
		Thread.sleep(Duration.ofMillis(500));
		Assert.assertTrue(directory.getSubscriptionMsg().isDisplayed());
		
	}
}
