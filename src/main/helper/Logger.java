package main.helper;

public class Logger {
    // Initialize Log4j logs
    private static org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(Logger.class.getName());
    private static org.apache.log4j.Logger databaseLogger = org.apache.log4j.Logger.getLogger("DatabaseLogger");

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName){
        Logger.info("Test Case : "+sTestCaseName+ " starts here");

    }

    //This is to print log for the ending of the test case

    public static void endTestCase(String sTestCaseName){
        Logger.info("Test Case : "+sTestCaseName+ " ends here");
        Logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    }

    // Need to create these methods, so that they can be called

    public static void info(String message) {
        Logger.info(message);

    }

    public static void warn(String message) {
        Logger.warn(message);

    }

    public static void error(String message) {
        Logger.error(message);

    }

    public static void fatal(String message) {
        Logger.fatal(message);

    }

    public static void debug(String message) {
        Logger.debug(message);

    }

    static void databaseLog(String message) {
        databaseLogger.info(message);

    }

    static void databaseLogWarn(String message) {
        databaseLogger.warn(message);

    }

}
