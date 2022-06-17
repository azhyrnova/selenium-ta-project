package pages;

import helpers.ConfigurationManager;

import static com.codeborne.selenide.Selenide.open;

public abstract class AbstractPage {

    protected final String PAGE_URL = ConfigurationManager.getProperty("base.url");

    protected AbstractPage openPage(){
        open(PAGE_URL);
        return this;
    }
}
