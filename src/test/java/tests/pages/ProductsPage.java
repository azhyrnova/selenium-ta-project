package tests.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends AbstractPage {

    protected final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//span[contains(text(), 'Products')]")
    private WebElement productsPageTitle;

    @FindBy(css = ".error-button")
    private WebElement errorButton;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartBackpack;

    @FindBy(xpath = "//div[@id = 'shopping_cart_container']/a/span")
    private WebElement shoppingCart;

    @FindBy(css = "#item_4_title_link > div")
    private  WebElement descriptionOfBackpack;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductsPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public String getTextFromPageTitle() {
        return productsPageTitle.getText();
    }

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
