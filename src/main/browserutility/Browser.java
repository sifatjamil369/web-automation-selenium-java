package main.browserutility;

import main.helper.CommonUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public final class Browser {

    private static WebDriver webDriver;

    /***
     * Implementing luanch browser functionality
     * @param browserName
     */
    public static void launchBrowser(String browserName) {
        String driverPath = "src\\main\\resources\\webdrivers\\";
        try {
            if (browserName.equalsIgnoreCase("ie")) {
                webDriver = new InternetExplorerDriver();

            } else if (browserName.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                webDriver = new ChromeDriver();

            } else if (browserName.equalsIgnoreCase("firefox")) {
                webDriver = new FirefoxDriver();
            }

            webDriver.manage().window().maximize();
        } catch (Exception exception){
            exception.printStackTrace();
            CommonUtility.logExceptionsToTheReport(exception);
        }
    }

    /***
     * Implementing get webdriver functionality
     * @return
     */
    public static WebDriver getWebDriver() {
        return webDriver;

    }

    /***
     * Implementing goToUrl functionality
     * @param url
     */
    public static void goToUrl(String url){
        webDriver.get(url);

    }

    /***
     * Implementing closeFocusedScreen functionality
     */
    public static void closeFocusedScreen(){
        webDriver.close();

    }

    /***
     * Method to get url of the current page
     */
    public static String getCurrentUrl(){
        return webDriver.getCurrentUrl();

    }

    /***
     * Method to click on browser back button
     */
    public static void clickOnBrowserBackButton() {
        webDriver.navigate().back();
    }

    /***
     * Method to refresh a browser page
     */
    public static void refreshBrowserPage() {
        webDriver.navigate().refresh();

    }

    /***
     * Implementing quitBrowser functionality
     */
    public static void quitBrowser(){
        webDriver.quit();

    }

    /***
     * Implementing getTitle functionality
     */
    public static String getTitleOfCurrentPage(){
        String titleOfCurrentPage =webDriver.getTitle();
        return titleOfCurrentPage;
    }
}
