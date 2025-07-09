package stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import static utilities.CommonFunctions.*;
import pageactions.CartActions;

public class CartStepDef {
	CartActions cartActions = new CartActions();

    @When("^I click the X button of (.+)$")
    public void I_click_the_X_button_of_product(String product) throws InterruptedException {
    	scrollToElement(cartActions.cartPage.cartTable);
    	cartActions.removeProductFromCart(product);
	}
	
    @And("I verify that cart page is displayed")
    public void I_verify_that_cart_page_is_displayed() {
    	waitForElement(cartActions.cartPage.shoppingCart);
    	Assert.assertTrue(cartActions.cartPage.shoppingCart.isDisplayed());
    }

    @Then("I verify that products are added to cart")
    public void I_verify_that_products_are_added_to_cart() {
    	scrollToElement(cartActions.cartPage.cartTable);
		Assert.assertTrue(cartActions.checkItemsInCart());
    }
    
    @And("I empty the cart")
    public void I_empty_the_cart() throws InterruptedException {
    	scrollToElement(cartActions.cartPage.cartTable);
		cartActions.emptyCart();
    }

	@And("^I verify that (.+) is removed from the cart")
	public void I_verify_that_product_is_removed_from_the_cart(String product) {
		scrollToElement(cartActions.cartPage.cartTable);
		Assert.assertFalse(cartActions.isProductPresentInCart(product));
	}
    
    @When("I click proceed to checkout")
    public void I_click_proceed_to_checkout() {
    	waitForElement(cartActions.cartPage.proceedToCheckout);
    	cartActions.cartPage.proceedToCheckout.click();
    }
    
    @And("I click register or login")
    public void I_click_register_login() {
		cartActions.clickModalRegisterLogin();
    }
    
    @And("I verify address details")
    public void I_verify_address_details() {
		Assert.assertTrue(cartActions.checkAddressType("Delivery Address"));
		Assert.assertTrue(cartActions.checkAddressType("Billing Address"));
    }
    
    @And("I verify that cart details are correct")
    public void I_verify_that_cart_details_are_correct() {
		scrollToElement(cartActions.cartPage.cartTable);
    	Assert.assertTrue(cartActions.checkCartDetails());
    }
    
    @And("I verify order details")
    public void I_verify_order_details() {
    	//Invoke existing step definition
    	I_verify_that_cart_details_are_correct();
    	scrollToElement(cartActions.cartPage.totalBill);
    	Assert.assertEquals("Rs. " + getCache("Bill"), cartActions.cartPage.totalBill.getText());
    }
    
    @And("^I add a (.+) to the order$")
    public void I_add_a_comment_to_the_order(String comment) {
    	scrollToElement(cartActions.cartPage.orderComment);
    	cartActions.cartPage.orderComment.sendKeys(comment);
    }
    
    @And("I click place order")
    public void I_click_place_order() {
		cartActions.clickPlaceOrder();
    }
}
