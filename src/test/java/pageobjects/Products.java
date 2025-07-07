package pageobjects;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static utilities.CommonFunctions.*;
import static components.BaseTest.cart;
import static pageobjects.Directory.*;


import java.util.List;

public class Products {
	WebDriver driver;
	By relatedProductName = By.xpath(".//p");
	By relatedSearchPath = By.xpath("//div[@class='productinfo text-center']");
	
	public Products(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="search_product")
	private WebElement searchText;
	
	@FindBy(id="quantity")
	private WebElement detailQuantity;
	
	@FindBy(id="submit_search")
	private WebElement searchBtn;
	
	@FindBy(id="name")
	private WebElement reviewName;
	
	@FindBy(id="email")
	private WebElement reviewEmail;
	
	@FindBy(id="review")
	private WebElement reviewMessage;
	
	@FindBy(id="button-review")
	private WebElement reviewSubmitBtn;
	
	@FindBy(css="div[class='modal-content']")
	private WebElement modalBody;
	
	@FindBy(css="a[href='#reviews']")
	private WebElement writeYourReview;
	
	@FindBy(css="button[class='btn btn-default cart']")
	private WebElement detailAddToCartBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'All Products')]")
	private WebElement allProductsHeading;
	
	@FindBy(xpath="//div[@class='features_items']")
	private WebElement productList;
	
	@FindBy(xpath="//div[@class='product-information']")
	private WebElement productInfo;
	
	@FindBy(xpath="//div[@class='product-information']//h2")
	private WebElement detailName;
	
	@FindBy(xpath="//p[contains(text(), 'Category')]")
	private WebElement detailCategory;
	
	@FindBy(xpath="//div[@class='product-information']//span//span")
	private WebElement detailPrice;
	
	@FindBy(xpath="//b[contains(text(), 'Availability')]/parent::p")
	private WebElement detailAvail;
	
	@FindBy(xpath="//b[contains(text(), 'Condition')]/parent::p")
	private WebElement detailCondition;
	
	@FindBy(xpath="//b[contains(text(), 'Brand')]/parent::p")
	private WebElement detailBrand;
	
	@FindBy(xpath="//h2[contains(text(), 'Searched Products')]")
	private WebElement searchedProductsHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'Category')]")
	private WebElement categoryHeading;
	
	@FindBy(xpath="//h2[contains(text(), 'Brands')]")
	private WebElement brandHeading;
	
	@FindBy(xpath="//span[contains(text(), 'Thank you for your review.')]")
	private WebElement reviewSuccess;
	
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
	
	//Getters
	public WebElement getAllProductsHeading() {
		return allProductsHeading;
	}
	
	public WebElement getProductList() {
		return productList;
	}
	
	public WebElement getProductInfo() {
		return productInfo;
	}
		
	public WebElement getDetailName() {
		return detailName;
	}

	public WebElement getDetailCategory() {
		return detailCategory;
	}

	public WebElement getDetailPrice() {
		return detailPrice;
	}

	public WebElement getDetailAvail() {
		return detailAvail;
	}

	public WebElement getDetailCondition() {
		return detailCondition;
	}

	public WebElement getDetailBrand() {
		return detailBrand;
	}
	
	public WebElement getSearchText() {
		return searchText;
	}
	
	public WebElement getSearchButton() {
		return searchBtn;
	}
	
	public WebElement getSearchedProductsHeading() {
		return searchedProductsHeading;
	}
	
	public By getRelatedProductName() {
		return relatedProductName;
	}
	
	public WebElement getModalContinue() {
		return modalBody.findElement(By.xpath(".//button[contains(text(), 'Continue Shopping')]"));
	}
	
	public WebElement getModalViewCart() {
		return modalBody.findElement(By.xpath(".//a[@href='/view_cart']"));
	}
	
	public WebElement getDetailQuantity() {
		return detailQuantity;
	}

	public WebElement getDetailAddToCartBtn() {
		return detailAddToCartBtn;
	}

	public WebElement getCategoryHeading() {
		return categoryHeading;
	}

	public WebElement getBrandHeading() {
		return brandHeading;
	}
	
	public WebElement getReviewName() {
		return reviewName;
	}

	public WebElement getReviewEmail() {
		return reviewEmail;
	}

	public WebElement getReviewMessage() {
		return reviewMessage;
	}

	public WebElement getReviewSubmitBtn() {
		return reviewSubmitBtn;
	}

	public WebElement getReviewSuccess() {
		return reviewSuccess;
	}

	public WebElement getWriteYourReview() {
		return writeYourReview;
	}

	public List<WebElement> getAvailableProducts(){
		return driver.findElements(By.cssSelector("div[class='product-image-wrapper']"));
	}

	//Page methods
	public void viewCart() {
    	waitForElement(getModalViewCart());
    	getModalViewCart().click();
    	
    	cartPage = new Cart(driver);
	}
	
	public void addToExpectedCartItems(String productName) {
		int finalQuantity = 1;
		int variableQuantity = 1;
		String price = "";
		
		//Gets variable quantity and price from current page
		if(driver.getCurrentUrl().contains("product_details")) {
			variableQuantity = Integer.valueOf(detailQuantity.getAttribute("value"));
			price = detailPrice.getText();
		}
		else {
			price = viewProductPrice(productName).getText();			
		}
		
		//If JSON already has the product, adjust quantity
		if(cart.has(productName)) {
			JSONObject record = (JSONObject) cart.get(productName);			
			finalQuantity = (int) record.get("Quantity") + variableQuantity;

			//remove key from JSON
			cart.remove(productName);
		}
		else {
			finalQuantity = variableQuantity;
		}
		
		JSONObject orderDetails = new JSONObject();
		orderDetails.put("Price", price);
		orderDetails.put("Quantity", finalQuantity);
		orderDetails.put("Total", Integer.valueOf(price.replaceAll("[^\\d]", "").strip()) * finalQuantity);
		
	  	cart.put(productName,  orderDetails);	
	}
	
	public void goToPanel(String navigation) {
		String[] nav = navigation.split(">");
		String panelType = nav[0].strip();
		String node1 = nav[1].strip();

		if(panelType.equalsIgnoreCase("Category")) {			
			String node2 = nav[2].strip();
			
			scrollToElement(categoryHeading);
			panelTitle(node1).click();
			waitForElement(categoryItem(node2));
			categoryItem(node2).click();	
		}
		else if(panelType.equalsIgnoreCase("Brands")) {
			scrollToElement(brandHeading);
			brandItem(node1).click();
		}
	}
}
