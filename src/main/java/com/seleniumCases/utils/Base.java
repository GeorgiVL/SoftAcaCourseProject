package com.seleniumCases.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Base {
    protected final WebDriver driver;
    public Base(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
