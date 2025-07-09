package pageobjects;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import static utilities.CommonFunctions.*;
import static components.BaseTest.*;

public class SignUpPage {
	
	public SignUpPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[id='name']")
	public WebElement name;
	
	@FindBy(css="input[id='email']")
	public WebElement email;
	
	@FindBy(css="input[id='password']")
	public WebElement password;
	
	@FindBy(css="select[id='days']")
	public WebElement birthDay;
	
	@FindBy(css="select[id='months']")
	public WebElement birthMonth;
	
	@FindBy(css="select[id='years']")
	public WebElement birthYear;

	@FindBy(xpath="//b[contains(text(), 'Enter Account Information')]")
	public WebElement accountInfoHeading;

	@FindBy(xpath="//b[contains(text(), 'Address Information')]")
	public WebElement addressInfo;

	@FindBy(css="input[id='newsletter']")
	public WebElement newsletter;
	
	@FindBy(css="input[id='optin']")
	public WebElement offers;
	
	@FindBy(css="input[id='first_name']")
	public WebElement firstName;
	
	@FindBy(css="input[id='last_name']")
	public WebElement lastName;
	
	@FindBy(css="input[id='company']")
	public WebElement company;
	
	@FindBy(css="input[id='address1']")
	public WebElement address1;
	
	@FindBy(css="input[id='address2']")
	public WebElement address2;
	
	@FindBy(css="select[id='country']")
	public WebElement country;
	
	@FindBy(css="input[id='state']")
	public WebElement state;
	
	@FindBy(css="input[id='city']")
	public WebElement city;
	
	@FindBy(css="input[id='zipcode']")
	public WebElement zipCode;
	
	@FindBy(css="input[id='mobile_number']")
	public WebElement mobileNumber;
	
	@FindBy(css="button[data-qa='create-account']")
	public WebElement createAccountBtn;
	
	@FindBy(xpath="//b[contains(text(), 'Account Created!')]")
	public WebElement accountCreated;

	@FindBy(css="a[data-qa='continue-button']")
	public WebElement continueBtn;
	
	//Dynamic Page Objects
	public WebElement title (String value) {
		return driver.findElement(By.cssSelector(String.format("input[name='title'][value='%s']", value)));
	}
}
