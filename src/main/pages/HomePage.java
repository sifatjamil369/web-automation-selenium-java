package main.pages;

import main.browserutility.DriverCommand;
import main.helper.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class HomePage extends BaseClass{

    By reserveADeskButtonLocator = By.xpath("//button[normalize-space()='Reserve a Desk']");
    By noUpcomingReservationLocator = By.xpath("//div[@class='MuiAlert-message css-1xsto0d']");

    /*
    Checks if the return button is present
     */
    public Boolean isReserveADeskButtonPresent() {
        try {
            return isExpectedElementVisible(reserveADeskButtonLocator, 120, "'Reserve a Desk' button");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /*
    Checks if 'No Upcoming Reservation' message is displayed
     */
    public Boolean isNoUpcomingReservationDisplayed() {
        try {
            return isExpectedElementVisible(noUpcomingReservationLocator, 120, "'No Upcoming Reservation' announcement");
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }
}
