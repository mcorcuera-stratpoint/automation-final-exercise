package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utilities.CommonFunctions.*;

public class ContactUs {
	WebDriver driver;
	
	public ContactUs(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="input[data-qa='name']")
	private WebElement name;
	
	@FindBy(css="input[data-qa='email']")
	private WebElement email;
	
	@FindBy(css="input[data-qa='subject']")
	private WebElement subject;
	
	@FindBy(css="textarea[data-qa='message']")
	private WebElement message;
	
	@FindBy(css="input[name='upload_file']")
	private WebElement chooseFileBtn;
	
	@FindBy(css="input[data-qa='submit-button']")
	private WebElement submitBtn;

	@FindBy(xpath="//h2[contains(text(), 'Get In Touch')]")
	private WebElement getInTouchHeading;
	
	@FindBy(xpath="//div[@class='status alert alert-success' and contains(text(), 'Success! Your details have been submitted successfully')]")
	private WebElement successMsg;
		
	//Getters
	public WebElement getInTouchHeading() {
		return getInTouchHeading;
	}
	
	public WebElement getSuccessMsg() {
		return successMsg;
	}
	
	//Page methods
	public void fillUpContactUsTextFields(String iName, String iEmail, String iSubject, String iMessage) {
		name.sendKeys(iName);
		email.sendKeys(iEmail);
		subject.sendKeys(iSubject);
		message.sendKeys(iMessage);
	}
	
	public void chooseFile(String fileName) {
		String path = System.getProperty("user.dir") + "//src//main/java//resources//" + fileName;
		scrollToElement(chooseFileBtn);
		chooseFileBtn.sendKeys(path);
		
	}
	
	public void submitContactUsForm() {
		scrollToElement(submitBtn);
		submitBtn.click();
	}
}


