package pageactions;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.DirectoryPage;
import pageobjects.ProductsPage;

import java.util.List;

import static components.BaseTest.*;
import static utilities.CommonFunctions.*;

public class ProductActions {
    public ProductsPage productsPage = new ProductsPage();

    public void viewCart() {
        try{
            productsPage.getModalViewCart().click();
        } catch (Exception e) {
            log.error("Exception occurred in viewCart(): " + e.getMessage());
            throw e;
        }
    }

    public void addToExpectedCartItems(String productName) {
        int finalQuantity = 1;
        int variableQuantity = 1;
        String price = "";

        try {
            //Gets variable quantity and price from current page
            if(driver.getCurrentUrl().contains("product_details")) {
                variableQuantity = Integer.valueOf(productsPage.detailQuantity.getAttribute("value"));
                price = productsPage.detailPrice.getText();
            }
            else {
                price = productsPage.viewProductPrice(productName).getText();
            }

            //If JSON already has the product, adjust quantity
            if(cartJson.has(productName)) {
                JSONObject record = (JSONObject) cartJson.get(productName);
                finalQuantity = (int) record.get("Quantity") + variableQuantity;

                //remove key from JSON
                cartJson.remove(productName);
            }
            else {
                finalQuantity = variableQuantity;
            }
            JSONObject orderDetails = new JSONObject();
            orderDetails.put("Price", price);
            orderDetails.put("Quantity", finalQuantity);
            orderDetails.put("Total", Integer.valueOf(price.replaceAll("[^\\d]", "").strip()) * finalQuantity);
            cartJson.put(productName,  orderDetails);

        } catch (Exception e) {
            log.error("Exception occurred in addToExpectedCartItems(): " + e.getMessage());
            throw e;
        }
    }

    public void goToPanel(String navigation) {
        String[] nav = navigation.split(">");
        String panelType = nav[0].strip();
        String node1 = nav[1].strip();

        try{
            if(panelType.equalsIgnoreCase("Category")) {
                String node2 = nav[2].strip();

                scrollToElement(productsPage.categoryHeading);
                productsPage.panelTitle(node1).click();
                waitForElement(productsPage.categoryItem(node2));
                productsPage.categoryItem(node2).click();
            }
            else if(panelType.equalsIgnoreCase("Brands")) {
                scrollToElement(productsPage.brandHeading);
                productsPage.brandItem(node1).click();
            }
        } catch (Exception e) {
            log.error("Exception occurred in goToPanel(): " + e.getMessage());
            throw e;
        }
    }

    public void searchProduct(String product){
        try{
            productsPage.searchText.sendKeys(product);
            productsPage.searchBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in searchProduct(): " + e.getMessage());
            throw e;
        }
    }

    public void addToCartOnOverlay(String productName){
        try{
            addToExpectedCartItems(productName);
            productsPage.overlayAddToCart(productName).click();
        } catch (Exception e) {
            log.error("Exception occurred in addToCartOnOverlay(): " + e.getMessage());
            throw e;
        }
    }

    public void addToCartViaProductDetail(){
        try{
            String productName = productsPage.detailName.getText();
            addToExpectedCartItems(productName);
            productsPage.detailAddToCartBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in addToCartViaProductDetail(): " + e.getMessage());
            throw e;
        }
    }

    public void increaseProductQuantity(String amount){
        try{
            productsPage.detailQuantity.clear();
            productsPage.detailQuantity.sendKeys(amount);
        } catch (Exception e) {
            log.error("Exception occurred in increaseProductQuantity(): " + e.getMessage());
            throw e;
        }
    }

    public void fillReviewDetails(String name, String email, String message){
        try{
            productsPage.reviewName.sendKeys(name);
            productsPage.reviewEmail.sendKeys(email);
            productsPage.reviewMessage.sendKeys(message);
        } catch (Exception e) {
            log.error("Exception occurred in fillReviewDetails(): " + e.getMessage());
            throw e;
        }
    }

    public boolean checkAvailableProducts(String navigation){
        boolean flag = true;

        try{
            List<WebElement> availableProducts = productsPage.getAvailableProducts();
            String[] nav = navigation.split(">");
            String productGroup = nav[0].strip();
            String node1 = nav[1].strip();
            String text= "";

            //Checks if there are no available products
            if(availableProducts.isEmpty()){
                flag = false;
            }

            //Checks if heading text is not displayed correctly
            if(productGroup.equalsIgnoreCase("Category")) {
                String node2 = nav[2].strip();
                text = productsPage.productGroupHeading(node1).getAttribute("textContent");

                if(!(text.equals(node1 + " - " + node2 + " Products"))){
                    flag = false;
                }
            }
            else if(productGroup.equalsIgnoreCase("Brands")) {
                text = productsPage.productGroupHeading("Brand").getAttribute("textContent");

                if(!(text.equals("Brand - " + node1 + " Products"))){
                    flag = false;
                }
            }

        } catch (Exception e) {
            log.error("Exception occurred in checkAvailableProducts(): " + e.getMessage());
            throw e;
        }
        return flag;
    }

    public boolean checkRelatedSearchProducts(String productName){
        boolean flag = true;

        try{
            List<WebElement> relatedSearch = productsPage.getAvailableProducts();

            for(WebElement element : relatedSearch) {
                WebElement name = element.findElement(productsPage.relatedProductName);

                if(!(name.getText().toLowerCase().contains(productName.toLowerCase()))){
                    flag = false;
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred in checkRelatedSearchProducts(): " + e.getMessage());
            throw e;
        }
        return flag;
    }

    public void addAllRelatedProductsToCart() throws InterruptedException {
        try{
            List<WebElement> relatedSearch = productsPage.getAvailableProducts();

            for(WebElement products : relatedSearch) {
                String productName = products.findElement(productsPage.relatedProductName).getText();

                scrollToElement(productsPage.viewProductName(productName));
                hoverToElement(productsPage.viewProductName(productName));
                addToCartOnOverlay(productName);
                waitForElement(productsPage.getModalContinue());
                productsPage.getModalContinue().click();
            }
        } catch (Exception e) {
            log.error("Exception occurred in addAllRelatedProductsToCart(): " + e.getMessage());
            throw e;
        }
    }
}
