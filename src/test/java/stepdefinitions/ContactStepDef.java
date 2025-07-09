package stepdefinitions;

import pageactions.ContactUsActions;
import org.testng.Assert;
import io.cucumber.java.en.And;
import static utilities.CommonFunctions.*;

public class ContactStepDef {
	ContactUsActions contactUsActions = new ContactUsActions();

	@And("I verify that get in touch is visible")
	public void I_verify_that_get_in_touch_is_visible() {
		waitForElement(contactUsActions.contactUsPage.getInTouchHeading);
		Assert.assertTrue(contactUsActions.contactUsPage.getInTouchHeading.isDisplayed());
	}
	
	@And("^I fill up contact us text fields with (.+), (.+), (.+), (.+)$")
	public void I_fill_up_contact_us_text_fields(String name, String email, String subject, String message) {
		contactUsActions.fillUpContactUsTextFields(name, email, subject, message);
	}
	
	@And("^I upload attachment with (.+)$")
	public void I_upload_attachment_with_file(String fileName) {
		contactUsActions.chooseFile(fileName);
	}
	
	@And("I submit contact us form")
	public void I_submit_contact_us_form() throws InterruptedException {
		contactUsActions.submitContactUsForm();
		handleAlerts("accept");
	}
	
	@And("I verify that success message is visible")
	public void I_verify_that_success_message_is_visible() {
		scrollToElement(contactUsActions.contactUsPage.successMsg);
		Assert.assertTrue(contactUsActions.contactUsPage.successMsg.isDisplayed());
	}
}
