package main.helper;

import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CommonUtility {
    private static String fileNameForLoggedInUserDetails = "loggedInUserDetails.txt";

    /***
     * Method to log messages and add them to the report
     * @param message Message to be logged
     * @param status Status of the log message (pass, fail, warn, fatal etc.)
     * */
    public static void logMessagesAndAddThemToReport(String message, String status) {
        Logger.info(message);
        switch (status.toLowerCase()) {
            case "pass":
                ExtentReportManager.logPass(message);
                break;
            case "fail":
                ExtentReportManager.logFail(message);
                break;
            case "info":
                ExtentReportManager.logInfo(message);
                break;
            case "warn":
                Logger.warn(message);
                ExtentReportManager.logWarn(message);
                break;
            case "fatal":
                Logger.fatal(message);
                ExtentReportManager.logFatal(message);
                break;
            case "error":
                Logger.error(message);
                ExtentReportManager.logError(message);
                break;
            case "debug":
                Logger.debug(message);
                ExtentReportManager.logDebug(message);
                break;
        }
    }

    /***
     * Method to log exceptions to the report
     * @param e exception to be logged
     * */
    public static void logExceptionsToTheReport(Exception e) {
        ExtentReportManager.logExceptions(e);

    }

    /***
     * Method to log start test case and add a test case to the report
     * @param testName Name of the test case to be logged
     */
    public static void logCreateTest(String testName) {
        Logger.startTestCase(testName);
        ExtentReportManager.logCreateTest(testName);

    }

    /***
     * Method to add a node under a test case to the report
     * @param testName Name of the test method to be logged
     */
    public static void logCreateNode(String testName) {
        ExtentReportManager.logCreateNode(testName);

    }

    /***
     * Method to add a child node under a node to the report
     * @param testName Name of the test method to be logged
     */
    public static void logCreateSubNode(String testName) {
        ExtentReportManager.logCreateSecondNode(testName);

    }

    /***
     * Method to save logged in users details to a text file
     * @param loggedInUserEmail email of the user
     * @param loggedInUserPassword password of the user
     */
    public static void saveLoggedInUserDetailsToAFile(String loggedInUserEmail, String loggedInUserPassword) {
        deleteLoggedInUserDetailsFile();
        String textToWriteToAFile = "Email : " + loggedInUserEmail + "\r\n" + "Password : " + loggedInUserPassword;
        createAndWriteTextToAFile(textToWriteToAFile, fileNameForLoggedInUserDetails);

    }

    /***
     * Method to read logged in user details
     * @param emailOrPassword to read email or password (email/password)
     */
    public static String readLoggedInUserDetailsFile(String emailOrPassword) {
        String[] loggedInUserDetails = readFile(fileNameForLoggedInUserDetails);
        String lastLoggedInUserEmail = "";
        String lastLoggedInUserPassword = "";
        String returnUserDetails = "";
        for (String text : loggedInUserDetails) {
            if (text == null) {
                break;
            } else if (text.contains("Email : ")) {
                String[] splitText = text.split(": ");
                lastLoggedInUserEmail = splitText[1];
            } else if (text.contains("Password : ")) {
                String[] splitText = text.split(": ");
                lastLoggedInUserPassword = splitText[1];
            }
        }
        if (emailOrPassword.toLowerCase().equals("email")) {
            returnUserDetails = lastLoggedInUserEmail;
        } else if (emailOrPassword.toLowerCase().equals("password")) {
            returnUserDetails = lastLoggedInUserPassword;
        }
        return returnUserDetails;
    }

    /***
     * Method to create a file and write to a file
     * @param fileName name of the file
     * @param text Text to be written to the file
     */
    public static void createAndWriteTextToAFile(String text, String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                logMessagesAndAddThemToReport("New file created -" + fileName, "info");
            } else {
                logMessagesAndAddThemToReport("File could not be created or already exists - " + fileName, "info");
            }
            FileWriter fileWriter = new FileWriter(fileName, false);
            fileWriter.write(text);
            fileWriter.close();

        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }

    }

    /***
     * Method to read contents of a file
     * @param fileName name of the file
     */
    public static String[] readFile(String fileName) {
        String[] text = new String[10];
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                text[i] = scanner.nextLine();
                i++;
            }
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return text;
    }

    /***
     * Method to delete the logged in users details file
     */
    public static void deleteLoggedInUserDetailsFile() {
        deleteAFile(fileNameForLoggedInUserDetails);

    }

    /***
     * Method to delete a file
     * @param fileName name of the file
     */
    public static void deleteAFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                if (file.delete()) {
                    logMessagesAndAddThemToReport(fileName + " file deleted", "info");
                } else {
                    logMessagesAndAddThemToReport("File could not be deleted - " + fileName, "info");
                }

            } else {
                logMessagesAndAddThemToReport("File does not exist", "info");
            }
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }

    }

    /***
     * Method to check if a file exists
     * @return status if the file exists or not
     */
    public static Boolean checkIfLoggedInUserDetailsFileExists() {
        Boolean status = null;
        try {
            File file = new File(fileNameForLoggedInUserDetails);
            if (file.exists()) {
                status = Boolean.TRUE;
            } else if (!file.exists()) {
                status = Boolean.FALSE;
            }
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return status;

    }

    /**
     * Method to get the current date time stamp
     *
     * @param dateTimeFormat the desired format of the date time
     * @return returns the current date time in the desired format
     */
    public static String getCurrentDateTimeStamp(String dateTimeFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
            Date date = new Date();
            return formatter.format(date);
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }

    /**
     * Method to sort a list of strings in descending oder
     *
     * @param listToBeSorted a list of string that is needed to be sorted
     */
    public static void sortAListInDescendingOrder(List<String> listToBeSorted) {
        try {
            listToBeSorted.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /**
     * Method to sort a list of strings in ascending oder
     *
     * @param listToBeSorted a list of string that is needed to be sorted
     */
    public static void sortAListInAscendingOrder(List<String> listToBeSorted) {
        try {
            listToBeSorted.sort(String.CASE_INSENSITIVE_ORDER);
        } catch (Exception e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
    }

    /**
     * Format a time from a given format to given target format
     *
     * @param inputFormat    input date time format
     * @param inputTimeStamp input date time
     * @param outputFormat   output date time format
     * @return date time in the desired output format
     */
    public static String TimeStampConverter(final String inputFormat, String inputTimeStamp, final String outputFormat) {
        try {
            return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(
                    inputFormat).parse(inputTimeStamp));
        } catch (ParseException e) {
            logMessagesAndAddThemToReport(e.getMessage(), "error");
        }
        return null;
    }
}
