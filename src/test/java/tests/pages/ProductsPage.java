package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends AbstractPage {

    protected final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(xpath = "//span[contains(text(), 'Products')]")
    private WebElement productsPageTitle;

    @FindBy(css = ".error-button")
    private WebElement errorButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductsPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public String getTextFromPageTitle() {
        return productsPageTitle.getText();
    }

    //rethink
    @Override
    public String getErrorMessage() {
        return driver
                .findElement(By.className("error-message-container"))
                .findElement(By.tagName("h3"))
                .getText();
    }


}
