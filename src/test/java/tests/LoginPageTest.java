package tests;

import helpers.UserManager;
import model.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import util.TestListener;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners({TestListener.class})
public class LoginPageTest extends BaseTest {

    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    @Test(priority = 0, description = "Standard user should login")
    public void standardUserShouldLogin() {
        //LoginPage loginPage = new LoginPage(driver);

        User testUser = UserManager.withStandardCredentials();

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
        //LoginPage loginPage = new LoginPage(driver);

        User testUser = UserManager.withWrongCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();
        String errorMessage = "Epic sadface: Username and password do not match any user in this service";
        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }

    @Test(description = "This test should fail")
    public void testShouldFail(){
        //Test designed specifically to show a failure
        //LoginPage loginPage = new LoginPage(driver);

        User testUser = UserManager.withWrongCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();

        assertThat(true).isEqualTo(false);
    }

    @Test
    public void lockedOutUserShouldNotLogin(){
        //LoginPage loginPage = new LoginPage(driver);

        User testUser = UserManager.lockedOut();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin();
        String errorMessage = "Epic sadface: Sorry, this user has been locked out.";
        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }
}