package pageactions;

import pageobjects.LoginPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static components.BaseTest.log;
import static utilities.CommonFunctions.getCache;
import static utilities.CommonFunctions.setCache;

public class LoginActions {
    public LoginPage loginPage = new LoginPage();

    public void login(String email, String password) throws InterruptedException {
        try{
            if(email.equals("auto")) {
                email = getCache("cache_email");
            }
            loginPage.loginEmail.sendKeys(email);
            loginPage.loginPassword.sendKeys(password);
            loginPage.loginBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in login(): " + e.getMessage());
            throw e;
        }
    }

    public void signUp(String email, String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        try{
            if(email.equals("auto")) {
                email = currentDateTime.truncatedTo(ChronoUnit.SECONDS).format(formatter).toString() + "@email.com";
                setCache("cache_email", email);
            }
            loginPage.signUpName.sendKeys(name);
            loginPage.signUpEmail.sendKeys(email);
            loginPage.signUpBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in signUp(): " + e.getMessage());
            throw e;
        }
    }
}
