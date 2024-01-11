package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class CartPage extends BaseClass{

    By cartButton = By.xpath("//a[@href=\"/view_cart\"]");
    By cartPageLogoLocator = By.xpath("//li[@class='active']");
    By proceedToCheckoutButtonLocator = By.xpath("//a[@class='btn btn-default check_out']");
    By registerLoginButtonLocator = By.xpath("//u[normalize-space()='Register / Login']");


    /*
    Clicks on the 'Cart' button
     */
    public void clickOnCartButton() {
        try {
            clickOn(cartButton, 120, "'Cart' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Checks if the Cart page is present
     */
    public Boolean isCartPageLogoPresent() {
        try {
            return isExpectedElementVisible(cartPageLogoLocator, 120, "'Cart page' logo");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Clicks on the 'Proceed To Checkout' button
     */
    public void clickOnProceedButton() {
        try {
            clickOn(proceedToCheckoutButtonLocator, 120, "'Proceed To Checkout' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

}
