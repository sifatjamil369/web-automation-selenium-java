package main.testcases;

import main.browserutility.Browser;

import main.helper.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseTest {
    ConfigReader configReader = new ConfigReader();
    static String reportPath;
    static String suiteName;



    /***
     * Method to capture screenshot
     * @return imageName : Returns the name of the screenshot
     */
    private String captureScreenshot() throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
        Date date = new Date();
        String strDate = formatter.format(date);
        File srcfileObj = ((TakesScreenshot) Browser.getWebDriver()).getScreenshotAs(OutputType.FILE);
        String imageName = strDate + ".png";
        System.out.println(reportPath);
        String screenshotPath = reportPath + imageName;
        File DestFileObj = new File(screenshotPath);
        FileUtils.copyFile(srcfileObj, DestFileObj);
        return imageName;

    }

    /***
     * Implementing test setup functionality
     * Browser will be launched and will navigate to the portal URL
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext iTestContext) throws IOException {
        String url = configReader.url;
        suiteName = iTestContext.getCurrentXmlTest().getSuite().getName();
        ExtentReportManager.setPathForReportGeneration(suiteName);
        reportPath = ExtentReportManager.getPathForReportGeneration();
        ExtentReportManager.generateExtentHtmlReport(reportPath, suiteName);
        CommonUtility.logCreateTest("Test Set Up");
        CommonUtility.logMessagesAndAddThemToReport("TEST EXECUTION STARTED", "info");
        CommonUtility.logCreateNode("Launch Browser and website");
        Browser.launchBrowser("chrome");
        CommonUtility.logMessagesAndAddThemToReport("Chrome Browser Launched", "info");
        Browser.goToUrl(url);
        CommonUtility.logMessagesAndAddThemToReport("Typed in url for Portal: " + url, "info");
    }


    public void refreshBrowser() {
        try {
            Browser.refreshBrowserPage();
        } catch (Exception exception) {
            exception.printStackTrace();
            CommonUtility.logExceptionsToTheReport(exception);
        }
    }

    /***
     * Implementing test tear down functionality
     * Browser focus will be closed and will quit the browser
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        CommonUtility.logCreateTest("Test Tear Down");

        CommonUtility.logCreateNode("Close Browser");
        Browser.closeFocusedScreen();
        Browser.quitBrowser();
        CommonUtility.logMessagesAndAddThemToReport("Browser Closed", "info");
        CommonUtility.logMessagesAndAddThemToReport("TEST EXECUTION FINISHED", "info");
        ExtentReportManager.flushReport();
    }


    /***
     * Method to log a test case as passed/failed and add it to google sheets. Also add screenshots to report in case of failure
     * @param results result of the test method
     */
    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(ITestResult results, ITestContext iTestContext) throws IOException {
        CommonUtility.logMessagesAndAddThemToReport("Suite Name : " + suiteName, "info");
        String testMethodName = results.getMethod().getMethodName();
        CommonUtility.logMessagesAndAddThemToReport("Method Name:" + testMethodName, "info");

        String tcID = getTCIDFromTestMethodName(testMethodName);
        CommonUtility.logMessagesAndAddThemToReport("TC ID : " + tcID, "info");
        if (ITestResult.FAILURE == results.getStatus()) {
            if (tcID.contains("TC")) {
                ExtentReportManager.onFailureAddAssertionExceptionsToReport(results);
                ExtentReportManager.logFail(" TC failed");
                String imageName = captureScreenshot();
                ExtentReportManager.addScreenshot(imageName);
            }
        } else if (ITestResult.SUCCESS == results.getStatus()) {
            if (tcID.contains("TC")) {
                ExtentReportManager.markATestCaseAsPassed("TC passed");
            }
        } else if (ITestResult.SKIP == results.getStatus()) {
            if (tcID.contains("TC")) {
                ExtentReportManager.logWarn("TC ignored");
            }
        }

    }


    /***
     * Method to get TC ID from te test method name
     * @param testMethodName name of @test method
     * @return TC ID
     */
    public String getTCIDFromTestMethodName(String testMethodName) {
        String[] tcID = new String[10];
        if (testMethodName.contains("_")) {
            tcID = testMethodName.split("_");
            if (!tcID[0].contains("TC")) {
                CommonUtility.logMessagesAndAddThemToReport("Test method name does not contain TC ID", "info");
                tcID[0] = " ";
            }
        } else {
            CommonUtility.logMessagesAndAddThemToReport("Test method name does not contain TC ID", "info");
            tcID[0] = " ";
        }
        return tcID[0];
    }
}
