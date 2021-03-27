package com.seleniumCases.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseDriver {
    protected final WebDriver driver;
    public BaseDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
