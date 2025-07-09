package pageactions;

import pageobjects.ContactUsPage;

import static components.BaseTest.log;
import static utilities.CommonFunctions.scrollToElement;

public class ContactUsActions {
    public ContactUsPage contactUsPage = new ContactUsPage();

    public void fillUpContactUsTextFields(String iName, String iEmail, String iSubject, String iMessage) {
        try{
            contactUsPage.name.sendKeys(iName);
            contactUsPage.email.sendKeys(iEmail);
            contactUsPage.subject.sendKeys(iSubject);
            contactUsPage.message.sendKeys(iMessage);
        } catch (Exception e) {
            log.error("Exception occurred in fillUpContactUsTextFields(): " + e.getMessage());
            throw e;
        }
    }

    public void chooseFile(String fileName) {
        try{
            String path = System.getProperty("user.dir") + "//src//main/java//resources//" + fileName;
            scrollToElement(contactUsPage.chooseFileBtn);
            contactUsPage.chooseFileBtn.sendKeys(path);
        } catch (Exception e) {
            log.error("Exception occurred in chooseFile(): " + e.getMessage());
            throw e;
        }
    }

    public void submitContactUsForm() {
        try{
            scrollToElement(contactUsPage.submitBtn);
            contactUsPage.submitBtn.click();
        } catch (Exception e){
            log.error("Exception occurred in submitContactUsForm(): " + e.getMessage());
            throw e;
        }
    }
}
