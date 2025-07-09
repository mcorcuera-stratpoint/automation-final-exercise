package pageactions;

import pageobjects.DirectoryPage;
import pageobjects.PaymentPage;
import utilities.Constants;

import static components.BaseTest.log;
import static utilities.CommonFunctions.deleteFile;

public class PaymentActions {
    public PaymentPage paymentPage = new PaymentPage();

    public void enterPaymentDetails(String nameOnCard, String cardNum, String cvc, String expMonth, String expYear){
        try{
            paymentPage.nameOnCard.sendKeys(nameOnCard);
            paymentPage.cardNumber.sendKeys(cardNum);
            paymentPage.cvc.sendKeys(cvc);
            paymentPage.expiryMonth.sendKeys(expMonth);
            paymentPage.expiryYear.sendKeys(expYear);
        } catch (Exception e) {
            log.error("Exception occurred in enterPaymentDetails(): " + e.getMessage());
            throw e;
        }
    }

    public void deleteInvoice(){
        try{
            deleteFile(Constants.DEFAULT_DOWNLOAD_PATH, "invoice.txt");
        } catch (Exception e) {
            log.error("Exception occurred in deleteInvoice(): " + e.getMessage());
            throw e;
        }
    }
}
