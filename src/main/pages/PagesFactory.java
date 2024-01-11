package main.pages;

import main.browserutility.Browser;
import org.openqa.selenium.support.PageFactory;
public final class PagesFactory {
    private static <T> T GetPage(Class<T> className){
        return PageFactory.initElements(Browser.getWebDriver(), className);
    }


    /**
     * Method to initiate Home page
     * @return
     */
    public static HomePage getHomePage(){
        return GetPage(HomePage.class);
    }

    /**
     * Method to initiate Cart page
     * @return
     */
    public static CartPage getCartPage(){
        return GetPage(CartPage.class);
    }

    /**
     * Method to initiate Register page
     * @return
     */
    public static RegisterPage getRegisterPage(){
        return GetPage(RegisterPage.class);
    }

    /**
     * Method to initiate Checkout page
     * @return
     */
    public static CheckoutPage getCheckoutPage(){
        return GetPage(CheckoutPage.class);
    }

    /**
     * Method to initiate Payment page
     * @return
     */
    public static PaymentPage getPaymentPage(){
        return GetPage(PaymentPage.class);
    }

}
