package main.pages;

import main.helper.CommonUtility;
import org.openqa.selenium.By;

public class ReserveADeskPage extends BaseClass{
    By reserveADeskButtonLocator = By.xpath("//button[normalize-space()='Reserve a Desk']");
    By floorDropdownLocator = By.xpath("//div[@class='MuiFormControl-root css-jnojtp']//div[@aria-haspopup='listbox']");
    By chooseFloorFromDropdownLocator;
    By searchButtonLocator = By.xpath("//button[normalize-space()='SEARCH']");
    By deskLocator = By.xpath("//*[local-name()='g' and @data-desk-number='50404']//*[local-name()='circle']");
    By reserveButtonLocator = By.xpath("//button[normalize-space()='Reserve']");
    By reservationConfirmedLocator = By.xpath("//p[normalize-space()='Reservation confirmed!']");
    By yesContinueButtonLocator = By.xpath("MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedError MuiButton-sizeMedium " +
            "MuiButton-containedSizeMedium MuiButton-root MuiButton-contained MuiButton-containedError MuiButton-sizeMedium MuiButton-containedSizeMedium css-yprzh9");
    By okButtonLocator = By.xpath("//button[normalize-space()='OK']");
    By cancellationConfirmedLocator = By.xpath("//div[@role='presentation']");


    /*
    Clicks on the 'Reserve a Desk' button is on the 'Home Screen'
     */
    public void clickOnReserveADeskButton() {
        try {
            clickOn(reserveADeskButtonLocator, 120, "'Reserve a Desk' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the specific desk
     */
    public void clickOnADesk() {
        try {
            clickOn(deskLocator, 5, "'Desk Locator' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the 'Reserve' button to confirm reservation
     */
    public void reserveADesk() {
        try {
            clickOn(reserveButtonLocator, 3, "'Reserve' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Opens the Floors dropdown
     */
    public void expandTheFloorDropdown() {
        try {
            clickOn(floorDropdownLocator, 3, "'Floor' Dropdown");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Selects a floor from the floor selection dropdown
    */
    public void selectAFloorFromDropdown(int floorNumber){
        chooseFloorFromDropdownLocator = By.xpath("//li[@data-value='"+floorNumber+"']");
        try {
            clickOn(chooseFloorFromDropdownLocator, 3, "Select 'Level "+floorNumber+"'");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the 'Search' button
     */
    public void clickOnSearchButton() {
        try {
            clickOn(searchButtonLocator, 3, "'Desk Locator' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the 'Yes, continue' button on the delete modal
     */
    public void clickOnYesContinueButton() {
        try {
            clickOn(yesContinueButtonLocator, 3, "'Yes, continue' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Clicks on the Ok button on the confirmation alert
     */
    public void clickOnOkButton() {
        try {
            clickOn(okButtonLocator, 3, "'OK' Button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /*
    Checks if the reservation confirmed message appars in the alert
     */
    public boolean checkIfReservationIsConfirmed() {
        try {
            return isExpectedElementVisible(reservationConfirmedLocator, 5,"'Reservation confirmed!' message");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return false;
    }

    /*
    Checks if the reservation cancelled message appars in the alert
     */
    public boolean checkIfCancellationIsConfirmed() {
        try {
            return isExpectedElementVisible(cancellationConfirmedLocator, 5,"'Reservation has been cancelled successfully!' message");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return false;
    }
}
