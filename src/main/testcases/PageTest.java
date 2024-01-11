package main.testcases;

import main.helper.CommonUtility;
import main.helper.Logger;
import main.pages.HomePage;
import main.pages.CartPage;
import main.pages.RegisterPage;
import main.pages.CheckoutPage;
import main.pages.PaymentPage;


import main.pages.PagesFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageTest extends BaseTest{
    HomePage homePage;
    CartPage cartPage;
    RegisterPage registerPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;

    public String productName;

    public String name = "Sifat";
    public String email = "sifatjamil369@gmail.com";
    public String password = "12345";
    public String firstName = "Sifat";
    public String lastName = "Jamil";
    public String address = "House-161";
    public String state = "Delhi";
    public String city = "New Delhi";
    public String zipcode = "1219";
    public String mobileNumber = "01701046386";
    public String nameOnCard = "Sifat Jamil";
    public String cardNumber = "121212121212";
    public String cvc = "6666";
    public String expirationMonth = "06";
    public String expirationYear = "2025";

    /***
     * Implementing test case to validate if 'Homepage' is visible
     * Steps 1,2,3
     */
    @Test(priority = 1)
    public void TC1_VerifyHomePageIsVisible() {
        CommonUtility.logCreateTest("TC3_VerifyHomePageIsVisible");

        homePage = PagesFactory.getHomePage();

        Assert.assertTrue(homePage.isHomePageLogoPresent(),"'Homepage' logo is displayed");
        Logger.endTestCase("VerifyHomePageIsVisible");
    }

    /***
     * Implementing test case to validate if a product can be added to cart
     * Gets the product name to verify it in the Checkout page
     * Step 4
     */
    @Test(priority = 2)
    public void TC2_AddProductToCart() {
        CommonUtility.logCreateTest("TC2_AddProductToCart");

        homePage = PagesFactory.getHomePage();

        productName = homePage.getProductName();
        homePage.clickOnAddToCartButton();
        homePage.clickOnContinueButton();
        Logger.endTestCase("AddProductToCart");
    }

    /***
     * Implementing test case to validate if 'Cart' page is displayed
     * Steps 5,6,7
     */
    @Test(priority = 3)
    public void TC3_VerifyCartPage() {
        CommonUtility.logCreateTest("TC3_VerifyCartPage");

        cartPage = PagesFactory.getCartPage();
        cartPage.clickOnCartButton();
        Assert.assertTrue(cartPage.isCartPageLogoPresent(),"'Cart page' logo is displayed");
        cartPage.clickOnProceedButton();
        Logger.endTestCase("VerifyCartPage");
    }

    /***
     * Implementing test case to validate if a user can be registered
     * Steps 8,9,10
     */
    @Test(priority = 4)
    public void TC4_VerifyRegistration() {
        CommonUtility.logCreateTest("TC4_VerifyRegistration");

        registerPage = PagesFactory.getRegisterPage();
        registerPage.clickOnRegisterButton();
        registerPage.newUserSignUp(name, email, password, firstName, lastName, address, state, city, zipcode, mobileNumber);
        Assert.assertTrue(registerPage.isUserRegistered(),"The user is registered");
        registerPage.clickOnContinueButton();
        Logger.endTestCase("VerifyRegistration");
    }

    /***
     * Implementing test case to validate username
     * Steps 11
     */
    @Test(priority = 5)
    public void TC5_VerifyUsername() {
        CommonUtility.logCreateTest("TC5_VerifyUsername");

        homePage = PagesFactory.getHomePage();

        Assert.assertEquals(homePage.getUsername(),name);
        Logger.endTestCase("VerifyUsername");
    }

    /***
     * Implementing test case to redirect to the cart page
     * Steps 12,13
     */
    @Test(priority = 6)
    public void TC6_ProceedToCheckout() {
        CommonUtility.logCreateTest("TC6_ProceedToCheckout");

        cartPage = PagesFactory.getCartPage();

        cartPage.clickOnCartButton();
        cartPage.clickOnProceedButton();
        Logger.endTestCase("ProceedToCheckout");
    }

    /***
     * Implementing test case to validate if the product and address are consistent
     * Step 14
     */
    @Test(priority = 7)
    public void TC7_VerifyProductAndAddress() {
        CommonUtility.logCreateTest("TC7_VerifyProductAndAddress");

        homePage = PagesFactory.getHomePage();
        checkoutPage = PagesFactory.getCheckoutPage();
        registerPage = PagesFactory.getRegisterPage();

        Assert.assertEquals(checkoutPage.getProductName(),productName);
        Assert.assertEquals(checkoutPage.getDeliveryName(),"."+" "+firstName+" "+lastName);
        Assert.assertEquals(checkoutPage.getDeliveryAddress(),address);
        Assert.assertEquals(checkoutPage.getDeliveryCity(),city+" "+state+" "+zipcode);
        Assert.assertEquals(checkoutPage.getDeliveryMobileNo(),mobileNumber);

        Logger.endTestCase("VerifyProductAndAddress");
    }

    /***
     * Implementing test case to validate commenting and placing order
     * Step 15
     */
    @Test(priority = 8)
    public void TC8_CommentAndPlaceOrder() {
        CommonUtility.logCreateTest("TC8_CommentAndPlaceOrder");

        checkoutPage = PagesFactory.getCheckoutPage();

        checkoutPage.typeComment("Test Comment");
        checkoutPage.clickPlaceOrder();
        Logger.endTestCase("CommentAndPlaceOrder");
    }

    /***
     * Implementing test case to validate payment and placing order
     * Step 16,17,18
     */
    @Test(priority = 9)
    public void TC9_VerifyPaymentAndConfirmOrder() {
        CommonUtility.logCreateTest("TC9_VerifyPaymentAndConfirmOrder");

        paymentPage = PagesFactory.getPaymentPage();

        paymentPage.enterPaymentDetails(nameOnCard, cardNumber, cvc, expirationMonth, expirationYear);
        paymentPage.clickOnPayButton();

        Assert.assertEquals(paymentPage.getSuccessMessage(), "Your order has been placed successfully!");
        Logger.endTestCase("VerifyPaymentAndConfirmOrder");
    }

    /***
     * Implementing test case to delete the created account for reusability
     */
    @Test(priority = 10)
    public void TC10_deletingTheAccount() {
        CommonUtility.logCreateTest("TC10_DeleteTheAccount");

        homePage = PagesFactory.getHomePage();

        homePage.clickOnDeleteAccountButton();
        Logger.endTestCase("DeleteTheAccount");
    }
}
