package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.ConfigurationManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends AbstractPage {

    private final String PAGE_URL = ConfigurationManager.getProperty("base.url") + "/inventory.html";
    private final Logger logger = LogManager.getRootLogger();

    private SelenideElement productsPageTitle =$x("//span[contains(text(), 'Products')]");

    private SelenideElement errorButton =$(".error-button");
    private SelenideElement addToCartBackpack =$("#add-to-cart-sauce-labs-backpack");

    private SelenideElement shoppingCart =$x("/div[@id = 'shopping_cart_container']/a/span");

    private SelenideElement descriptionOfBackpack =$("#item_4_title_link > div");

    public ProductsPage() {
        //super();
        //return page(ProductsPage.class);
        //PageFactory.initElements(this.driver, this);
    }

    public ProductsPage openPage() {
        open(PAGE_URL);
        return this;
    }

    public String getTextFromPageTitle() {
        return productsPageTitle.getText();
    }

    @Step("Backpack was added to the cart")
    public void addToCartBackpack(){
        addToCartBackpack.click();
        logger.info(getDescription() + " was added to the cart");
    }

    public int getNumberOfItemsInShoppingCart(){
        return Integer.parseInt(shoppingCart.getText());
    }

    public String getDescription(){
        return descriptionOfBackpack.getText();
    }


}
