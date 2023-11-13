package main.testcases;

import main.helper.CommonUtility;
import main.helper.Logger;
import main.pages.PagesFactory;
import main.pages.ReserveADeskPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReserveADeskTest extends BaseTest{

    ReserveADeskPage reserveADeskPage;

    /***
     * Implementing test case to validate if a desk can be reserved
     */
    @Test(priority = 1)
    public void TC1_CheckIfDeskCanBeReserved() {
        CommonUtility.logCreateTest("TC1_CheckIfDeskCanBeReserved");

        reserveADeskPage = PagesFactory.reserveADeskPage();

        reserveADeskPage.clickOnReserveADeskButton();
        reserveADeskPage.expandTheFloorDropdown();
        reserveADeskPage.selectAFloorFromDropdown(5);
        reserveADeskPage.clickOnSearchButton();
        reserveADeskPage.clickOnADesk();
        reserveADeskPage.reserveADesk();
        Assert.assertTrue(reserveADeskPage.checkIfReservationIsConfirmed());
        reserveADeskPage.clickOnOkButton();
        Logger.endTestCase("CheckIfDeskCanBeReserved");
    }

    /***
     * Implementing test case to validate if a desk reservation can be cancelled
     */
    @Test(priority = 2)
    public void TC2_CheckIfDeskReservationCanBeCancelled() {
        CommonUtility.logCreateTest("TC2_CheckIfDeskReservationCanBeCancelled");

        reserveADeskPage = PagesFactory.reserveADeskPage();

        reserveADeskPage.expandTheFloorDropdown();
        reserveADeskPage.selectAFloorFromDropdown(5);
        reserveADeskPage.clickOnSearchButton();
        reserveADeskPage.clickOnADesk();
        reserveADeskPage.reserveADesk();
        reserveADeskPage.clickOnADesk();
        reserveADeskPage.clickOnYesContinueButton();
        Assert.assertTrue(reserveADeskPage.checkIfCancellationIsConfirmed());
        Logger.endTestCase("CheckIfDeskReservationCanBeCancelled");
    }
}
