package pages;

import helpers.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected final String PAGE_URL = ConfigurationManager.getProperty("base.url");

    //protected AbstractPage(){};

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
