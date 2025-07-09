package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static components.BaseTest.*;

public class PaymentPage {

	public PaymentPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="submit")
	public WebElement payAndConfirmOrder;
	
	@FindBy(css="h2[class='heading']")
	public WebElement paymentHeading;
	
	@FindBy(css="input[name='name_on_card']")
	public WebElement nameOnCard;
	
	@FindBy(css="input[name='card_number']")
	public WebElement cardNumber;
	
	@FindBy(css="input[name='cvc']")
	public WebElement cvc;
	
	@FindBy(css="input[name='expiry_month']")
	public WebElement expiryMonth;
	
	@FindBy(css="input[name='expiry_year']")
	public WebElement expiryYear;
	
	@FindBy(xpath="//*[contains(text(), 'Your order has been')]")
	public WebElement successOrderMsg;
	
	@FindBy(xpath="//a[contains(@href, 'download_invoice')]")
	public WebElement downloadInvoice;
}
