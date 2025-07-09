package pageactions;

import org.openqa.selenium.support.ui.Select;
import pageobjects.SignUpPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static components.BaseTest.log;
import static utilities.CommonFunctions.scrollToElement;
import static utilities.CommonFunctions.setCache;

public class SignUpActions {
    public SignUpPage signUpPage = new SignUpPage();

    public void fillAccountInfo(String iTitle, String iName, String iPassword,
                                String iDate, Boolean iNews, Boolean iOffers) throws InterruptedException {

        try{
            //Parse iDate from string to local date object
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate iBirth = LocalDate.parse(iDate, format);

            Select dayDropDown = new Select(signUpPage.birthDay);
            Select monthDropDown = new Select(signUpPage.birthMonth);
            Select yearDropDown = new Select(signUpPage.birthYear);

            signUpPage.title(iTitle).click();

            //Save title to cache for address verification
            setCache("Title", signUpPage.title(iTitle).getAttribute("value") + ".");

            if(!signUpPage.name.getText().equals(iName)) {
                signUpPage.name.clear();
                signUpPage.name.sendKeys(iName);
            }

            signUpPage.password.sendKeys(iPassword);

            scrollToElement(signUpPage.birthDay);
            dayDropDown.selectByValue(String.valueOf(iBirth.getDayOfMonth()));
            monthDropDown.selectByValue(String.valueOf(iBirth.getMonthValue()));
            yearDropDown.selectByValue(String.valueOf(iBirth.getYear()));

            //Toggles check boxes if parameter passed is true
            if(iNews) {
                signUpPage.newsletter.click();
            }
            if(iOffers) {
                signUpPage.offers.click();
            }
        } catch (Exception e) {
            log.error("Exception occurred in fillAccountInfo(): " + e.getMessage());
            throw e;
        }
    }

    public void fillAddressInfo(String iFirstName, String iLastName, String iCompany, String iAddressLine, String iMobile) throws InterruptedException {

        try{
            //Parse iAddressLine as string array
            String[] address = iAddressLine.split(",");
            String iAddress1 = address[0];
            String iAddress2 = address[1];
            String iCountry = address[2];
            String iState = address[3];
            String iCity = address[4];
            String iZipCode = address[5];

            Select countryDropDown = new Select(signUpPage.country);
            scrollToElement(signUpPage.firstName);
            signUpPage.firstName.sendKeys(iFirstName);
            signUpPage.lastName.sendKeys(iLastName);
            signUpPage.company.sendKeys(iCompany);
            signUpPage.address1.sendKeys(iAddress1);
            signUpPage.address2.sendKeys(iAddress2);
            countryDropDown.selectByValue(iCountry);
            signUpPage.state.sendKeys(iState);
            signUpPage.city.sendKeys(iCity);

            scrollToElement(signUpPage.zipCode);
            signUpPage.zipCode.sendKeys(iZipCode);
            signUpPage.mobileNumber.sendKeys(iMobile);

            //Saves values to cache for address verification
            setCache("FirstName", iFirstName);
            setCache("LastName", iLastName);
            setCache("Company", iCompany);
            setCache("Address1", iAddress1);
            setCache("Address2", iAddress2);
            setCache("Country", iCountry);
            setCache("State", iState);
            setCache("City", iCity);
            setCache("ZipCode", iZipCode);
            setCache("MobileNumber", iMobile);
        } catch (Exception e) {
            log.error("Exception occurred in fillAddressInfo(): " + e.getMessage());
            throw e;
        }
    }

    public void submitAccountForm() {
        try{
            scrollToElement(signUpPage.createAccountBtn);
            signUpPage.createAccountBtn.click();
        } catch (Exception e) {
            log.error("Exception occurred in submitAccountForm(): " + e.getMessage());
            throw e;
        }
    }
}
