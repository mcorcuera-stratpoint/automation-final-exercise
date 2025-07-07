package pageobjects;

import static utilities.CommonFunctions.*;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUp{
	WebDriver driver;
	
	public SignUp(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[id='name']")
	private WebElement name;
	
	@FindBy(css="input[id='email']")
	private WebElement email;
	
	@FindBy(css="input[id='password']")
	private WebElement password;
	
	@FindBy(css="select[id='days']")
	private WebElement birthDay;
	
	@FindBy(css="select[id='months']")
	private WebElement birthMonth;
	
	@FindBy(css="select[id='years']")
	private WebElement birthYear;

	@FindBy(xpath="//b[contains(text(), 'Enter Account Information')]")
	private WebElement accountInfoHeading;

	@FindBy(xpath="//b[contains(text(), 'Address Information')]")
	private WebElement addressInfo;

	@FindBy(css="input[id='newsletter']")
	private WebElement newsletter;
	
	@FindBy(css="input[id='optin']")
	private WebElement offers;
	
	@FindBy(css="input[id='first_name']")
	private WebElement firstName;
	
	@FindBy(css="input[id='last_name']")
	private WebElement lastName;
	
	@FindBy(css="input[id='company']")
	private WebElement company;
	
	@FindBy(css="input[id='address1']")
	private WebElement address1;
	
	@FindBy(css="input[id='address2']")
	private WebElement address2;
	
	@FindBy(css="select[id='country']")
	private WebElement country;
	
	@FindBy(css="input[id='state']")
	private WebElement state;
	
	@FindBy(css="input[id='city']")
	private WebElement city;
	
	@FindBy(css="input[id='zipcode']")
	private WebElement zipCode;
	
	@FindBy(css="input[id='mobile_number']")
	private WebElement mobileNumber;
	
	@FindBy(css="button[data-qa='create-account']")
	private WebElement createAccountBtn;
	
	@FindBy(xpath="//b[contains(text(), 'Account Created!')]")
	private WebElement accountCreated;
	
	//Dynamic Page Objects
	public WebElement title (String value) {
		return driver.findElement(By.cssSelector(String.format("input[name='title'][value='%s']", value)));
	}
	
	//Getters
	public WebElement getAccountInfoHeading() {
		return accountInfoHeading;
	}
	
	public WebElement getAccountCreated() {
		return accountCreated;
	}

	//Page methods
	public void fillAccountInfo(String iTitle, String iName, String iPassword, 
								LocalDate iBirth, Boolean iNews, Boolean iOffers) throws InterruptedException {
		
		Select dayDropDown = new Select(birthDay);
		Select monthDropDown = new Select(birthMonth);
		Select yearDropDown = new Select(birthYear);
		
		title(iTitle).click();
		
		//Save title to cache for address verification
		setCache("Title", title(iTitle).getAttribute("value") + ".");
		
		if(!name.getText().equals(iName)) {
			name.clear();
			name.sendKeys(iName);			
		}		
	
		password.sendKeys(iPassword);
		
		scrollToElement(birthDay);
		dayDropDown.selectByValue(String.valueOf(iBirth.getDayOfMonth()));
		monthDropDown.selectByValue(String.valueOf(iBirth.getMonthValue()));
		yearDropDown.selectByValue(String.valueOf(iBirth.getYear()));
		
		//Toggles check boxes if parameter passed is true
		if(iNews) {
			newsletter.click();
		}
		if(iOffers) {
			offers.click();
		}
		
		System.out.println("Created Account!");
	}
	
	public void fillAddressInfo(String iFirstName, String iLastName, String iCompany, String iAddress1, 
								String iAddress2, String iCountry, String iState, String iCity, String iZipCode, String iMobile) throws InterruptedException {
		
		Select countryDropDown = new Select(country);
		
		scrollToElement(firstName);
		firstName.sendKeys(iFirstName);
		lastName.sendKeys(iLastName);
		company.sendKeys(iCompany);
		address1.sendKeys(iAddress1);
		address2.sendKeys(iAddress2);
		countryDropDown.selectByValue(iCountry);
		state.sendKeys(iState);
		city.sendKeys(iCity);
		
		scrollToElement(zipCode);
		zipCode.sendKeys(iZipCode);
		mobileNumber.sendKeys(iMobile);
		
		//Saves values to cache for address verification
		setCache("FirstName", iFirstName);
		setCache("LastName", iLastName);
		setCache("Company", iCompany);
		setCache("Address1", iAddress1);
		setCache("Address2", iAddress2);
		setCache("Country", iCountry);
		setCache("State", iState);
		setCache("City", iCity);
		setCache("ZipCode", iZipCode);
		setCache("MobileNumber", iMobile);
	}
	
	public void submitAccountForm() {
		
		scrollToElement(createAccountBtn);
		createAccountBtn.click();
	}


}
