package pages;

import helpers.ConfigurationManager;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS =  Integer.parseInt(ConfigurationManager.getProperty("webdriver.timeout.seconds"));

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
