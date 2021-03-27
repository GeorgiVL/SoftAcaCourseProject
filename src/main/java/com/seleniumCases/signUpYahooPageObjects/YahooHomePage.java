package com.seleniumCases.signUpYahooPageObjects;

import com.seleniumCases.utils.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YahooHomePage extends BaseDriver {



    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    private WebElement sighIn;

    @FindBy(xpath = "//div[@class='con-wizard']//ancestor::div[@class='wizard-body']//ancestor::div[@class='actions couple']//button")
    private WebElement cookieWindow;

    @FindBy(id = "yfin-usr-qry")
    private WebElement searchField;

    @FindBy(css = "#header-desktop-search-button")
    private WebElement searchButton;

    public YahooHomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement signIn() {
        return sighIn;
    }
    public WebElement acceptCookie() {
        return cookieWindow;
    }
    public WebElement searchField() {
        return searchField;
    }
    public WebElement searchButton() {
        return searchButton;
    }








    /*
    By sighIn = By.xpath("//span[contains(text(),'Sign in')]");
    By cookieWindow = By.xpath("//div[@class='con-wizard']//ancestor::div[@class='wizard-body']//ancestor::div[@class='actions couple']//button");
    By searchField = By.id("yfin-usr-qry");
    By searchButton = By.cssSelector("#header-desktop-search-button");

    public WebElement signIn() {
        return driver.findElement(sighIn);
    }

    public WebElement acceptCookie() {
        return driver.findElement(cookieWindow);
    }

    public WebElement searchField() {
        return driver.findElement(searchField);
    }

    public WebElement searchButton() {
        return driver.findElement(searchButton);
    }
*/


}
