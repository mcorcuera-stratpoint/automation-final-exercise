package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Payment {
	WebDriver driver;

	public Payment(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="submit")
	private WebElement payAndConfirmOrder;
	
	@FindBy(css="h2[class='heading']")
	private WebElement paymentHeading;
	
	@FindBy(css="input[name='name_on_card']")
	private WebElement nameOnCard;
	
	@FindBy(css="input[name='card_number']")
	private WebElement cardNumber;
	
	@FindBy(css="input[name='cvc']")
	private WebElement cvc;
	
	@FindBy(css="input[name='expiry_month']")
	private WebElement expiryMonth;
	
	@FindBy(css="input[name='expiry_year']")
	private WebElement expiryYear;
	
	@FindBy(xpath="//*[contains(text(), 'Your order has been')]")
	private WebElement successOrderMsg;
	
	@FindBy(xpath="//a[contains(@href, 'download_invoice')]")
	private WebElement downloadInvoice;
	
	//Getters
	public WebElement getPaymentHeading() {
		return paymentHeading;
	}

	public WebElement getPayAndConfirmOrder() {
		return payAndConfirmOrder;
	}

	public WebElement getNameOnCard() {
		return nameOnCard;
	}

	public WebElement getCardNumber() {
		return cardNumber;
	}

	public WebElement getCvc() {
		return cvc;
	}

	public WebElement getExpiryMonth() {
		return expiryMonth;
	}

	public WebElement getExpiryYear() {
		return expiryYear;
	}
	
	public WebElement getSuccessOrderMessage() {
		return successOrderMsg;
	}
	
	public WebElement getDownloadInvoice() {
		return downloadInvoice;
	}
	
}
