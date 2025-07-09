package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static components.BaseTest.*;

public class CartPage {

	public CartPage(){
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - Static Page Objects
	@FindBy(id="cart_info")
	public WebElement cartTable;
	
	@FindBy(id="address_delivery")
	public WebElement deliveryAddressInfo;
	
	@FindBy(id="address_invoice")
	public WebElement billingAddressInfo;
	
	@FindBy(css="a[class='btn btn-default check_out']")
	public WebElement proceedToCheckout;
	
	@FindBy(css="div[class='modal-content']")
	public WebElement modalBody;
	
	@FindBy(css="textarea[name='message']")
	public WebElement orderComment;
	
	@FindBy(css="a[href='/payment']")
	public WebElement placeOrderBtn;
	
	@FindBy(xpath="//tbody/tr[last()]//p")
	public WebElement totalBill;
	
	@FindBy(xpath="//li[contains(text(), 'Shopping Cart')]")
	public WebElement shoppingCart;
	
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

	//For traversing child nodes in cart table xpath locators
	public By cartDescription = By.xpath(".//td[@class='cart_description']//a");
	public By cartPrice = By.xpath(".//td[@class='cart_price']//p");
	public By cartQuantity = By.xpath(".//td[@class='cart_quantity']//button");
	public By cartTotal = By.xpath(".//td[@class='cart_total']//p");
}
