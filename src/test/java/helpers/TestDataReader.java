package helpers;

import java.util.ResourceBundle;

//do i need this?
public class TestDataReader {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
