/*package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

*//*
public class DriverFactory {

    public static WebDriver create() {
        switch (System.getProperty("browser")){
            case "firefox" -> {
               return WebDriverManager.firefoxdriver().create();
            }
            case "chrome", default -> {
                return  WebDriverManager.chromedriver().create();
            }
        }
    }
}
*/
