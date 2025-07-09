package pageactions;

import pageobjects.*;
import static components.BaseTest.*;

import static utilities.CommonFunctions.scrollToElement;

public class DirectoryActions {
    public DirectoryPage directory = new DirectoryPage();

    public void subscribeToWebsite(String email){
        try{
            scrollToElement(directory.subscribeEmail);
            directory.subscribeEmail.sendKeys(email);
            directory.subscribeBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in subscribeToWebsite(): " + e.getMessage());
            throw e;
        }
    }

    public void goToHeader(String header) {
        try{
            switch(header) {
                case "Home":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://automationexercise.com/")) {
                        directory.home.click();
                    }
                    break;

                case "Products":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/products")) {
                        directory.products.click();
                    }
                    break;

                case "Cart":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/view_cart")) {
                        directory.cart.click();
                    }
                    break;

                case "Signup/Login":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/login")) {
                        directory.signup.click();
                    }
                    break;

                case "Test Cases":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/test_cases")) {
                        directory.testCases.click();
                    }
                    break;

                case "Contact Us":
                    //Checks if user is already on the page
                    if(!driver.getCurrentUrl().equals("https://www.automationexercise.com/contact_us")) {
                        directory.contact.click();
                    }
                    break;

                default:
                    log.error("Invalid header");
                    driver.quit();
                    break;
            }
        } catch (Exception e) {
            log.error("Exception occurred in goToHeader(): " + e.getMessage());
            throw e;
        }
    }
}
