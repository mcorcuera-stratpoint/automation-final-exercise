package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;
import static utilities.CommonFunctions.*;
import static pageobjects.Directory.*;
import static components.BaseTest.cart;

public class Cart {
	WebDriver driver;
	By cartDescription = By.xpath(".//td[@class='cart_description']//a");
	By cartPrice = By.xpath(".//td[@class='cart_price']//p");
	By cartQuantity = By.xpath(".//td[@class='cart_quantity']//button");
	By cartTotal = By.xpath(".//td[@class='cart_total']//p");

	public Cart(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="cart_info")
	private WebElement cartTable;
	
	@FindBy(id="address_delivery")
	private WebElement deliveryAddressInfo;
	
	@FindBy(id="address_invoice")
	private WebElement billingAddressInfo;
	
	@FindBy(css="a[class='btn btn-default check_out']")
	private WebElement proceedToCheckout;
	
	@FindBy(css="div[class='modal-content']")
	private WebElement modalBody;
	
	@FindBy(css="textarea[name='message']")
	private WebElement orderComment;
	
	@FindBy(css="a[href='/payment']")
	private WebElement placeOrderBtn;
	
	@FindBy(xpath="//tbody/tr[last()]//p")
	private WebElement totalBill;
	
	@FindBy(xpath="//li[contains(text(), 'Shopping Cart')]")
	private WebElement shoppingCart;
	
	public List<WebElement> getCartItems(){
		List<WebElement> cartItems = cartTable.findElements(By.xpath(".//tbody/tr[contains(@id, 'product')]"));
		return cartItems;
	}
	
	//Dynamic Page Objects
	public WebElement getCartDelete(String product) {
		return driver.findElement(By.xpath(String.format("//td[@class='cart_description']//a[contains(text(), '%s')]" + "/parent::h4/parent::td/parent::tr//td[@class='cart_delete']//a", product)));
	}
	
	public List<WebElement> getAddressElements(String addressType){
		List<WebElement> addressElements = null;
		
		if(addressType.equalsIgnoreCase("Delivery Address")) {
			addressElements = deliveryAddressInfo.findElements(By.xpath(".//li"));
		}
		else if(addressType.equalsIgnoreCase("Billing Address")) {
			addressElements = billingAddressInfo.findElements(By.xpath(".//li"));			
		}
		
		return addressElements;
	}
		
	//Getters
	public WebElement getCartTable() {
		return cartTable;
	}
	
	public WebElement getShoppingCart() {
		return shoppingCart;
	}

	public By getCartDescription() {
		return cartDescription;
	}

	public By getCartPrice() {
		return cartPrice;
	}

	public By getCartQuantity() {
		return cartQuantity;
	}

	public By getCartTotal() {
		return cartTotal;
	}
	
	public WebElement getProceedToCheckout() {
		return proceedToCheckout;
	}
	
	public WebElement getModalRegisterLogin() {
		return modalBody.findElement(By.xpath(".//a[@href='/login']"));
	}
	
	public WebElement getTotalBill() {
		return totalBill;
	}
	
	public WebElement getOrderComment() {
		return orderComment;
	}
	
	public WebElement getPlaceOrderButton() {
		return placeOrderBtn;
	}
		
	//Page methods
	public void removeProductFromCart(String product) throws InterruptedException {   	
		//Remove key from JSON
		cart.remove(product);
		
		//Click X button
		getCartDelete(product).click();
		
    	Thread.sleep(Duration.ofSeconds(2));
	}
	
	public boolean isProductPresent(String product) {
		List<WebElement> search = driver.findElements(By.xpath(String.format("//td[@class='cart_description']//a[contains(text(), '%s')]", product)));
		
		if(search.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickModalRegisterLogin() {
    	waitForElement(Directory.cartPage.getModalRegisterLogin());
    	Directory.cartPage.getModalRegisterLogin().click();   	
    	loginPage = new Login(driver);
	}
	
	public void clickPlaceOrder() {
    	scrollToElement(Directory.cartPage.getPlaceOrderButton());
    	Directory.cartPage.getPlaceOrderButton().click();
    	paymentPage = new Payment(driver);
	}

}
