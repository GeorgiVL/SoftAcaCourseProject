package com.seleniumCases.signUpYahooPageObjects;

import com.seleniumCases.utils.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooLoginPage extends Base {

    public YahooLoginPage(WebDriver driver) {
        super(driver);
    }

    By createAnAccount = By.id("createacc");
    public WebElement signUp() {
        return driver.findElement(createAnAccount);
    }
}
