package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageactions.PaymentActions;
import pageobjects.DirectoryPage;
import utilities.Constants;

import static utilities.CommonFunctions.*;
import java.io.IOException;

public class PaymentStepDef {
	PaymentActions paymentActions = new PaymentActions();

    @Then("I verify that payment page is displayed")
    public void I_verify_that_payment_page_is_displayed() {
    	waitForElement(paymentActions.paymentPage.paymentHeading);
    	Assert.assertTrue(paymentActions.paymentPage.paymentHeading.isDisplayed());
    }
    
    @Given("^I enter payment details with (.+), (.+), (.+), (.+), (.+)$")
    public void I_enter_payment_details_with_name_card_cvc_expiry(String nameOnCard, String cardNum, String cvc, String expMonth, String expYear) {
    	waitForElement(paymentActions.paymentPage.nameOnCard);
		paymentActions.enterPaymentDetails(nameOnCard, cardNum, cvc, expMonth, expYear);
    }
    
    @And("I click pay and confirm order")
    public void I_click_pay_and_confirm_order() throws InterruptedException {
    	scrollToElement(paymentActions.paymentPage.expiryYear);
		paymentActions.paymentPage.payAndConfirmOrder.click();
    }
    
    @Then("I verify that successful order message is displayed")
    public void I_verify_that_successful_order_message_is_displayed() {
    	waitForElement(paymentActions.paymentPage.successOrderMsg);
    	Assert.assertTrue(paymentActions.paymentPage.successOrderMsg.isDisplayed());
    }
    
	@And("I click on download invoice")
	public void I_click_on_download_invoice() {
		waitForElement(paymentActions.paymentPage.downloadInvoice);
		paymentActions.paymentPage.downloadInvoice.click();
	}
	
	@Then("I verify that invoice is downloaded successfully")
	public void I_verify_that_invoice_is_downloaded_successfully() throws IOException {
		//Verify file is downloaded
		Assert.assertTrue(waitForDownload("invoice.txt", 10));

		//Verify contents of file
		Assert.assertTrue(readFileToString(Constants.DEFAULT_DOWNLOAD_PATH, "invoice.txt").contains("Your total purchase amount is " + getCache("Bill")));
	}

	@And("I delete invoice")
	public void I_delete_invoice(){
		paymentActions.deleteInvoice();
	}
}
