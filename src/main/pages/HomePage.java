package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class HomePage extends BaseClass{

    By homePageLogoLocator = By.xpath("//img[@alt='Website for automation practice']");
    By productNameLocator = By.xpath("(//div[@class='productinfo text-center']/p)[1]");
    By addToCartButtonLocator = By.xpath("(//a[@data-product-id='1'])[1]");
    By continueButtonLocator = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    By deleteAccountButtonLocator = By.xpath("//a[@href='/delete_account']");
    By usernameLocator = By.xpath("//b");

    /*
    Checks if the Homepage logo is present
     */
    public Boolean isHomePageLogoPresent() {
        try {
            return isExpectedElementVisible(homePageLogoLocator, 120, "'Homepage' logo");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Gets and returns the product name for verification
     */
    public String getProductName() {
        try {
            return getText(productNameLocator, 120, "Product name");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Clicks on the 'Add to Cart' button for a product
     */
    public void clickOnAddToCartButton() {
        try {
            clickOn(addToCartButtonLocator, 120, "'Add to Cart' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the Continue button
     */
    public void clickOnContinueButton() {
        try {
            clickOn(continueButtonLocator, 120, "'Continue' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the 'Delete Account' button
     */
    public void clickOnDeleteAccountButton() {
        try {
            clickOn(deleteAccountButtonLocator, 120, "'Add to Cart' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Gets and returns username for verification
     */
    public String getUsername() {
        try {
            return getText(usernameLocator,"Username");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }
}
