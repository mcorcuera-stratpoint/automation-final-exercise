package stepdefinitions;

import org.testng.Assert;
import java.text.ParseException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageactions.ProductActions;
import static utilities.CommonFunctions.*;

public class ProductsStepDef {
	ProductActions productActions = new ProductActions();
    
	@And("I verify that all products page is visible")
	public void I_verify_that_all_products_page_is_visible() {
		waitForElement(productActions.productsPage.allProductsHeading);
		Assert.assertTrue(productActions.productsPage.allProductsHeading.isDisplayed());
	}
	
	@And("I verify that the products list is visible")
	public void I_verify_that_the_products_list_is_visible() {
		waitForElement(productActions.productsPage.productList);
		Assert.assertTrue(productActions.productsPage.productList.isDisplayed());
	}
	
	@And("I verify that product detail page is visible")
	public void I_verify_that_product_detail_page_is_visible() {
		waitForElement(productActions.productsPage.productInfo);
		Assert.assertTrue(productActions.productsPage.productInfo.isDisplayed());
	}
	
	@And("I verify that searched products heading is visible")
	public void I_verify_that_searched_products_heading_is_visible() {
		waitForElement(productActions.productsPage.searchedProductsHeading);
		Assert.assertTrue(productActions.productsPage.searchedProductsHeading.isDisplayed());
	}

	@And ("I verify that categories are visible")
	public void I_verify_that_categories_are_visible() {
		scrollToElement(productActions.productsPage.categoryHeading);
		Assert.assertTrue(productActions.productsPage.categoryHeading.isDisplayed());
	}

	@And ("I verify that brands are visible")
	public void I_verify_that_brands_are_visible() {
		scrollToElement(productActions.productsPage.brandHeading);
		Assert.assertTrue(productActions.productsPage.brandHeading.isDisplayed());
	}

	@Given("I verify that write your review is visible")
	public void I_verify_that_write_your_review_is_visible() {
		scrollToElement(productActions.productsPage.writeYourReview);
		Assert.assertTrue(productActions.productsPage.writeYourReview.isDisplayed());
	}

	@Then("I verify that successful review message is displayed")
	public void I_verify_that_successful_review_message_is_displayed() throws InterruptedException {
		waitForElement(productActions.productsPage.reviewSuccess);
		Assert.assertTrue(productActions.productsPage.reviewSuccess.isDisplayed());
	}

	@And("^I verify that related products to (.+) are visible$")
	public void I_verify_that_related_products_to_name_are_visible(String productName) {
		scrollToElement(productActions.productsPage.searchedProductsHeading);
		productActions.checkRelatedSearchProducts(productName);
	}

	@Then("^I verify that page and products are displayed for (.+)$")
	public void I_verify_that_page_and_products_are_displayed_for_group(String navigation) {
		Assert.assertTrue(productActions.checkAvailableProducts(navigation));
	}

	@And("I verify that viewed product information is visible")
	public void I_verify_that_viewed_product_information_is_visible() {
		waitForElement(productActions.productsPage.detailName);
		Assert.assertTrue(productActions.productsPage.detailName.isDisplayed());
		Assert.assertTrue(productActions.productsPage.detailCategory.isDisplayed());
		Assert.assertTrue(productActions.productsPage.detailPrice.isDisplayed());
		Assert.assertTrue(productActions.productsPage.detailAvail.isDisplayed());
		Assert.assertTrue(productActions.productsPage.detailCondition.isDisplayed());
		Assert.assertTrue(productActions.productsPage.detailBrand.isDisplayed());
	}
	
    @And("^I click add to cart on (.+) overlay$")
    public void i_click_add_to_cart_on_overlay(String productName) {
    	scrollToElement(productActions.productsPage.viewProductName(productName));
		productActions.addToCartOnOverlay(productName);
    }
    
    @And("I click add to cart via product detail")
    public void i_click_add_to_cart_via_product_detail() {
    	waitForElement(productActions.productsPage.detailAddToCartBtn);
		productActions.addToCartViaProductDetail();
    }
	
    @And("I click view cart")
    public void I_click_view_cart() {
		waitForElement(productActions.productsPage.getModalViewCart());
		productActions.viewCart();
    }

	@And("^I search for a product (.+)$")
	public void I_search_for_a_product_name(String product) {
		waitForElement(productActions.productsPage.searchText);
		productActions.searchProduct(product);
	}
    
    @And("^I increase product quantity to (.+)$")
    public void I_increase_product_quantity_to_amount(String amount) {
    	waitForElement(productActions.productsPage.detailQuantity);
		productActions.increaseProductQuantity(amount);
    }
    
    @When("^I navigate to panel (.+)$")
    public void I_navigate_to_panel(String navigation) {
		productActions.goToPanel(navigation);
    }

	@When("^I fill review details with (.+), (.+), and (.+)$")
	public void I_fill_review_details_with_name_email_and_message(String name, String email, String message) {
		scrollToElement(productActions.productsPage.reviewName);
		productActions.fillReviewDetails(name, email, message);
	}

	@And("I submit the review form")
	public void I_submit_the_review_form() {
		waitForElement(productActions.productsPage.reviewSubmitBtn);
		productActions.productsPage.reviewSubmitBtn.click();
	}

	@And("^I click on view product of the (.+) product$")
	public void I_click_on_view_product_of_a_product(String ordinal) throws ParseException {
		scrollToElement(productActions.productsPage.viewNthProduct(String.valueOf(ordinalStringToInteger(ordinal))));
		productActions.productsPage.viewNthProduct(String.valueOf(ordinalStringToInteger(ordinal))).click();
	}

	@When("^I hover over a product (.+)$")
	public void I_hover_over_a_product_name(String productName) throws InterruptedException {
		scrollToElement(productActions.productsPage.viewProductName(productName));
		hoverToElement(productActions.productsPage.viewProductName(productName));
	}

	@Then("I click continue shopping button")
	public void I_click_continue_shopping_button() {
		waitForElement(productActions.productsPage.getModalContinue());
		productActions.productsPage.getModalContinue().click();
	}
    
    @And("I add all related products to cart")
    public void I_add_all_related_products_to_cart() throws InterruptedException {
		scrollToElement(productActions.productsPage.searchedProductsHeading);
		productActions.addAllRelatedProductsToCart();
    }
}
