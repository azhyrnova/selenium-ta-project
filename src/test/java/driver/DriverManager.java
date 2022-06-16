package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager(){}

    public static WebDriver getDriver(){
        if (null == driver) {
            driver = DriverFactory.create();
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

}