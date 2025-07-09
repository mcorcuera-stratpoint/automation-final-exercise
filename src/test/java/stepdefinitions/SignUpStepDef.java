package stepdefinitions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import io.cucumber.java.en.And;
import pageactions.SignUpActions;
import pageobjects.DirectoryPage;

import static utilities.CommonFunctions.*;

public class SignUpStepDef{
	SignUpActions signUpActions = new SignUpActions();

	@And("^I fill account info with (.+), (.+), (.+), (.+), (.+), (.+)$")
	public void I_fill_account_info(String iTitle, String iName, String iPassword, String iDate, String iNews, String iOffers ) throws InterruptedException {
		
		//Populate account information fields
		signUpActions.fillAccountInfo(iTitle, iName, iPassword, iDate, Boolean.valueOf(iNews), Boolean.valueOf(iOffers));
	}
	
	//Note: iAddressLine string format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
	@And("^I fill address info with (.+), (.+), (.+), (.+), (.+)$")
	public void I_fill_account_info(String iFirstName, String iLastName, String iCompany, String iAddressLine, String iMobile)throws InterruptedException {

		//Populate address information fields
		signUpActions.fillAddressInfo(iFirstName, iLastName, iCompany, iAddressLine, iMobile);
	}
	
	@And("^I verify that enter account information is visible")
	public void I_fill_account_info() {
		
		waitForElement(signUpActions.signUpPage.accountInfoHeading);
		Assert.assertTrue(signUpActions.signUpPage.accountInfoHeading.isDisplayed());
	}
	
	@And("I verify that a new user account is created")
	public void a_new_user_account_is_created() {
		
		waitForElement(signUpActions.signUpPage.accountCreated);
		Assert.assertTrue(signUpActions.signUpPage.accountCreated.isDisplayed());
		signUpActions.signUpPage.continueBtn.click();
	}
	
	@And("I submit the sign up form")
	public void I_submit_the_sign_up_form() throws InterruptedException {

		signUpActions.submitAccountForm();
		Thread.sleep(Duration.ofSeconds(1));
	}
}
