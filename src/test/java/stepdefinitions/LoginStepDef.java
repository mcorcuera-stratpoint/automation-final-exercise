package stepdefinitions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.testng.Assert;
import pageobjects.Directory;
import static utilities.CommonFunctions.*;
import io.cucumber.java.en.And;

public class LoginStepDef{
	
	@And("^I sign up with (.+) and (.+)$")
	public void I_sign_up_with_name_and_email(String name, String email) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		if(email.equals("auto")) {
			email = currentDateTime.truncatedTo(ChronoUnit.SECONDS).format(formatter).toString() + "@email.com";
			setCache("cache_email", email);
		}
		
		Directory.loginPage.signUp(email, name);
	}
	
	@And("I verify that new user sign up is visible")
	public void I_verify_that_new_user_sign_up_is_visible() {
		waitForElement(Directory.loginPage.getNewUserHeading());
		Assert.assertTrue(Directory.loginPage.getNewUserHeading().isDisplayed());
	}
	
	@And("I verify that login to your account is visible")
	public void I_verify_that_login_to_your_account_is_visible() {
		waitForElement(Directory.loginPage.getLoginHeading());
		Assert.assertTrue(Directory.loginPage.getLoginHeading().isDisplayed());
	}
	
	@And("^I login with (.+) and (.+)$")
	public void I_login_with_email_and_password(String email, String password) throws InterruptedException {
		waitForElement(Directory.loginPage.getLoginHeading());
		
		if(email.equals("auto")) {
			email = getCache("cache_email");
		}
		Directory.loginPage.login(email, password);
	}
	
	@And("I verify that incorrect credentials message is visible")
	public void I_verify_that_incorrect_credentials_message_is_visible() {
		waitForElement(Directory.loginPage.getIncorrectCredentials());
		Assert.assertTrue(Directory.loginPage.getIncorrectCredentials().isDisplayed());
	}
	
	@And("I verify that existing email address message is visible")
	public void I_verify_that_existing_email_address_message_is_visible() {
		waitForElement(Directory.loginPage.getExistingEmailMsg());
		Assert.assertTrue(Directory.loginPage.getExistingEmailMsg().isDisplayed());
	}
}
