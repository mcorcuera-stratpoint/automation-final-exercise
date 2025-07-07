package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCases {
	WebDriver driver;
	
	public TestCases(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(xpath="//b[contains(text(), 'Test Cases')]")
	private WebElement testCasesHeading;
		
	//Getters
	public WebElement getTestCasesHeading() {
		return testCasesHeading;
	}
	
}
