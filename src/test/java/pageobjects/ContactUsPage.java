package pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static components.BaseTest.*;

import static utilities.CommonFunctions.*;

public class ContactUsPage {

	public ContactUsPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[data-qa='name']")
	public WebElement name;
	
	@FindBy(css="input[data-qa='email']")
	public WebElement email;
	
	@FindBy(css="input[data-qa='subject']")
	public WebElement subject;
	
	@FindBy(css="textarea[data-qa='message']")
	public WebElement message;
	
	@FindBy(css="input[name='upload_file']")
	public WebElement chooseFileBtn;
	
	@FindBy(css="input[data-qa='submit-button']")
	public WebElement submitBtn;

	@FindBy(xpath="//h2[contains(text(), 'Get In Touch')]")
	public WebElement getInTouchHeading;
	
	@FindBy(xpath="//div[@class='status alert alert-success' and contains(text(), 'Success! Your details have been submitted successfully')]")
	public WebElement successMsg;
}


