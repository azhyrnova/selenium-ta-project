package tests;

import helpers.CommonConditions;
import helpers.UserCreator;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.pages.LoginPage;
import tests.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest extends CommonConditions {

    @Test
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

        Assert.assertTrue(expectedTitle.equalsIgnoreCase(actualTitle));

    }

    @Test
    public void userShouldNotLoginWithWrongCredentials(){
        LoginPage loginPage = new LoginPage(driver);

        User testUser = UserCreator.withWrongCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();
        String errorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
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
