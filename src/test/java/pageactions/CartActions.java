package pageactions;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import pageobjects.CartPage;
import static components.BaseTest.*;
import static utilities.CommonFunctions.*;
import static utilities.CommonFunctions.getCache;

public class CartActions {
    public CartPage cartPage = new CartPage();

    public List<WebElement> getCartItems(){
        List<WebElement> cartItems;
        try {
            cartItems = cartPage.cartTable.findElements(By.xpath(".//tbody/tr[contains(@id, 'product')]"));
        } catch (Exception e) {
            log.error("Exception occurred in getCartItems(): " + e.getMessage());
            throw e;
        }
        return cartItems;
    }

    public boolean isProductPresentInCart(String product) {
        boolean flag = false;
        try{
            List<WebElement> search = driver.findElements(By.xpath(String.format("//td[@class='cart_description']//a[contains(text(), '%s')]", product)));
            if(search.size()>0) {
                flag = true;
            }
            else {
                flag = false;
            }
        } catch (Exception e) {
            log.error("Exception occurred in isProductPresentInCart(): " + e.getMessage());
            throw e;
        }
        return flag;
    }

    public void removeProductFromCart(String product) throws InterruptedException {
        try{
            //Remove key from JSON
            cartJson.remove(product);
            //Click X button
            cartPage.getCartDelete(product).click();
            Thread.sleep(Duration.ofSeconds(2));

        } catch (Exception e) {
            log.error("Exception occurred in removeProductFromCart(): " + e.getMessage());
            throw e;
        }
    }

    public WebElement getModalRegisterLogin() {
        try{
            return cartPage.modalBody.findElement(By.xpath(".//a[@href='/login']"));
        } catch (Exception e) {
            log.error("Exception occurred in getModalRegisterLogin(): " + e.getMessage());
            throw e;
        }
    }

    public void clickModalRegisterLogin() {
        try{
            waitForElement(getModalRegisterLogin());
            getModalRegisterLogin().click();
        } catch (Exception e) {
            log.error("Exception occurred in clickModalRegisterLogin(): " + e.getMessage());
            throw e;
        }
    }

    public void clickPlaceOrder() {
        try{
            scrollToElement(cartPage.placeOrderBtn);
            cartPage.placeOrderBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in clickPlaceOrder(): " + e.getMessage());
            throw e;
        }
    }

    public boolean checkItemsInCart(){
        boolean flag = true;
        try{
            //Gets all table rows of cart page
            List<WebElement> cartItems = getCartItems();
            for(WebElement items : cartItems) {
                String itemName = items.findElement(cartPage.cartDescription).getText();

                //Checks if item name does not exist in the JSON
                if(!cartJson.has(itemName)){
                    flag = false;
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred in checkItemsInCart(): " + e.getMessage());
            throw e;
        }
        return flag;
    }

    public void emptyCart() throws InterruptedException {
        try {
            //Gets all table rows of cart page
            List<WebElement> cartItems = getCartItems();

            for(WebElement item : cartItems) {
                String itemName = item.findElement(cartPage.cartDescription).getText();

                scrollToElement(item);
                removeProductFromCart(itemName);
            }
        } catch (Exception e) {
            log.error("Exception occurred in emptyCart(): " + e.getMessage());
            throw e;
        }
    }

    public boolean checkAddressType(String addressType){
        boolean flag = true;
        try {
            List<WebElement> addressElements = cartPage.getAddressElements(addressType);
            List<String> expectedAddressLines = new ArrayList<>();

            //Get expected values from cache
            expectedAddressLines.add("Address Lines");
            expectedAddressLines.add(getCache("Title") + " " + getCache("FirstName") + " " + getCache("LastName"));
            expectedAddressLines.add(getCache("Company"));
            expectedAddressLines.add(getCache("Address1"));
            expectedAddressLines.add(getCache("Address2"));
            expectedAddressLines.add(getCache("City") + " " + getCache("State") + " " + getCache("ZipCode"));
            expectedAddressLines.add(getCache("Country"));
            expectedAddressLines.add(getCache("MobileNumber"));

            //Compare expected vs actual address values
            for(int i = 0; i< addressElements.size(); i++) {
                //Skips heading element
                if(i == (0)) {
                    continue;
                }
                //Checks if expected address value is NOT equal to actual address value
                if(!expectedAddressLines.get(i).equals(addressElements.get(i).getText())){
                    flag = false;
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred in checkAddressType(): " + e.getMessage());
            throw e;
        }
        return flag;
    }

    public boolean checkCartDetails(){
        boolean flag = true;
        try{
            //Gets all table rows of cart and store as list
            List<WebElement> cartItems = getCartItems();

            //Initialize total bill
            int bill = 0;

            //Verify cart details
            for(WebElement items : cartItems) {
                String itemName = items.findElement(cartPage.cartDescription).getText();

                //Actual values from cart page
                String actualItemPrice = items.findElement(cartPage.cartPrice).getText();
                String actualItemQuantity = items.findElement(cartPage.cartQuantity).getText();
                String actualTotalPrice = items.findElement(cartPage.cartTotal).getText();

                //Expected values from JSON
                JSONObject record  = (JSONObject) cartJson.get(itemName);
                String expectedItemPrice = record.getString("Price");
                String expectedItemQuantity = String.valueOf(record.getInt("Quantity"));
                String expectedTotalPrice = "Rs. " + record.getInt("Total");
                bill = bill + record.getInt("Total");

                //Checks if expected values are NOT equal to actual values
                if((!expectedItemPrice.equals(actualItemPrice)) ||
                   (!expectedItemQuantity.equals(actualItemQuantity)) ||
                   (!expectedTotalPrice.equals(actualTotalPrice)) ){

                    flag = false;
                    break;
                }
            }
            //Store bill to cache
            setCache("Bill", String.valueOf(bill));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}