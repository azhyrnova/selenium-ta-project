package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    protected final String PAGE_URL = "https://www.saucedemo.com";

    @FindBy(id = "user-name")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public LoginPage enterUsername(final String username) {
        inputLogin.clear();
        inputLogin.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(final String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public String getErrorMessage() {
        return driver
                .findElement(By.className("error-message-container"))
                .findElement(By.tagName("h3"))
                .getText();
    }

    public void clickLogin() {
        loginButton.click();
    }
}
