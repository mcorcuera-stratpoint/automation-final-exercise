package stepdefinitions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.Directory;
import static utilities.CommonFunctions.*;

import java.text.ParseException;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsStepDef {
	
    @When("^I hover over a product (.+)$")
    public void I_hover_over_a_product_name(String productName) throws InterruptedException {    	
    	scrollToElement(Directory.productsPage.viewProductName(productName));
    	hoverToElement(Directory.productsPage.viewProductName(productName));
    }
    
    @Then("I click continue shopping button")
    public void I_click_continue_shopping_button() {
    	waitForElement(Directory.productsPage.getModalContinue());
    	Directory.productsPage.getModalContinue().click();
    }
    
	@And("I verify that all products page is visible")
	public void I_verify_that_all_products_page_is_visible() {
		waitForElement(Directory.productsPage.getAllProductsHeading());
		Assert.assertTrue(Directory.productsPage.getAllProductsHeading().isDisplayed());
	}
	
	@And("I verify that the products list is visible")
	public void I_verify_that_the_products_list_is_visible() {
		waitForElement(Directory.productsPage.getProductList());
		Assert.assertTrue(Directory.productsPage.getProductList().isDisplayed());
	}
	
	@And("I verify that product detail page is visible")
	public void I_verify_that_product_detail_page_is_visible() {
		waitForElement(Directory.productsPage.getProductInfo());
		Assert.assertTrue(Directory.productsPage.getProductInfo().isDisplayed());
	}
	
	@And("^I click on view product of the (.+) product$")
	public void I_click_on_view_product_of_a_product(String ordinal) throws ParseException {		
		int convert = ordinalStringToInteger(ordinal);
		scrollToElement(Directory.productsPage.viewNthProduct(String.valueOf(convert)));
		Directory.productsPage.viewNthProduct(String.valueOf(convert)).click();
	}
	
	@And("I verify that viewed product information is visible")
	public void I_verify_that_viewed_product_information_is_visible() {
		waitForElement(Directory.productsPage.getDetailName());
		Assert.assertTrue(Directory.productsPage.getDetailName().isDisplayed());
		Assert.assertTrue(Directory.productsPage.getDetailCategory().isDisplayed());
		Assert.assertTrue(Directory.productsPage.getDetailPrice().isDisplayed());
		Assert.assertTrue(Directory.productsPage.getDetailAvail().isDisplayed());
		Assert.assertTrue(Directory.productsPage.getDetailCondition().isDisplayed());
		Assert.assertTrue(Directory.productsPage.getDetailBrand().isDisplayed());
	}
	
	@And("^I search for a product (.+)$")
	public void I_search_for_a_product_name(String product) {
		waitForElement(Directory.productsPage.getSearchText());
		Directory.productsPage.getSearchText().sendKeys(product);
		Directory.productsPage.getSearchButton().click();
	}
	
	@And("I verify that searched products heading is visible")
	public void I_verify_that_searched_products_heading_is_visible() {
		waitForElement(Directory.productsPage.getSearchedProductsHeading());
		scrollToElement(Directory.productsPage.getSearchedProductsHeading());
		Assert.assertTrue(Directory.productsPage.getSearchedProductsHeading().isDisplayed());
	}
	
	@And("^I verify that related products to (.+) are visible$")
	public void I_verify_that_related_products_to_name_are_visible(String productName) {
		scrollToElement(Directory.productsPage.getSearchedProductsHeading());
		List<WebElement> relatedSearch = Directory.productsPage.getAvailableProducts();
		
		Assert.assertTrue(relatedSearch.size()>0);
		
		for(WebElement element : relatedSearch) {
			WebElement name = element.findElement(Directory.productsPage.getRelatedProductName());
			
			Assert.assertTrue(name.getText().toLowerCase().contains(productName.toLowerCase()));			
		}
	}
	
    @And("^I click add to cart on (.+) overlay$")
    public void i_click_add_to_cart_on_overlay(String productName) {
    	scrollToElement(Directory.productsPage.viewProductName(productName));
    	Directory.productsPage.addToExpectedCartItems(productName);
    	Directory.productsPage.overlayAddToCart(productName).click();
    }
    
    @And("I click add to cart via product detail")
    public void i_click_add_to_cart_via_product_detail() {
    	waitForElement(Directory.productsPage.getDetailAddToCartBtn());
    	String productName = Directory.productsPage.getDetailName().getText();
    			
    	Directory.productsPage.addToExpectedCartItems(productName);
    	Directory.productsPage.getDetailAddToCartBtn().click();
    }
	
    @And("I click view cart")
    public void I_click_view_cart() {
    	Directory.productsPage.viewCart();
    }
    
    @And("^I increase product quantity to (.+)$")
    public void I_increase_product_quantity_to_amount(String amount) {
    	waitForElement(Directory.productsPage.getDetailQuantity());
    	Directory.productsPage.getDetailQuantity().clear();
    	Directory.productsPage.getDetailQuantity().sendKeys(amount);
    }
    
    @And ("I verify that categories are visible")
    public void I_verify_that_categories_are_visible() {
    	scrollToElement(Directory.productsPage.getCategoryHeading());
    	Assert.assertTrue(Directory.productsPage.getCategoryHeading().isDisplayed());
    }
    
    @And ("I verify that brands are visible")
    public void I_verify_that_brands_are_visible() {
    	scrollToElement(Directory.productsPage.getBrandHeading());
    	Assert.assertTrue(Directory.productsPage.getBrandHeading().isDisplayed());
    }
    
    @When("^I navigate to panel (.+)$")
    public void I_navigate_to_panel(String navigation) {
    	Directory.productsPage.goToPanel(navigation);
    }
    
    @Then("^I verify that page and products are displayed for (.+)$")
    public void I_verify_that_page_and_products_are_displayed_for_group(String navigation) {
		List<WebElement> availableProducts = Directory.productsPage.getAvailableProducts();
    	String[] nav = navigation.split(">");
		String productGroup = nav[0].strip();
		String node1 = nav[1].strip();
		String text= "";
		
		if(productGroup.equalsIgnoreCase("Category")) {	
			String node2 = nav[2].strip();
			text = Directory.productsPage.productGroupHeading(node1).getAttribute("textContent");
			Assert.assertEquals(text, (node1 + " - " + node2 + " Products"));
		}
		else if(productGroup.equalsIgnoreCase("Brands")) {
			text = Directory.productsPage.productGroupHeading("Brand").getAttribute("textContent");
			Assert.assertEquals(text, ("Brand - " + node1 + " Products"));				
		}
		
		Assert.assertTrue(availableProducts.size()>0);
    }
    
    @Given("I verify that write your review is visible")
    public void I_verify_that_write_your_review_is_visible() {
    	scrollToElement(Directory.productsPage.getWriteYourReview());
    	Assert.assertTrue(Directory.productsPage.getWriteYourReview().isDisplayed());
    }
    
    @When("^I fill review details with (.+), (.+), and (.+)$")
    public void I_fill_review_details_with_name_email_and_message(String name, String email, String message) {
    	scrollToElement(Directory.productsPage.getReviewName());
    	Directory.productsPage.getReviewName().sendKeys(name);
    	Directory.productsPage.getReviewEmail().sendKeys(email);
    	Directory.productsPage.getReviewMessage().sendKeys(message);
    }
    
    @And("I submit the review form")
    public void I_submit_the_review_form() {
    	waitForElement(Directory.productsPage.getReviewSubmitBtn());
    	Directory.productsPage.getReviewSubmitBtn().click();
    }
    
    @Then("I verify that successful review message is displayed")
    public void I_verify_that_successful_review_message_is_displayed() throws InterruptedException {
		waitForElement(Directory.productsPage.getReviewSuccess());
		Assert.assertTrue(Directory.productsPage.getReviewSuccess().isDisplayed());
    }
    
    @And("I add all related products to cart")
    public void I_add_all_related_products_to_cart() throws InterruptedException {
		scrollToElement(Directory.productsPage.getSearchedProductsHeading());
		List<WebElement> relatedSearch = Directory.productsPage.getAvailableProducts();

		for(WebElement products : relatedSearch) {
			String productName = products.findElement(Directory.productsPage.getRelatedProductName()).getText();
	    	I_hover_over_a_product_name(productName);
	    	i_click_add_to_cart_on_overlay(productName);
	    	I_click_continue_shopping_button();
		}
    }
}
