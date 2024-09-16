package tests;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import util.TestListener;
import driver.DriverManager;
import helpers.ConfigurationManager;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public abstract class BaseTest {
    protected WebDriver driver = DriverManager.getDriver();
    protected final int WAIT_TIMEOUT_SECONDS =  Integer.parseInt(ConfigurationManager.getProperty("webdriver.timeout.seconds"));

    static {
        File allureResults = new File("target/allure-results");
        if(allureResults.isDirectory()) {
            for (File item: Objects.requireNonNull(allureResults.listFiles())) {
                item.delete();
            }
        }
    }
    abstract protected void initPages();

    @BeforeMethod
    public void setUp()  {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        initPages();
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
