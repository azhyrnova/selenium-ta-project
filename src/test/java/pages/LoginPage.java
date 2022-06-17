package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends AbstractPage {

    private SelenideElement inputLogin =$("#user-name");
    private SelenideElement inputPassword =$("#password");

    private SelenideElement loginButton =$("#login-button");

    private SelenideElement errorBox =$x("//h3[@data-test='error']");

    public LoginPage() {
        //return page(LoginPage.class);
        //PageFactory.initElements(this.driver, this);
    }

    @Step("Login page was opened")
    public LoginPage openPage() {
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
        loginButton.click();
        return page(ProductsPage.class);
    }
}
