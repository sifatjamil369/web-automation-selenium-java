package main.browserutility;

import main.helper.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.*;

public class DriverCommand {

    final WebDriver webDriver;

    public DriverCommand() {
        this.webDriver = Browser.getWebDriver();

    }

    /**
     * Method to get WebDriver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return this.webDriver;

    }

    /***
     * Implementing Webdriverwait functionality
     * @return Webdrierwait
     */
    public WebDriverWait explicitlyWait() {
        return explicitlyWait(30);

    }

    /***
     * Implementing overload of Webdriverwait functionality
     * @param wait: Take the timeout value as integer
     * @return
     */
    public WebDriverWait explicitlyWait(int wait) {
        return new WebDriverWait(webDriver, ofSeconds(wait));
    }

    /***
     * Implementing implicitWait functionality. This function has been implemented to avoid Thread.sleep()
     * @param waitTimeOut
     */
    public void implicitWait(int waitTimeOut) {
        try {
            explicitlyWait(waitTimeOut).until(ExpectedConditions.presenceOfElementLocated(By.id("dummy locator")));
        } catch (Exception exception) {
            CommonUtility.logMessagesAndAddThemToReport("Waited for "
                    + waitTimeOut + " seconds to locate a dummy locator", "info");
        }
    }

    /**
     * Implementation of fluentWait functionality
     *
     * @param locator                  web element's locator
     * @param attributeName            name of the attribute
     * @param expectedValueOfAttribute expected value of attribute
     * @param waitTime                 wait until the condition is satisfied (in milliseconds)
     * @param pollingFrequency         frequency duration for checking the condition (in milliseconds)
     */
    public void fluentWait(By locator, String attributeName, String expectedValueOfAttribute,
                           int waitTime, int pollingFrequency) {
        try {
            //Declare and initialise a fluent wait
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver());
            //Specify the timeout of the wait
            wait.withTimeout(ofSeconds(waitTime));
            //Specify polling time
            wait.pollingEvery(ofSeconds(pollingFrequency));
            //This is the condition to wait on.
            wait.until(ExpectedConditions.attributeContains(locator, attributeName, expectedValueOfAttribute));
            CommonUtility.logMessagesAndAddThemToReport(
                    expectedValueOfAttribute + " was found within "+ waitTime +"the time limitation", "info");
        } catch (Exception exception) {
            CommonUtility.logMessagesAndAddThemToReport("Waited for "
                    + waitTime + " milliseconds but the expected value " + expectedValueOfAttribute
                    + " was not found in the "+attributeName+" attribute", "warn");
        }

    }


    /***
     * Implementing maximizeWindow functionality
     */
    public void maximizeWindow() {
        webDriver.manage().window().maximize();

    }

    /***
     * Implementing minimizeWindow functionality
     */
    public void minimizeWindow() {
        webDriver.manage().window().maximize();

    }

    /***
     * Implementing close focused screen functionality
     */
    public void closeFocusedScreen() {
        Browser.closeFocusedScreen();

    }

}
