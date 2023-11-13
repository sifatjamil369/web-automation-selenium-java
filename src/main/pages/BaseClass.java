package main.pages;

import main.browserutility.Browser;
import main.helper.CommonUtility;
import main.browserutility.DriverCommand;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.Format;

import static java.time.Duration.ofSeconds;

public class BaseClass {

    DriverCommand driverCommand;

    final static String SUCCESS_TOAST_TYPE = "SUCCESS";
    final static String WARNING_TOAST_TYPE = "WARNING";
    final static String ERROR_TOAST_TYPE = "ERROR";

    public BaseClass() {
        driverCommand = new DriverCommand();

    }

    /***
     * Implementing Webdriverwait functionality
     * @return Webdrierwait
     */
    public WebDriverWait explicitlyWait() {
        return driverCommand.explicitlyWait(30);

    }

    /***
     * Implementing overload of Webdriverwait functionality
     * @param wait: Take the timeout value as integer
     * @return Webdrierwait
     */
    public WebDriverWait explicitlyWait(int wait) {
        return driverCommand.explicitlyWait(wait);

    }

    /***
     * Implementing maximizeWindow functionality
     */

    public void maximizeWindow() {
        driverCommand.maximizeWindow();

    }

    /***
     * Implementing minimizeWindow functionality
     */
    public void minimizeWindow() {
        driverCommand.minimizeWindow();

    }

    /***
     * Implementing implicitWait functionality. This function has been implemented to avoid Thread.sleep()
     * @param waitTimeOut
     */
    public void implicitlyWait(int waitTimeOut) {
        driverCommand.implicitWait(waitTimeOut);
    }

