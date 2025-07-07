package pageobjects;

import static pageobjects.Directory.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login{
	WebDriver driver;
	
	public Login(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[data-qa='login-email']")
	private WebElement loginEmail;
	
	@FindBy(css="input[data-qa='login-password']")
	private WebElement loginPassword;
	
	@FindBy(css="input[data-qa='signup-email']")
	private WebElement signUpEmail;
	
	@FindBy(css="input[data-qa='signup-name']")
	private WebElement signUpName;
	
	@FindBy(css="button[data-qa='login-button']")
	private WebElement loginBtn;
	
	@FindBy(css="button[data-qa='signup-button']")
	private WebElement signUpBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Login to your account')]")
	private WebElement loginHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'New User Signup!')]")
	private WebElement newUserHeading;
	
	@FindBy(xpath="//p[contains(text(), 'Your email or password is incorrect!')]")
	private WebElement incorrectCredentialsMsg;
	
	@FindBy(xpath="//p[contains(text(), 'Email Address already exist!')]")
	private WebElement existingEmailMsg;
		
	//Getters
	public WebElement getNewUserHeading() {
		return newUserHeading;
	}
	
	public WebElement getLoginHeading() {
		return loginHeading;
	}
	
	public WebElement getIncorrectCredentials() {
		return incorrectCredentialsMsg;
	}
	
	public WebElement getExistingEmailMsg() {
		return existingEmailMsg;
	}
	
	//Page methods
	public void login(String email, String password) throws InterruptedException {
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		loginBtn.click();
	}
	
	public void signUp(String email, String name) {
		signUpName.sendKeys(name);
		signUpEmail.sendKeys(email);
		signUpBtn.click();
		
		signUpPage = new SignUp(driver);
	}
}
