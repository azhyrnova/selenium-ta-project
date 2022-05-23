package tests;

import helpers.CommonConditions;
import org.testng.annotations.Test;
import tests.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageTest extends CommonConditions {
    @Test
    public void directOpeningThePageShouldThrowError() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();
        String errorMessage = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

        assertThat(errorMessage).isEqualTo(productsPage.getErrorMessage());
    }
}
