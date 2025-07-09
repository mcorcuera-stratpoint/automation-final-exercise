package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static components.BaseTest.*;

public class DirectoryPage {
	
	public DirectoryPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="a[href='/']")
	public WebElement home;

	@FindBy(css="a[href='/products']")
	public WebElement products;

	@FindBy(css="a[href='/view_cart']")
	public WebElement cart;

	@FindBy(css="a[href='/login']")
	public WebElement signup;

	@FindBy(css="a[href='/test_cases']")
	public WebElement testCases;

	@FindBy(css="a[href='/contact_us']")
	public WebElement contact;

	@FindBy(css="input[type='email']")
	public WebElement subscribeEmail;
	
	@FindBy(css="button[id='subscribe']")
	public WebElement subscribeBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Subscription')]")
	public WebElement subscriptionHeading;
	
	@FindBy(xpath="//div[@class='alert-success alert' and contains(text(), 'You have been successfully subscribed!')]")
	public WebElement subscriptionMsg;

}
