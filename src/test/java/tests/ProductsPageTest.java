package tests;

import helpers.UserManager;
import model.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import util.InvokedMethodTestListener;
import util.TestListener;

import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners({TestListener.class, InvokedMethodTestListener.class})
public class ProductsPageTest extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    @Test
    public void directOpeningThePageShouldThrowError() {
        initPages();
        productsPage.openPage();

        String errorMessage = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

        assertThat(errorMessage).isEqualTo(loginPage.getErrorMessage());
    }

    @Test
    public void userCanAddItemToShoppingCart(){
        initPages();
        User testUser = UserManager.withStandardCredentials();
        loginPage.
                openPage().
                enterUsername(testUser.getUsername()).
                enterPassword(testUser.getPassword()).clickLogin().addToCartBackpack();


        int expectedNumberOfItems = 1;
        int actualNumberOfItems = productsPage.getNumberOfItemsInShoppingCart();
        assertThat(actualNumberOfItems).isEqualTo(expectedNumberOfItems);
    }

    void initPages() {
        loginPage = page(LoginPage.class);
        productsPage = page(ProductsPage.class);
    }
}