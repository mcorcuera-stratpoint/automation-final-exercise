package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageobjects.Directory;
import utilities.Constants;

import static utilities.CommonFunctions.*;
import java.io.IOException;

public class PaymentStepDef {
    @Then("I verify that payment page is displayed")
    public void I_verify_that_payment_page_is_displayed() {
    	waitForElement(Directory.paymentPage.getPaymentHeading());
    	Assert.assertTrue(Directory.paymentPage.getPaymentHeading().isDisplayed());
    }
    
    @Given("^I enter payment details with (.+), (.+), (.+), (.+), (.+)$")
    public void I_enter_payment_details_with_name_card_cvc_expiry(String nameOnCard, String cardNum, String cvc, String expMonth, String expYear) {
    	waitForElement(Directory.paymentPage.getNameOnCard());
    	Directory.paymentPage.getNameOnCard().sendKeys(nameOnCard);
    	Directory.paymentPage.getCardNumber().sendKeys(cardNum);
    	Directory.paymentPage.getCvc().sendKeys(cvc);
    	Directory.paymentPage.getExpiryMonth().sendKeys(expMonth);
    	Directory.paymentPage.getExpiryYear().sendKeys(expYear);
    }
    
    @And("I click pay and confirm order")
    public void I_click_pay_and_confirm_order() throws InterruptedException {
    	scrollToElement(Directory.paymentPage.getExpiryYear());
    	Directory.paymentPage.getPayAndConfirmOrder().click();
    }
    
    @Then("I verify that successful order message is displayed")
    public void I_verify_that_successful_order_message_is_displayed() {
    	waitForElement(Directory.paymentPage.getSuccessOrderMessage());
    	Assert.assertTrue(Directory.paymentPage.getSuccessOrderMessage().isDisplayed());
    }
    
	@And("I click on download invoice")
	public void I_click_on_download_invoice() {
		waitForElement(Directory.paymentPage.getDownloadInvoice());
		Directory.paymentPage.getDownloadInvoice().click();
	}
	
	@Then("I verify that invoice is downloaded successfully")
	public void I_verify_that_invoice_is_downloaded_successfully() throws IOException {
		//Verify file is downloaded
		Assert.assertTrue(waitForDownload("invoice.txt", 10));
		
		//Verify contents of file
		String invoice = readFileToString(Constants.DEFAULT_DOWNLOAD_PATH, "invoice.txt");
		String bill = getCache("Bill");
		Assert.assertTrue(invoice.contains("Your total purchase amount is " + bill));
		
		//Delete file
		deleteFile(Constants.DEFAULT_DOWNLOAD_PATH, "invoice.txt");
	}
}
