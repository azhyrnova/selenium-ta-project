package tests;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import util.TestListener;
import helpers.ConfigurationManager;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public abstract class BaseTest {
    protected final int WAIT_TIMEOUT_SECONDS =  Integer.parseInt(ConfigurationManager.getProperty("webdriver.timeout.seconds"));

    static {
        File allureResults = new File("target/allure-results");
        if(allureResults.isDirectory()) {
            for (File item: Objects.requireNonNull(allureResults.listFiles())) {
                item.delete();
            }
        }
    }
    @BeforeMethod
    public void setUp()  {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = WAIT_TIMEOUT_SECONDS;
    }

    @BeforeSuite
    public void readProperties() throws IOException{
        ConfigurationManager.loadProperties();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){
        Configuration.holdBrowserOpen = false;
    }

    /*public WebDriver getDriver(){
        return driver;
    }
*/
    abstract void initPages();
}
