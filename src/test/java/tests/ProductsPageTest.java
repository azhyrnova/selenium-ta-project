package tests;

import helpers.UserCreator;
import model.User;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageTest extends BaseTest {
    @Test
    public void directOpeningThePageShouldThrowError() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();
        LoginPage loginPage = new LoginPage(driver);

        String errorMessage = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }

    @Test
    public void userCanAddItemToShoppingCart(){
        LoginPage loginPage = new LoginPage(driver);
        User testUser = UserCreator.withStandardCredentials();
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
