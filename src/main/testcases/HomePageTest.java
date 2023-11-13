package main.testcases;

import main.helper.CommonUtility;
import main.helper.Logger;
import main.pages.HomePage;
import main.pages.PagesFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{
    HomePage homePage;

//    @BeforeTest
//    public void Wait() throws InterruptedException {
//        Thread.sleep(120000);
//    }

    /***
     * Implementing test case to validate if 'Reserve a Desk' button is present
     */
    @Test(priority = 1)
    public void TC5_CheckIfReserveADeskButtonIsPresent() {
        CommonUtility.logCreateTest("TC5_CheckIfReserveADeskButtonIsPresent");

        homePage = PagesFactory.getHomePage();

        Assert.assertTrue(homePage.isReserveADeskButtonPresent(),"'Reserve a Desk' button is displayed");
        Logger.endTestCase("CheckIfReserveADeskButtonIsPresent");
    }

    /***
     * Implementing test case to validate if 'You have no upcoming reservation' message is displayed
     */
    @Test(priority = 2)
    public void TC17_CheckIfNoUpcomingReservationDisplayed() {
        CommonUtility.logCreateTest("TC17_CheckIfNoUpcomingReservationDisplayed");

        homePage = PagesFactory.getHomePage();

        Assert.assertTrue(homePage.isNoUpcomingReservationDisplayed(),"'You have no upcoming reservation' message is displayed");
        Logger.endTestCase("CheckIfNoUpcomingReservationDisplayed");
    }
}
