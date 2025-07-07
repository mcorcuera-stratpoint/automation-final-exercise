package stepdefinitions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import io.cucumber.java.en.And;
import pageobjects.Directory;
import static utilities.CommonFunctions.*;

public class SignUpStepDef{
	
	@And("^I fill account info with (.+), (.+), (.+), (.+), (.+), (.+)$")
	public void I_fill_account_info(String iTitle, String iName, String iPassword, 
									String iDate, String iNews, String iOffers ) throws InterruptedException {
		
		//Parse iDate from string to local date object
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate iBirth = LocalDate.parse(iDate, format);
		
		//Populate account information fields
		Directory.signUpPage.fillAccountInfo(iTitle, iName, iPassword, iBirth, 
											   Boolean.valueOf(iNews), Boolean.valueOf(iOffers));
	}
	
	//Note: iAddressLine string format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
	@And("^I fill address info with (.+), (.+), (.+), (.+), (.+)$")
	public void I_fill_account_info(String iFirstName, String iLastName, String iCompany, String iAddressLine, String iMobile)throws InterruptedException {
		
		//Parse iAddressLine as string array
		String[] address = iAddressLine.split(",");
		 
		//Populate address information fields
		Directory.signUpPage.fillAddressInfo(iFirstName, iLastName, iCompany, address[0], address[1], 
											 address[2], address[3], address[4], address[5], iMobile);
		
	}
	
	@And("^I verify that enter account information is visible")
	public void I_fill_account_info() {
		
		waitForElement(Directory.signUpPage.getAccountInfoHeading());
		Assert.assertTrue(Directory.signUpPage.getAccountInfoHeading().isDisplayed());
	}
	
	@And("I verify that a new user account is created")
	public void a_new_user_account_is_created() {
		
		waitForElement(Directory.signUpPage.getAccountCreated());
		Assert.assertTrue(Directory.signUpPage.getAccountCreated().isDisplayed());
		Directory.homePage.getContinueBtn().click();
	}
	
	@And("I submit the sign up form")
	public void I_submit_the_sign_up_form() throws InterruptedException {
		
		Directory.signUpPage.submitAccountForm();
		Thread.sleep(Duration.ofSeconds(1));
	}
}
