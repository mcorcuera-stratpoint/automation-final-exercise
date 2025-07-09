package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static components.BaseTest.*;

public class TestCasesPage {
	
	public TestCasesPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(xpath="//b[contains(text(), 'Test Cases')]")
	public WebElement testCasesHeading;
}
