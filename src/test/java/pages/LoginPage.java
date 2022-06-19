package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorBox;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("Login page was opened")
    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    @Step("Username was entered")
    public LoginPage enterUsername(final String username) {
        inputLogin.clear();
        inputLogin.sendKeys(username);
        return this;
    }

    @Step("Password was entered")
    public LoginPage enterPassword(final String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Error message was retrieved")
    public String getErrorMessage() {
        return errorBox.getText();
    }

    @Step("Login button was clicked")
    public ProductsPage clickLogin() {
        ProductsPage productsPage = new ProductsPage(driver);
        loginButton.click();
        return productsPage;
    }
}
