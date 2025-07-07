package stepdefinitions;

import pageobjects.Directory;
import org.testng.Assert;
import io.cucumber.java.en.And;
import static utilities.CommonFunctions.*;

public class ContactStepDef {
	
	@And("I verify that get in touch is visible")
	public void I_verify_that_get_in_touch_is_visible() {
		waitForElement(Directory.contactUsPage.getInTouchHeading());
		Assert.assertTrue(Directory.contactUsPage.getInTouchHeading().isDisplayed());
	}
	
	@And("^I fill up contact us text fields with (.+), (.+), (.+), (.+)$")
	public void I_fill_up_contact_us_text_fields(String name, String email, String subject, String message) {
		Directory.contactUsPage.fillUpContactUsTextFields(name, email, subject, message);
	}
	
	@And("^I upload attachment with (.+)$")
	public void I_upload_attachment_with_file(String fileName) {
		Directory.contactUsPage.chooseFile(fileName);
	}
	
	@And("I submit contact us form")
	public void I_submit_contact_us_form() throws InterruptedException {
		Directory.contactUsPage.submitContactUsForm();
		handleAlerts("accept");
	}
	
	@And("I verify that success message is visible")
	public void I_verify_that_success_message_is_visible() throws InterruptedException {
		scrollToElement(Directory.contactUsPage.getSuccessMsg());
		Assert.assertTrue(Directory.contactUsPage.getSuccessMsg().isDisplayed());
		
	}
}
