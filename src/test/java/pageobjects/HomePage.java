package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static components.BaseTest.*;

public class HomePage {
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - Static Page Objects
	@FindBy(css="a[id='scrollUp']")
	public WebElement scrollUpBtn;
	
	@FindBy(css="div[id='slider-carousel']")
	public WebElement sliderCarousel;
	
	@FindBy(css="a[href='/logout']")
	public WebElement logoutBtn;
	
	@FindBy(css="a[data-qa='continue-button']")
	public WebElement continueBtn;
	
	@FindBy(css="a[class='left recommended-item-control']")
	public WebElement leftRecomItemControl;
	
	@FindBy(xpath="//a[contains(text(), 'Delete Account')]")
	public WebElement deleteAccountBtn;
	
	@FindBy(xpath="//div[@id='recommended-item-carousel']//div[@class='carousel-inner']")
	public WebElement recomItemCarousel;
	
	@FindBy(xpath="//b[contains(text(), 'Account Deleted!')]")
	public WebElement accountDeleted;
	
	@FindBy(xpath="//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]")
	public WebElement fullFledgedText;
	
	@FindBy(xpath="//h2[contains(text(), 'recommended items')]")
	public WebElement recomItemsHeading;
	
	//Dynamic Page Objects
	public WebElement loggedInAsUser (String username) {
		return driver.findElement(By.xpath(String.format("//a[contains(text(), 'Logged in as')]//following-sibling::b[contains(text(), %s)]", username)));
	}
	
	public WebElement recommendedItem (String product) {
		return driver.findElement(By.xpath(String.format("//div[@id='recommended-item-carousel']//p[contains(text(), '%s')]//parent::div", product)));
	}
}
