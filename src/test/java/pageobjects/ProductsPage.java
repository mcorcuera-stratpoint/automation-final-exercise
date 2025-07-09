package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static components.BaseTest.*;

public class ProductsPage {
	public ProductsPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - Static Page Objects
	@FindBy(id="search_product")
	public WebElement searchText;
	
	@FindBy(id="quantity")
	public WebElement detailQuantity;
	
	@FindBy(id="submit_search")
	public WebElement searchBtn;
	
	@FindBy(id="name")
	public WebElement reviewName;
	
	@FindBy(id="email")
	public WebElement reviewEmail;
	
	@FindBy(id="review")
	public WebElement reviewMessage;
	
	@FindBy(id="button-review")
	public WebElement reviewSubmitBtn;
	
	@FindBy(css="div[class='modal-content']")
	public WebElement modalBody;
	
	@FindBy(css="a[href='#reviews']")
	public WebElement writeYourReview;
	
	@FindBy(css="button[class='btn btn-default cart']")
	public WebElement detailAddToCartBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'All Products')]")
	public WebElement allProductsHeading;
	
	@FindBy(xpath="//div[@class='features_items']")
	public WebElement productList;
	
	@FindBy(xpath="//div[@class='product-information']")
	public WebElement productInfo;
	
	@FindBy(xpath="//div[@class='product-information']//h2")
	public WebElement detailName;
	
	@FindBy(xpath="//p[contains(text(), 'Category')]")
	public WebElement detailCategory;
	
	@FindBy(xpath="//div[@class='product-information']//span//span")
	public WebElement detailPrice;
	
	@FindBy(xpath="//b[contains(text(), 'Availability')]/parent::p")
	public WebElement detailAvail;
	
	@FindBy(xpath="//b[contains(text(), 'Condition')]/parent::p")
	public WebElement detailCondition;
	
	@FindBy(xpath="//b[contains(text(), 'Brand')]/parent::p")
	public WebElement detailBrand;
	
	@FindBy(xpath="//h2[contains(text(), 'Searched Products')]")
	public WebElement searchedProductsHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'Category')]")
	public WebElement categoryHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'Brands')]")
	public WebElement brandHeading;
	
	@FindBy(xpath="//span[contains(text(), 'Thank you for your review.')]")
	public WebElement reviewSuccess;
	
	//Dynamic Page Objects
	public WebElement viewNthProduct (String n) {
		return driver.findElement(By.cssSelector(String.format("a[href='/product_details/%s']", n)));
	}
	
	public WebElement viewProductName (String productName) {
		return driver.findElement(By.xpath(String.format("//div[@class='productinfo text-center']//p[contains(text(), '%s')]//parent::div", productName)));
	}
	
	public WebElement viewProductPrice (String productName) {
		return driver.findElement(By.xpath(String.format("//div[@class='productinfo text-center']//p[contains(text(), '%s')]/preceding-sibling::h2", productName)));
	}
	
	public WebElement overlayAddToCart (String productName) {
		return driver.findElement(By.xpath(String.format("//div[@class='product-overlay']//p[contains(text(), '%s')]//following-sibling::a", productName)));
	}
		
	public WebElement panelTitle (String title) {
		return driver.findElement(By.xpath(String.format("//div[@id='%s']/preceding-sibling::div/h4/a", title)));
	}
	
	public WebElement categoryItem (String item) {
		return driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]", item)));
	}
	
	public WebElement brandItem(String item) {
		return driver.findElement(By.xpath(String.format("//a[contains(@href, '%s')]", item)));
	}
	
	public WebElement productGroupHeading (String title) {
		return driver.findElement(By.xpath(String.format("//h2[contains(text(), '%s -')]", title)));
	}
	
	public WebElement getModalContinue() {
		return modalBody.findElement(By.xpath(".//button[contains(text(), 'Continue Shopping')]"));
	}
	
	public WebElement getModalViewCart() {
		return modalBody.findElement(By.xpath(".//a[@href='/view_cart']"));
	}

	public List<WebElement> getAvailableProducts(){
		return driver.findElements(By.cssSelector("div[class='product-image-wrapper']"));
	}

	//For traversing child nodes in product xpath locators
	public By relatedProductName = By.xpath(".//p");
	public By relatedSearchPath = By.xpath("//div[@class='productinfo text-center']");
}
