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
	@FindBy(css="a[href='/']")
	static WebElement home;

	@FindBy(css="a[href='/products']")
	static WebElement products;

	@FindBy(css="a[href='/view_cart']")
	static WebElement cart;

	@FindBy(css="a[href='/login']")
	static WebElement signup;

	@FindBy(css="a[href='/test_cases']")
	static WebElement testCases;

	@FindBy(id="api")
	static WebElement api;

	@FindBy(id="video")
	static WebElement video;

	@FindBy(css="a[href='/contact_us']")
	static WebElement contact;

	@FindBy(css="input[type='email']")
	private WebElement subscribeEmail;
	
	@FindBy(css="button[id='subscribe']")
	private WebElement subscribeBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Subscription')]")
	private WebElement subscriptionHeading;
	
	@FindBy(xpath="//div[@class='alert-success alert' and contains(text(), 'You have been successfully subscribed!')]")
	private WebElement subscriptionMsg;

	//Getters
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

	public void goToHeader(String header) {
		switch(header) {

			case "Home":
				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://automationexercise.com/")) {
					home.click();
					Directory.homePage = new Home(driver);
				}
				break;

			case "Products":
				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/products")) {
					products.click();
					Directory.productsPage = new Products(driver);
				}
				break;

			case "Cart":
				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/view_cart")) {
					cart.click();
					Directory.cartPage = new Cart(driver);
				}
				break;

			case "Signup/Login":

				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/login")) {
					signup.click();
					Directory.loginPage = new Login(driver);
				}
				break;

			case "Test Cases":
				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/test_cases")) {
					testCases.click();
					Directory.testCasesPage = new TestCases(driver);
				}
				testCases.click();
				break;

			case "Contact Us":
				//Checks if user is already on the page
				if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/contact_us")) {
					contact.click();
					Directory.contactUsPage = new ContactUs(driver);
				}
				contact.click();
				break;

			default:
				System.out.println("Invalid header");
				driver.quit();
				break;
		}

	}

}
