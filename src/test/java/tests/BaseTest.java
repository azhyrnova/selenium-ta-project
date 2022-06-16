package tests;

import driver.DriverManager;
import helpers.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import util.TestListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public class BaseTest {
    protected static WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS =  Integer.parseInt(ConfigurationManager.getProperty("webdriver.timeout.seconds"));

    @BeforeMethod
    public void setUp()  {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void readProperties() throws IOException{
        ConfigurationManager.loadProperties();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser(){
        DriverManager.closeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
