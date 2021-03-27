package com.seleniumCases.signUpYahooPageObjects;

import com.seleniumCases.utils.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooSignUpPage extends BaseDriver {


    public YahooSignUpPage(WebDriver driver) {
        super(driver);
    }

    By inputFirstName = By.cssSelector("#usernamereg-firstName");
    By inputLastName = By.cssSelector("#usernamereg-lastName");
    By inputEmailAddress = By.xpath("//input[@id='usernamereg-yid']");
    By inputPassword = By.id("usernamereg-password");
    By inputPhoneNum = By.id("usernamereg-phone");
    By selectElement = By.xpath("//select[@name='mm']");
    By inputDay = By.id("usernamereg-day");
    By inputYear = By.id("usernamereg-year");
    By singUpbutton = By.id("reg-submit-button");


    public WebElement firstName() {
        return driver.findElement(inputFirstName);
    }

    public WebElement lastName() {
        return driver.findElement(inputLastName);
    }

    public WebElement email() {
        return driver.findElement(inputEmailAddress);
    }

    public WebElement password() {
        return driver.findElement(inputPassword);
    }

    public WebElement phoneNum() {
        return driver.findElement(inputPhoneNum);
    }

    public WebElement dropDownElements() {
        return driver.findElement(selectElement);
    }

    public WebElement day() {
        return driver.findElement(inputDay);
    }

    public WebElement year() {
        return driver.findElement(inputYear);
    }

    public WebElement signUpButton() {
        return driver.findElement(singUpbutton);
    }
}
