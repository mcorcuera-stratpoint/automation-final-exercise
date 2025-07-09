package stepdefinitions;

import org.testng.Assert;

import static utilities.CommonFunctions.*;
import io.cucumber.java.en.And;
import pageactions.LoginActions;

public class LoginStepDef{
	LoginActions loginActions = new LoginActions();
	
	@And("I verify that new user sign up is visible")
	public void I_verify_that_new_user_sign_up_is_visible() {
		waitForElement(loginActions.loginPage.newUserHeading);
		Assert.assertTrue(loginActions.loginPage.newUserHeading.isDisplayed());
	}
	
	@And("I verify that login to your account is visible")
	public void I_verify_that_login_to_your_account_is_visible() {
		waitForElement(loginActions.loginPage.loginHeading);
		Assert.assertTrue(loginActions.loginPage.loginHeading.isDisplayed());
	}
	
	@And("^I login with (.+) and (.+)$")
	public void I_login_with_email_and_password(String email, String password) throws InterruptedException {
		waitForElement(loginActions.loginPage.loginHeading);
		loginActions.login(email, password);
	}
	
	@And("I verify that incorrect credentials message is visible")
	public void I_verify_that_incorrect_credentials_message_is_visible() {
		waitForElement(loginActions.loginPage.incorrectCredentials);
		Assert.assertTrue(loginActions.loginPage.incorrectCredentials.isDisplayed());
	}
	
	@And("I verify that existing email address message is visible")
	public void I_verify_that_existing_email_address_message_is_visible() {
		waitForElement(loginActions.loginPage.existingEmailMsg);
		Assert.assertTrue(loginActions.loginPage.existingEmailMsg.isDisplayed());
	}

	@And("^I sign up with (.+) and (.+)$")
	public void I_sign_up_with_name_and_email(String name, String email) {
		loginActions.signUp(email, name);
	}
}
