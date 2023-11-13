package main.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public String url;
    public String testDataFilePath;

    public ConfigReader() {
        Properties prop = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
            url = prop.getProperty("url");
            testDataFilePath = prop.getProperty("testDataFilePath");
        } catch (IOException e) {
            e.printStackTrace();
            CommonUtility.logExceptionsToTheReport(e);
        }
    }
}