    /***
     * Implementing is expected element visible functionality
     * @param locator
     * @param waitTimeOut
     * @param logMsg
     * @return boolean
     */
    public boolean isExpectedElementVisible(By locator, int waitTimeOut, String logMsg) {
        boolean status = true;
        try {
            explicitlyWait(waitTimeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " appeared", "pass");

        } catch (WebDriverException exception) {
            status = false;
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " did not appear", "info");
            CommonUtility.logExceptionsToTheReport(exception);
            exception.printStackTrace();
        }
        return status;

    }

    /***
     * Implementing is not expected element visible functionality
     * @param locator
     * @param waitTimeOut
     * @param logMsg
     * @return boolean
     */
    public boolean isNotExpectedElementVisible(By locator, int waitTimeOut, String logMsg) {
        boolean status = true;
        try {
            explicitlyWait(waitTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(locator));
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " - successful", "pass");

        } catch (WebDriverException exception) {
            status = false;
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " - failed", "warn");
            CommonUtility.logExceptionsToTheReport(exception);
            exception.printStackTrace();
        }
        return status;

    }

    /***
     * Method to get text of a locator (default waitTimeOut is set to 30 sec)
     * @param locator
     * @return text of the locator if it's available
     */
    public String getText(By locator, String logMsg) {
        try {
            if (isExpectedElementVisible(locator, 30, logMsg)) {
                return driverCommand.getDriver().findElement(locator).getText();
            } else {
                return locator + " did not appear";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            CommonUtility.logMessagesAndAddThemToReport(exception.getMessage(), "error");
        }
        return null;
    }

    /***
     * Method to get text of a locator
     * @param locator
     * @param waitTimeOut
     * @return
     */
    public String getText(By locator, int waitTimeOut, String logMsg) {
        if (isExpectedElementVisible(locator, waitTimeOut, logMsg)) {
            return driverCommand.getDriver().findElement(locator).getText();
        } else {
            return locator + " did not appear";
        }

    }

    /**
     * Method to write text on field (default waitTimeOut is set to 30 sec)
     *
     * @param locator
     * @param text
     * @param logMsg
     */
    public void sendKeys(By locator, String text, String logMsg) {
        if (isExpectedElementVisible(locator, 30, logMsg)) {
            driverCommand.getDriver().findElement(locator).sendKeys(text);
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " typed", "info");
        }

    }

    /**
     * Method to write text on field
     *
     * @param locator
     * @param text
     * @param wait
     * @param logMsg
     */
    public void sendKeys(By locator, String text, int wait, String logMsg) {
        if (isExpectedElementVisible(locator, wait, logMsg)) {
            driverCommand.getDriver().findElement(locator).sendKeys(text);
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " typed", "info");
        }

    }

    /***
     * Method to click on a locator (default waitTimeOut is set to 30 sec)
     * @param locator
     */
    public void clickOn(By locator, String logMsg) {
        if (isExpectedElementVisible(locator, 30, logMsg)) ;
        {
            try {
                driverCommand.getDriver().findElement(locator).click();
                CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked", "pass");
            } catch (Exception e) {
                // TODO need to redesign this method.
                JavascriptExecutor executor = (JavascriptExecutor) driverCommand.getDriver();
                executor.executeScript("arguments[0].click();", driverCommand.getDriver().findElement(locator));
                CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked by Java Script", "pass");
            }
        }
    }

    /**
     * Method to click on a locator
     *
     * @param locator
     * @param waitTimeOut
     * @param logMsg
     */
    public void clickOn(By locator, int waitTimeOut, String logMsg) {
        if (isExpectedElementVisible(locator, waitTimeOut, logMsg)) {
            try {
                driverCommand.getDriver().findElement(locator).click();
                CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked", "pass");
            } catch (Exception e) {
                try {
                    JavascriptExecutor executor = (JavascriptExecutor) driverCommand.getDriver();
                    executor.executeScript("arguments[0].click();", driverCommand.getDriver().findElement(locator));
                    CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked by Java Script", "pass");
                } catch (Exception ex) {
                    WebDriver webDriver = Browser.getWebDriver();
                    WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(30));
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                    new Actions(webDriver).moveToElement(element)
                            .click()
                            .perform();
                    CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked using Action class", "pass");
                }

            }
        }

    }

    /***
     * Method to verify the click on a locator (default waitTimeOut is set to 30 sec)
     * @param locator
     */
    public boolean clickVerify(By locator, String logMsg) {
        boolean status = true;

        if (isExpectedElementVisible(locator, 30, logMsg)) {
            try {
                driverCommand.getDriver().findElement(locator).click();
                CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked", "pass");
            } catch (Exception e) {
                JavascriptExecutor executor = (JavascriptExecutor) driverCommand.getDriver();
                executor.executeScript("arguments[0].click();", driverCommand.getDriver().findElement(locator));
                CommonUtility.logMessagesAndAddThemToReport(logMsg + " clicked by Java Script", "pass");
            }
        } else {
            status = false;
        }
        return status;

    }

    /***
     * Method to click with java script
     * @param locator
     */
    public void clickWithJavaScript(By locator) {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("arguments[0].click();", driverCommand.getDriver().findElement(locator));

    }

    /***
     * Method to get an attribute value of a locator (default time is set to 30 sec)
     * @param locator
     * @param attributeName
     * @param logMsg
     * @return
     */
    protected String getAttributeValue(By locator, String attributeName, String logMsg) {
        if (isExpectedElementVisible(locator, 30, logMsg)) {
            String attributeValue = driverCommand.getDriver().findElement(locator).getAttribute(attributeName);
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " is found as-" + attributeValue, "info");
            return attributeValue;
        } else {
            return locator + " did not appear";
        }
    }

    /***
     * Method to get an attribute value of a webElement (default time is set to 30 sec)
     * @param webElement
     * @param attributeName
     * @param logMsg
     * @return
     */
    protected String getAttributeValue(WebElement webElement, String attributeName, String logMsg) {
        try {
            String attributeValue = webElement.getAttribute(attributeName);
            CommonUtility.logMessagesAndAddThemToReport(logMsg + " is found as-" + attributeValue, "info");
            return attributeValue;
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return "";
    }

    /***
     * Method to know if an attribute is present in the locator (default time is set to 10 sec)
     * @param locator the locator in which the attribute will be searched for
     * @param attributeName the attribute name to be searched for
     * @return true if the attribute is present, otherwise false
     */
    protected boolean isAttributePresent(By locator, String attributeName, String logMsg) {
        if (isExpectedElementVisible(locator, 10, logMsg)) {
            String value = driverCommand.getDriver().findElement(locator).getAttribute(attributeName);
            return value != null;
        }
        return false;
    }

    /***
     * Method to know if an attribute is present in the webElement (default time is set to 10 sec)
     * @param webElement the webElement in which the attribute will be searched for
     * @param attributeName the attribute name to be searched for
     * @return true if the attribute is present, otherwise false
     */
    protected boolean isAttributePresent(WebElement webElement, String attributeName) {
        try {
            String value = webElement.getAttribute(attributeName);
            return value != null;
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return false;
    }

    /***
     * Returns the selected option of the dropdown
     * @param locator
     * @param logMsg
     * @return the selected option of the dropdown
     */
    protected String getSelectedDropdownValue(By locator, String logMsg) {
        try {
            if (isExpectedElementVisible(locator, 30, logMsg)) {
                WebDriver webDriver = Browser.getWebDriver();
                WebElement element = webDriver.findElement(locator);
                Select selObj = new Select(element);
                WebElement option = selObj.getFirstSelectedOption();
                String selectedOption = option.getText();
                CommonUtility.logMessagesAndAddThemToReport("The selected option of dropdown is " +
                        selectedOption, "info");
                return selectedOption;
            } else {
                return locator + " did not appear";
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtility.logMessagesAndAddThemToReport("Could not find the selected dropdown option. error " +
                    "message: " + e.getMessage(), "error");
            return "Could not find the selected dropdown option. Error occurred!";
        }
    }

    /***
     * Method to explore menus/options by hovering mouse cursor on an element.
     * @param locator
     */
    public void MouseHover(By locator) {
        Actions actObj = new Actions(driverCommand.getDriver());
        actObj.moveToElement(driverCommand.getDriver().findElement(locator)).build().perform();

    }

    /***
     * Method to get timestamp
     * @return e.g May28201513_15_10 PM
     */
    public String GetTimeStamp() {
        Format f = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        String DateValue = f.format(new Date());
        return DateValue;

    }

    /***
     * Method to sleep for sec
     * @param sec Time in second
     */
    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /***
     * Method to scroll vertically till a locator appears
     * @param locator
     */
    public void scrollVerticallyTillAppears(By locator) {
        try {
            ((JavascriptExecutor) driverCommand.getDriver()).executeScript("arguments[0].scrollIntoView(true);", driverCommand.getDriver().findElement(locator));
            sleep(2);
        } catch (Exception e) {
            WebDriver webDriver = Browser.getWebDriver();
            WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            Actions actions = new Actions(webDriver);
            actions.moveToElement(element);
            actions.perform();
        }


    }

    /***
     * Method to scroll vertically till an element appears
     * @param element
     */
    public void scrollVerticallyTillAppears(WebElement element) {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(2);

    }

    /***
     * Method to scroll horizontally till a locator appears
     * @param locator
     */
    public void scrollHorizontallyTillAppears(By locator) {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("document.querySelector('table th:last-child').scrollIntoView();", driverCommand.getDriver().findElement(locator));
        sleep(2);

    }

    /***
     * Method to scroll horizontally till an element appears
     * @param element
     */
    public void scrollHorizontallyTillAppears(WebElement element) {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("document.querySelector('table th:last-child').scrollIntoView();", element);
        sleep(2);

    }

    /***
     * Method to get ByTyoe of a WebElement
     * By format = "[foundFrom] -> locator: term"
     * @param element
     * @return
     */
    public By toByVal(WebElement element) {
        String[] data = element.toString().split(" -> ")[1].replace("]", "").split(": ");
        String locator = data[0];
        String term = data[1];

        if (locator.equals("xpath")) {
            return By.xpath(term);
        } else if (locator.equals("css selector")) {
            return By.xpath(term);
        } else if (locator.equals("id")) {
            return By.xpath(term);
        } else if (locator.equals("tag name")) {
            return By.xpath(term);
        } else if (locator.equals("name")) {
            return By.xpath(term);
        } else if (locator.equals("link text")) {
            return By.xpath(term);
        } else if (locator.equals("class name")) {
            return By.xpath(term);
        }
        return (By) element;

    }

    /***
     * Return string from By type excluding the "By.type: " part.
     * E.g. "By.xpath: //div" -> "//div"
     * @param locator
     * @return string
     */
    public String stringFromBy(By locator) {
        if (locator.toString().contains("By.xpath: ")) {
            return locator.toString().replace("By.xpath: ", "");
        } else if (locator.toString().contains("By.xpath: ")) {
            return locator.toString().replace("By.xpath: ", "");
        } else if (locator.toString().contains("By.className: ")) {
            return locator.toString().replace("By.className: ", "");
        } else if (locator.toString().contains("By.id: ")) {
            return locator.toString().replace("By.id: ", "");
        } else if (locator.toString().contains("By.linkText: ")) {
            return locator.toString().replace("By.linkText: ", "");
        } else if (locator.toString().contains("By.cssSelector: ")) {
            return locator.toString().replace("By.cssSelector: ", "");
        } else if (locator.toString().contains("By.partialLinkText: ")) {
            return locator.toString().replace("By.partialLinkText: ", "");
        } else {
            return locator.toString().replace("By.xpath: ", "");
        }

    }

    /**
     * To check if a new page is appeared in a new tab by matching with expected URL. Closes the new tab if the page is appeared.
     *
     * @param URL URL/Part of URL of the expected page
     */
    public void checkOpenedTab(String URL) {
        List<String> browserTabs = new ArrayList<String>(driverCommand.getDriver().getWindowHandles());
        driverCommand.getDriver().switchTo().window(browserTabs.get(1));
        Assert.assertTrue(driverCommand.getDriver().getCurrentUrl().contains(URL));
        driverCommand.getDriver().close();
        driverCommand.getDriver().switchTo().window(browserTabs.get(0));
    }

    /***
     * Method to get timestamp
     */
    public String GetCurrentDate() {
        Format f = new SimpleDateFormat("MM/dd/yyyy");
        String date = f.format(new Date());
        return date;

    }

    /***
     * Method to get toast message
     */
    public void MatchToastMessageValue(String value) {
        isExpectedElementVisible(By.xpath("//div[@class='toast-message body']"), 60,
                "Toast Message: " + value);
        Assert.assertTrue(isExpectedElementVisible(By.xpath("//div[@class='toast-message body' and text()='"
                + value + "']"), 60, "Proper toast message: " + value));

    }

    /**
     * method to check if toast message disappeared
     */
    public void successfulToastMessageDisappear() {
        isNotExpectedElementVisible(By.xpath("//div[@class='toast-message body']"), 60, "Successful Message Disappear");
    }

    /***
     * Method to verify that toast message did not appear
     * @param value This string contains the toast message that should not appear
     */
    public void checkToastMessageDidNotAppear(String value) {
        Assert.assertTrue(isNotExpectedElementVisible(By.xpath("//div[@class='toast-message body' and text()='"
                + value + "']"), 20, "'" + value + "'" + " toast message should not appear"));

    }

    /***
     * Method to clear the text of a text box
     */
    public void clearText(By locator) {
        try {
            driverCommand.getDriver().findElement(locator).clear();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtility.logExceptionsToTheReport(e);
        }
    }

    /**
     * Method to verify if user is successfully navigated to a specific list page
     *
     * @param accessValue (Yes/No)
     * @param locator
     */
    public void verifyListPageNavigation(String accessValue, By locator, String logMsg) {
        if (accessValue.toLowerCase().equals("yes")) {
            try {
                Assert.assertTrue(isExpectedElementVisible(locator, 30, logMsg));
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtility.logExceptionsToTheReport(e);
            }

        } else if (accessValue.toLowerCase().equals("no")) {
            try {
                Assert.assertTrue(isNotExpectedElementVisible(locator, 10, logMsg + " should not appear - "));
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtility.logExceptionsToTheReport(e);
            }

        } else {
            CommonUtility.logMessagesAndAddThemToReport("Wrong text provided in the CSV file", "fail");
            Assert.fail();

        }

    }

    /**
     * Method to verify if user cab navigate to a sub page
     *
     * @param accessValue (Yes/No)
     * @param locator
     */
    public void verifySubPageNavigation(String accessValue, By locator, String logMsg) {
        if (accessValue.toLowerCase().equals("yes")) {
            try {
                Assert.assertTrue(clickVerify(locator, logMsg));
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtility.logExceptionsToTheReport(e);

            }

        } else if (accessValue.toLowerCase().equals("no")) {
            try {
                Assert.assertTrue(isNotExpectedElementVisible(locator, 10, logMsg + " should not appear - "));
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtility.logExceptionsToTheReport(e);
            }

        } else {
            CommonUtility.logMessagesAndAddThemToReport("Wrong text provided in the CSV file", "fail");
            Assert.fail();

        }

    }

    /***
     * Method to clear the text of a text box using control and delete button
     * @param locator
     */
    public void clearTextUsingKeyboard(By locator) {
        driverCommand.getDriver().findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

    }

    /**
     * Method to click on a button by the text that appears on the button
     *
     * @param text : The text on button
     */
    public void clickOnButton(String text) {
        By button = By.xpath("//button[text()='" + text + "']");
        try {
            clickOn(button, 30, text + " button");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     * Method to get the ID of a created data
     */
    public String getID(String splitText) {
        String currentUrl = Browser.getCurrentUrl();
        String[] text = currentUrl.split(splitText);
        String id = text[1];
        return id;
    }

    /***
     * Method to get open a new tab in the browser window
     */
    public void openNewTab() {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("window.open()");

    }

    /***
     * Method to change the tab to the desired tab
     * @param tabNum Number of the tab the user wants to change to
     */
    public void changeTabFocus(int tabNum) {
        ArrayList<String> tabs = new ArrayList<String>(driverCommand.getDriver().getWindowHandles());
        driverCommand.getDriver().switchTo().window(tabs.get(tabNum));

    }

    /***
     * Method to close the current tab
     */
    public void closeCurrentTab() {
        ((JavascriptExecutor) driverCommand.getDriver()).executeScript("window.close()");

    }

    /***
     * Method to upload file
     * @param locator Locator of 'input' type element
     * @param filePath Path of the file which is to be uploaded
     * */
    public void uploadFile(By locator, String filePath) {
        driverCommand.getDriver().findElement(locator).sendKeys(filePath);

    }

    /***
     * Method to get number of opened tabs
     * @return No of tabs opened
     */
    public int getNumberOfOpenedTabs() {
        ArrayList<String> tabs = new ArrayList<String>(driverCommand.getDriver().getWindowHandles());
        return tabs.size();

    }

    /***
     * Method to navigate backwards by using browser back button
     */
    public void navigateBackwardsByUsingBrowserBackButton() {
        try {
            Browser.clickOnBrowserBackButton();
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /***
     * Method to get tooltip
     */
    public String getTooltip(By locator) {
        return driverCommand.getDriver().findElement(locator).getAttribute("title");
    }

    /***
     * Method to get the title of the Page from browser tab
     */
    public String getTitleOfAPage() {
        String pageTitle = null;

        try {
            pageTitle = Browser.getTitleOfCurrentPage();
        } catch (Exception exception) {
            exception.printStackTrace();
            CommonUtility.logExceptionsToTheReport(exception);
        }
        return pageTitle;
    }

    /***
     * Method to verify if the button is in enable state or disable state
     */
    public boolean is_enable(By locator) {
        try {
            Boolean buttonEnableStatus = driverCommand.getDriver().findElement(locator).isEnabled();
            return buttonEnableStatus;
        } catch (Exception exception) {
            exception.printStackTrace();
            CommonUtility.logExceptionsToTheReport(exception);
        }
        return false;
    }

    /***
     * Method to get all web elements of a locator
     * @param locator locators of desired web elements
     * @return all web elements of a locator
     */
    protected List<WebElement> searchElements(By locator) {
        try {
            return driverCommand.getDriver().findElements(locator);
        } catch (Exception e) {
            CommonUtility.logMessagesAndAddThemToReport(e.getMessage(), "error");
            return null;
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
    protected void fluentWait(By locator, String attributeName, String expectedValueOfAttribute,
                              int waitTime, int pollingFrequency) {
        CommonUtility.logMessagesAndAddThemToReport("Timer started", "Info");
        driverCommand.fluentWait(locator, attributeName, expectedValueOfAttribute, waitTime, pollingFrequency);
        CommonUtility.logMessagesAndAddThemToReport("Timer ended", "Info");
    }

    /***
     * Method to get toast message content
     *
     * @return returns the content of the toast message
     */
    public String getToastMessageContent() {
        return getText(By.cssSelector(".toast-message"), "getToastMessageContent");
    }

    /***
     * Method to get toast message type
     *
     * @return returns the type of the toast message
     */
    public String getToastMessageType() {
        String classValue = getAttributeValue(By.cssSelector(".toasts i.icon"), "class",
                "toast message icon class");

        if (classValue.contains("success")) {
            return SUCCESS_TOAST_TYPE;
        } else if (classValue.contains("warning")) {
            return WARNING_TOAST_TYPE;
        } else if (classValue.contains("error")) {
            return ERROR_TOAST_TYPE;
        } else {
            return null;
        }
    }

}
