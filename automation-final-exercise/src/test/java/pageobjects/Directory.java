package pageobjects;

import static utilities.CommonFunctions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Directory {
	WebDriver driver;
	public static Cart cartPage;
	public static ContactUs contactUsPage;
	public static Home homePage;
	public static Login loginPage;
	public static Payment paymentPage;
	public static Products productsPage;
	public static SignUp signUpPage;
	public static TestCases testCasesPage;
	
	public Directory(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[type='email']")
	private WebElement subscribeEmail;
	
	@FindBy(css="button[id='subscribe']")
	private WebElement subscribeBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Subscription')]")
	private WebElement subscriptionHeading;
	
	@FindBy(xpath="//div[@class='alert-success alert' and contains(text(), 'You have been successfully subscribed!')]")
	private WebElement subscriptionMsg;
	
	public WebElement getSubscriptionMsg() {
		return subscriptionMsg;
	}
	
	public WebElement getSubscriptionEmail() {
		return subscribeEmail;
	}
	
	public WebElement getSubscriptionHeading() {
		return subscriptionHeading;
	}	
	
	//Page methods
	public void subscribeToWebsite(String email){
		scrollToElement(subscribeEmail);
		subscribeEmail.sendKeys(email);
		subscribeBtn.click();
	}
}
