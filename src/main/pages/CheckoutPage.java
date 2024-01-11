package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class CheckoutPage extends BaseClass{

    By productNameLocator = By.xpath("//a[@href=\"/product_details/1\"]");
    By deliveryNameLocator = By.xpath("(//li[@class='address_firstname address_lastname'])[1]");
    By deliveryAddressLocator = By.xpath("(//li[@class='address_address1 address_address2'])[2]");
    By deliveryCityLocator = By.xpath("(//li[@class='address_city address_state_name address_postcode'])[1]");
    By deliveryCountryLocator = By.xpath("(//li[@class='address_country_name'])[1]");
    By deliveryMobileNoLocator = By.xpath("(//li[@class='address_phone'])[1]");
    By commentBoxLocator = By.xpath("//textarea");
    By placeOrderButtonLocator = By.xpath("//a[@class='btn btn-default check_out']");


    /*
    Returns the 'Product name' from Checkout page
     */
    public String getProductName() {
        try {
            return getText(productNameLocator, "'Product name' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Returns the 'Delivery name' from Checkout page
     */
    public String getDeliveryName() {
        try {
            return getText(deliveryNameLocator, "'Delivery name' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Returns the 'Delivery Address' from Checkout page
     */
    public String getDeliveryAddress() {
        try {
            return getText(deliveryAddressLocator, "'Delivery Address' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Returns the 'Delivery City' from Checkout page
     */
    public String getDeliveryCity() {
        try {
            return getText(deliveryCityLocator, "'Delivery City' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Returns the 'Delivery Country' from Checkout page
     */
    public String getDeliveryCountry() {
        try {
            return getText(deliveryCountryLocator, "'Delivery Country' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Returns the 'Delivery Mobile no' from Checkout page
     */
    public String getDeliveryMobileNo() {
        try {
            return getText(deliveryMobileNoLocator, "'Delivery Mobile no' from Checkout page");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Types in the comment box
     */
    public void typeComment(String comment) {
        try {
            sendKeys(commentBoxLocator, comment, "Comment in the comment box");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the 'Place Order' button
     */
    public void clickPlaceOrder() {
        try {
            clickOn(placeOrderButtonLocator, 120, "'Place Order' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

}
