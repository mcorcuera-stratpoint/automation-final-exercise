package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.Directory;
import static utilities.CommonFunctions.*;
import static components.BaseTest.cart;


public class CartStepDef {
	
    //    Then I verify that the product is removed from the cart
    @When("^I click the X button of (.+)$")
    public void I_click_the_X_button_of_product(String product) throws InterruptedException {
    	scrollToElement(Directory.cartPage.getCartTable());
    	Directory.cartPage.removeProductFromCart(product);

	}    
    
    @And("^I verify that (.+) is removed from the cart")
    public void I_verify_that_product_is_removed_from_the_cart(String product) {
    	scrollToElement(Directory.cartPage.getCartTable());
    	Assert.assertFalse(Directory.cartPage.isProductPresent(product));
    }
	
    @And("I verify that cart page is displayed")
    public void I_verify_that_cart_page_is_displayed() {
    	waitForElement(Directory.cartPage.getShoppingCart());
    	Assert.assertTrue(Directory.cartPage.getShoppingCart().isDisplayed());
    }
    
    @Then("I verify that products are added to cart")
    public void I_verify_that_products_are_added_to_cart() {
    	
    	//Gets all table rows of cart page
    	List<WebElement> cartItems = Directory.cartPage.getCartItems();
    	
    	scrollToElement(Directory.cartPage.getCartTable());
    	
		for(WebElement items : cartItems) {
			String itemName = items.findElement(Directory.cartPage.getCartDescription()).getText();
			
			//Checks if item name exists in the JSON
			Assert.assertTrue(cart.has(itemName));
		}
    }
    
    @And("I empty the cart")
    public void I_empty_the_cart() throws InterruptedException {
    	//Gets all table rows of cart page
    	List<WebElement> cartItems = Directory.cartPage.getCartItems();
    	
    	scrollToElement(Directory.cartPage.getCartTable());

		for(WebElement item : cartItems) {
			String itemName = item.findElement(Directory.cartPage.getCartDescription()).getText();
			
			scrollToElement(item);
	    	Directory.cartPage.removeProductFromCart(itemName);
		}
    }
    
    @When("I click proceed to checkout")
    public void I_click_proceed_to_checkout() {
    	waitForElement(Directory.cartPage.getProceedToCheckout());
    	Directory.cartPage.getProceedToCheckout().click();
    }
    
    @And("I click register or login")
    public void I_click_register_login() {
    	Directory.cartPage.clickModalRegisterLogin();
    }
    
    @And("I verify address details")
    public void I_verify_address_details() {
    	List<WebElement> deliveryAddress = Directory.cartPage.getAddressElements("Delivery Address");
    	List<WebElement> billingAddress = Directory.cartPage.getAddressElements("Billing Address");
    	List<String> expectedAddressLines = new ArrayList<>();
    	int deliverySize = deliveryAddress.size();
    	int billingSize = billingAddress.size();
    	
    	//Get expected values from cache
    	expectedAddressLines.add("Address Lines");
    	expectedAddressLines.add(getCache("Title") + " " + getCache("FirstName") + " " + getCache("LastName"));
    	expectedAddressLines.add(getCache("Company"));
    	expectedAddressLines.add(getCache("Address1"));
    	expectedAddressLines.add(getCache("Address2"));
    	expectedAddressLines.add(getCache("City") + " " + getCache("State") + " " + getCache("ZipCode"));
    	expectedAddressLines.add(getCache("Country"));
    	expectedAddressLines.add(getCache("MobileNumber"));
    	
    	Assert.assertEquals(deliverySize, billingSize);
    	
    	//Verify delivery and billing address details
    	for(int i = 0; i<deliverySize; i++) {
    		
    		//Skips heading element
    		if(i == (0)) {
    			continue;
    		}

    		Assert.assertEquals(expectedAddressLines.get(i), deliveryAddress.get(i).getText());
    		Assert.assertEquals(expectedAddressLines.get(i), billingAddress.get(i).getText());  		 
    	}
    }
    
    @And("I verify that cart details are correct")
    public void I_verify_that_cart_details_are_correct() {
    	//Initialize total bill
    	int bill = 0;
    	
    	//Gets all table rows of cart and store as list
    	List<WebElement> cartItems = Directory.cartPage.getCartItems();
    	
    	scrollToElement(Directory.cartPage.getCartTable());
    	
    	//Verify cart details
		for(WebElement items : cartItems) {				
				String itemName = items.findElement(Directory.cartPage.getCartDescription()).getText();
				
				//Actual values from cart page
				String actualItemPrice = items.findElement(Directory.cartPage.getCartPrice()).getText();
				String actualItemQuantity = items.findElement(Directory.cartPage.getCartQuantity()).getText();
				String actualTotalPrice = items.findElement(Directory.cartPage.getCartTotal()).getText();
					
				//Expected values from JSON
				JSONObject record  = (JSONObject) cart.get(itemName);
				String expectedItemPrice = record.getString("Price");
				String expectedItemQuantity = String.valueOf(record.getInt("Quantity"));
				String expectedTotalPrice = "Rs. " + record.getInt("Total");
				bill = bill + record.getInt("Total");
					
				Assert.assertEquals(expectedItemPrice, actualItemPrice);
				Assert.assertEquals(expectedItemQuantity, actualItemQuantity);
				Assert.assertEquals(expectedTotalPrice, actualTotalPrice);				
		}	
		
		//Store bill to cache
		setCache("Bill", String.valueOf(bill));		
    }
    
    @And("I verify order details")
    public void I_verify_order_details() {
    	//Invoke existing step definition
    	I_verify_that_cart_details_are_correct();
    	scrollToElement(Directory.cartPage.getTotalBill());
    	
    	//Verify total amount
    	String expectedBill = "Rs. " + getCache("Bill");
    	String actualBill = Directory.cartPage.getTotalBill().getText();
    	
    	Assert.assertEquals(expectedBill, actualBill);
    }
    
    @And("^I add a (.+) to the order$")
    public void I_add_a_comment_to_the_order(String comment) {
    	scrollToElement(Directory.cartPage.getOrderComment());
    	Directory.cartPage.getOrderComment().sendKeys(comment);
    }
    
    @And("I click place order")
    public void I_click_place_order() {
    	Directory.cartPage.clickPlaceOrder();
    }
 
}
