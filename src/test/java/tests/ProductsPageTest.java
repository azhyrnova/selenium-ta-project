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
public class ProductsPageTest extends BaseTest {

    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    ProductsPage productsPage = PageFactory.initElements(driver, ProductsPage.class);
    @Test
    public void directOpeningThePageShouldThrowError() {
        //ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();
        //LoginPage loginPage = new LoginPage(driver);

        String errorMessage = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }

    @Test
    public void userCanAddItemToShoppingCart(){
        //LoginPage loginPage = new LoginPage(driver);
        User testUser = UserManager.withStandardCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin().addToCartBackpack();

        ProductsPage productsPage = new ProductsPage(driver);

        int expectedNumberOfItems = 1;
        int actualNumberOfItems = productsPage.getNumberOfItemsInShoppingCart();
        assertThat(actualNumberOfItems).isEqualTo(expectedNumberOfItems);
    }
}
