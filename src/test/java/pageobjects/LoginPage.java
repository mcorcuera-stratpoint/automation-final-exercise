package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static pageobjects.DirectoryPage.*;
import static components.BaseTest.*;

public class LoginPage {

	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[data-qa='login-email']")
	public WebElement loginEmail;
	
	@FindBy(css="input[data-qa='login-password']")
	public WebElement loginPassword;
	
	@FindBy(css="input[data-qa='signup-email']")
	public WebElement signUpEmail;
	
	@FindBy(css="input[data-qa='signup-name']")
	public WebElement signUpName;
	
	@FindBy(css="button[data-qa='login-button']")
	public WebElement loginBtn;
	
	@FindBy(css="button[data-qa='signup-button']")
	public WebElement signUpBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Login to your account')]")
	public WebElement loginHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'New User Signup!')]")
	public WebElement newUserHeading;
	
	@FindBy(xpath="//p[contains(text(), 'Your email or password is incorrect!')]")
	public WebElement incorrectCredentials;
	
	@FindBy(xpath="//p[contains(text(), 'Email Address already exist!')]")
	public WebElement existingEmailMsg;
}
