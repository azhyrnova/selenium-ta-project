package tests;

import helpers.UserCreator;
import model.User;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest extends BaseTest {

    @Test(priority = 0, description = "Standard user should login")
    public void standardUserShouldLogin() {
        LoginPage loginPage = new LoginPage(driver);

        User testUser = UserCreator.withStandardCredentials();

        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);

        String actualTitle = productsPage.getTextFromPageTitle();
        String expectedTitle = "Products";

        assertThat(expectedTitle.equalsIgnoreCase(actualTitle));
    }

    @Test(priority = 1, description = "User should not login with wrong credentials")
    public void userShouldNotLoginWithWrongCredentials(){
        LoginPage loginPage = new LoginPage(driver);

        User testUser = UserCreator.withWrongCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();
        String errorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage()+1);
    }

    @Test
    public void lockedOutUserShouldNotLogin(){
        LoginPage loginPage = new LoginPage(driver);

        User testUser = UserCreator.lockedOut();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();
        String errorMessage = "Epic sadface: Sorry, this user has been locked out.";
        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }
}