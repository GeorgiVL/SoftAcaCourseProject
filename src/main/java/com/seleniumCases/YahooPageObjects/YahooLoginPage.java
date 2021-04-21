package com.seleniumCases.YahooPageObjects;

import com.seleniumCases.utils.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooLoginPage extends BaseDriver {

    public YahooLoginPage(WebDriver driver) {
        super(driver);
    }

    By createAnAccount = By.id("createacc");

    public WebElement signUp() {
        return driver.findElement(createAnAccount);
    }
}
