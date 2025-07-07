package pageobjects;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static components.BaseTest.cart;
import static utilities.CommonFunctions.*;

public class Home{
	WebDriver driver;
	
	public Home(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css="a[id='scrollUp']")
	private WebElement scrollUpBtn;
	
	@FindBy(css="div[id='slider-carousel']")
	private WebElement sliderCarousel;
	
	@FindBy(css="a[href='/logout']")
	private WebElement logout;
	
	@FindBy(css="a[data-qa='continue-button']")
	private WebElement continueBtn;
	
	@FindBy(css="a[class='left recommended-item-control']")
	private WebElement leftRecomItemControl;
	
	@FindBy(xpath="//a[contains(text(), 'Delete Account')]")
	private WebElement deleteAccount;
	
	@FindBy(xpath="//div[@id='recommended-item-carousel']//div[@class='carousel-inner']")
	private WebElement recomItemCarousel;
	
	@FindBy(xpath="//b[contains(text(), 'Account Deleted!')]")
	private WebElement accountDeleted;
	
	@FindBy(xpath="//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]")
	private WebElement fullFledgedText;
	
	@FindBy(xpath="//h2[contains(text(), 'recommended items')]")
	private WebElement recomItemsHeading;
	
	//Dynamic Page Objects
	public WebElement loggedInAsUser (String username) {
		return driver.findElement(By.xpath(String.format("//a[contains(text(), 'Logged in as')]//following-sibling::b[contains(text(), %s)]", username)));
	}
	
	public WebElement recommendedItem (String product) {
		return driver.findElement(By.xpath(String.format("//div[@id='recommended-item-carousel']//p[contains(text(), '%s')]//parent::div", product)));
	}
		
	//Getters
	public WebElement getSliderCarousel() {
		return sliderCarousel;
	}
	
	public WebElement getRecomItemsHeading() {
		return recomItemsHeading;
	}
	
	public WebElement getRecomItemCarousel() {
		return recomItemCarousel;
	}

	public WebElement getAccountDeleted() {
		return accountDeleted;
	}
	
	public WebElement getContinueBtn() {
		return continueBtn;
	}
	
	public WebElement getDeleteAccountBtn() {
		return deleteAccount;
	}	
	
	public WebElement getLogoutBtn() {
		return logout;
	}	
	
	public WebElement getScrollUpButton() {
		return scrollUpBtn;
	}	
	
	public WebElement getFullFledgedText() {
		return fullFledgedText;
	}
	
	//Page methods
	public void addToCartRecommendedItem(String product) throws InterruptedException {
		hoverToElement(recomItemCarousel);
				
		if(!recommendedItem(product).isDisplayed()) {
			leftRecomItemControl.click();
			waitForElement(recommendedItem(product));
		}
		
		hoverToElement(recommendedItem(product));
		
		int recomItemQuantity = 1;
		String recomItemPrice = recommendedItem(product).findElement(By.xpath(".//h2")).getText();
		
		//Click add to cart
		recommendedItem(product).findElement(By.xpath(".//a")).click();	
		
		//If JSON already has the product, adjust quantity
		if(cart.has(product)) {
			JSONObject record = (JSONObject) cart.get(product);			
			recomItemQuantity = (int) record.get("Quantity") + 1;

			//remove key from JSON
			cart.remove(product);
		}
		
		//Store to cart JSON
		JSONObject orderDetails = new JSONObject();
		orderDetails.put("Price", recomItemPrice);
		orderDetails.put("Quantity", recomItemQuantity);
		orderDetails.put("Total", Integer.valueOf(recomItemPrice.replaceAll("[^\\d]", "").strip()) * recomItemQuantity);		
	  	cart.put(product,  orderDetails);
	}
}
