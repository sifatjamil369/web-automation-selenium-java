package main.helper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    private static Object[][] data = null;
    private static CSVReader csvReader;
    private static CSVReader forColumns;

    /***
     * This method will read the CSV file, initialize the CSVReaders and size of DATA
     * @param fileName Name of the CSV
     */
    private static void readCSV(String fileName) {
        String csvFile = System.getProperty("user.dir") + "\\CSVs\\" + fileName; // Providing file location
        try {
//            csvReader = new CSVReader(new FileReader(csvFile), ',' , '"' , 1);
//            forColumns = new CSVReader(new FileReader(csvFile), ',' , '"' , 1);
            csvReader = new CSVReader(new FileReader(csvFile));
            forColumns = new CSVReader(new FileReader(csvFile));

            int columnCount = 0;

            String[] header = forColumns.readNext();
            if (header != null) {
                columnCount = header.length;
            }
            System.out.println("Number of Columns: " + columnCount);
            forColumns.close();

            data = new Object[1][columnCount];

        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /***
     * This method will return the database credentials
     * @param getCredentials : Name of the row of the csv file
     */
    private static void getDatabaseCredentials(String getCredentials) {
        try {
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!= null) {
                if (nextRecord[0].equals(getCredentials)) {
                    for (int i = 0; i<nextRecord.length; i++) {
                        data[0][i] = nextRecord[i];
                    }
                }
            }
            csvReader.close();
        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    /***
     * This method will return the data you want from the CSV file
     * @param roleName Role of the user, whose data you are looking for
     */
    private static void getRoleSpecificData(String roleName) {
        try {
            // Reading Records One by One in a String array
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].equals(roleName))
                {
                    for (int i = 0; i<nextRecord.length; i++)
                    {
                        data[0][i] = nextRecord[i];
                        System.out.println("TEST DATA :" + data[0][i]);
                    }
                }
            }
            csvReader.close();
        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

    }

    /***
     * This method will provide you page access data based on user role
     * @param fileName name of the CSV file
     * @param roleName name of the role
     * @return data in an array
     */
    public static Object[][] getRoleSpecificData(String fileName, String roleName){
        readCSV(fileName);
        getRoleSpecificData(roleName);
        return data;

    }

    /***
     * This method will provide you credentials of different user roles
     * @param fileName name of the CSV file
     * @param roleName name of the role
     * @return data in an array
     */
    public static Object[][] getRoleSpecificCredentials(String fileName, String roleName){
        readCSV(fileName);
        getRoleSpecificData(roleName);
        return data;

    }

    /***
     * This method will provide the database credentials for DB connection
     * @param fileName name of the csv file
     * @param getCredentials name of the row of the csv file that contains the credentials
     * @return data in an array
     */
    public static Object[][] getDatabaseCredentials(String fileName, String getCredentials) {
        readCSV(fileName);
        getDatabaseCredentials(getCredentials);
        return data;
    }

}
