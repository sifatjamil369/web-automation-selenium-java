package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class PaymentPage extends BaseClass{

    By nameOnCardFieldLocator = By.xpath("//input[@data-qa='name-on-card']");
    By cardNumberFieldLocator = By.xpath("//input[@data-qa='card-number']");
    By cvcFieldLocator = By.xpath("//input[@data-qa='cvc']");
    By expirationMonthFieldLocator = By.xpath("//input[@data-qa='expiry-month']");
    By expirationYearFieldLocator = By.xpath("//input[@data-qa='expiry-year']");
    By payConfirmOrderButtonLocator = By.xpath("//button[@data-qa='pay-button']");
    By successMessageLocator = By.xpath("(//div[@class='alert-success alert'])[1]");



    /*
    Enter Payment details
     */
    public void enterPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expirationMonth, String  expirationYear) {
        try {
            sendKeys(nameOnCardFieldLocator, nameOnCard, "Name on card");
            sendKeys(cardNumberFieldLocator, cardNumber, "Card number");
            sendKeys(cvcFieldLocator, cvc, "CVC");
            sendKeys(expirationMonthFieldLocator, expirationMonth, "Expiration month of card");
            sendKeys(expirationYearFieldLocator, expirationYear, "Expiration year of card");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the Pay button
     */
    public void clickOnPayButton() {
        try {
            clickOn(payConfirmOrderButtonLocator, "'Pay and Confirm Order' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Gets and returns Success message for verification
     */
    public String getSuccessMessage() {
        try {
            driverCommand.backButton();
            return getText(successMessageLocator, "Success message");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }
}
