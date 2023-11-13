package main.helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentTest test;
    private static ExtentTest node;
    private static ExtentTest subNode;
    private static ExtentTest logger;
    private static ExtentReports extentReports;
    private static String reportPath;

    /***
     * Method to set path to where the report will be generated
     * @param folderName Name of the folder in which the report will be generated
     */
    public static void setPathForReportGeneration(String folderName) {
        Format rf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String fullStamp = rf.format(new Date());
        if (folderName.toLowerCase().contains("user")) {
            reportPath = System.getProperty("user.dir") + "\\TestReports\\Reports_User Module" + "\\" + folderName + "\\" + fullStamp + "\\";
        }
        else if(folderName.toLowerCase().contains("action access")) {
            reportPath = System.getProperty("user.dir") + "\\TestReports\\Reports_Action Access" + "\\" + folderName + "\\" + fullStamp + "\\";
        }
        else {
            reportPath = System.getProperty("user.dir") + "\\TestReports\\Reports_" + folderName + "\\" + fullStamp + "\\";
        }


    }

    /***
     * Method to return the path to where the report will be generated
     * @return reportPath : Path to where the report will be generated
     */
    public static String getPathForReportGeneration() {
        return reportPath;

    }

    /***
     * Method to generate the Extent Html Report
     * @param path Path to where the report will be generated
     * @param reportName Name of the report
     */
    public static void generateExtentHtmlReport(String path, String reportName) {
        String reportPath = path + reportName + ".html";
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setAutoCreateRelativePathMedia(true);
        extentHtmlReporter.config().setDocumentTitle(reportName);
        extentHtmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\main\\resources\\" + "extent-config.xml");

    }

    /***
     * Method to get which logger object the log messages should be written to (that is under which test case or node,
     * the log messages will be written)
     */
    private static ExtentTest getLoggerObject() {
        ExtentTest loggerObject = null;
        if(subNode == null && node == null && test != null) {
            loggerObject = test;
        }
        else if(subNode == null && node != null && test != null) {
            loggerObject = node;
        }
        else {
            loggerObject = subNode;
        }
        return loggerObject;

    }

    /***
     * Method to create a ExtentTest object for logging mechanism
     * @param testName Name of the test to be displayed in the report
     * */
    public static void logCreateTest(String testName) {
        test = extentReports.createTest(testName);
        node = null;
        subNode = null;

    }

    /***
     * Method to create ExtentTest object for creating a node inside a test case in Extent reporter
     * @param testName Name of the test method to be displayed in the report
     */
    public static void logCreateNode(String testName) {
        node = test.createNode(testName);
        subNode = null;

    }

    /***
     * Method to create ExtentTest object for a creating a child node inside another node in Extent Reporter
     * @param testName Name of the test method to be displayed in the report
     */
    public static void logCreateSecondNode(String testName) {
        subNode = node.createNode(testName);

    }

    /***
     * Method to log info message
     * @param message Message to be logged
     * */
    public static void logInfo(String message) {
        logger = getLoggerObject();
        logger.log(Status.INFO, message);

    }

    /***
     * Method to log PASS message
     * @param message Message to be logged
     * */
    public static void logPass(String message) {
        logger = getLoggerObject();
        logger.log(Status.PASS, message);

    }

    /***
     * Method to log FAILED message
     * @param message Message to be logged
     * */
    public static void logFail(String message) {
        logger = getLoggerObject();
        logger.log(Status.FAIL, message);

    }

    /***
     * Method to log exception messages
     * @param e exception to be logged
     * */
    public static void logExceptions(Exception e) {
        logger = getLoggerObject();
        logger.warning(e);

    }

    /***
     * Method to log WARN message
     * @param message Message to be logged
     */
    public static void logWarn(String message) {
        logger = getLoggerObject();
        logger.log(Status.WARNING, message);

    }

    /***
     * Method to log FATAL message
     * @param message Message to be logged
     */
    public static void logFatal(String message) {
        logger = getLoggerObject();
        logger.log(Status.FATAL, message);

    }

    /***
     * Method to log ERROR message
     * @param message Message to be logged
     */
    public static void logError(String message) {
        logger = getLoggerObject();
        logger.log(Status.ERROR, message);

    }

    /***
     * Method to log DEBUG message
     * @param message Message to be logged
     */
    public static void logDebug(String message) {
        logger = getLoggerObject();
        logger.log(Status.DEBUG, message);

    }

    /***
     * Method to add screenshot to the report
     * @param imageName : Name of the image to be added to the screenshot
     * */
    public static void addScreenshot(String imageName) throws IOException {
        logger = getLoggerObject();
        logger.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(imageName).build());

    }

    /***
     * Method to mark a test case as passed
     * @param message message to be logged
     */
    public static void markATestCaseAsPassed(String message) {
        logger = getLoggerObject();
        logger.pass(message);

    }

    /***
     * Method to add exceptions thrown by assertions to the report and mark test case as failed
     * @param iTestResult result of the test case
     */
    public static void onFailureAddAssertionExceptionsToReport(ITestResult iTestResult) {
        logger = getLoggerObject();
        logger.fail(iTestResult.getThrowable());

    }

    /***
     * Method to add or append test information to the report
     */
    public static void flushReport() {
        extentReports.flush();

    }

}
