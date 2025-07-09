package pageactions;

import org.json.JSONObject;
import org.openqa.selenium.By;
import pageobjects.HomePage;

import static components.BaseTest.cartJson;
import static components.BaseTest.log;
import static utilities.CommonFunctions.hoverToElement;
import static utilities.CommonFunctions.waitForElement;

public class HomePageActions {
    public static HomePage homePage = new HomePage();

    public void addToCartRecommendedItem(String product) throws InterruptedException {
        try{
            hoverToElement(homePage.recomItemCarousel);

            if(!homePage.recommendedItem(product).isDisplayed()) {
                homePage.leftRecomItemControl.click();
                waitForElement(homePage.recommendedItem(product));
            }

            hoverToElement(homePage.recommendedItem(product));

            int recomItemQuantity = 1;
            String recomItemPrice = homePage.recommendedItem(product).findElement(By.xpath(".//h2")).getText();

            //Click add to cart
            homePage.recommendedItem(product).findElement(By.xpath(".//a")).click();

            //If JSON already has the product, adjust quantity
            if(cartJson.has(product)) {
                JSONObject record = (JSONObject) cartJson.get(product);
                recomItemQuantity = (int) record.get("Quantity") + 1;

                //remove key from JSON
                cartJson.remove(product);
            }

            //Store to cart JSON
            JSONObject orderDetails = new JSONObject();
            orderDetails.put("Price", recomItemPrice);
            orderDetails.put("Quantity", recomItemQuantity);
            orderDetails.put("Total", Integer.valueOf(recomItemPrice.replaceAll("[^\\d]", "").strip()) * recomItemQuantity);
            cartJson.put(product,  orderDetails);
        } catch (Exception e) {
            log.error("Exception occurred in addToCartRecommendedItem(): " + e.getMessage());
            throw e;
        }
    }
}
