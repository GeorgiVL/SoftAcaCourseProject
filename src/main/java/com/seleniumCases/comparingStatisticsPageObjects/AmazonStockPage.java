package com.seleniumCases.comparingStatisticsPageObjects;

import com.seleniumCases.utils.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonStockPage extends Base {

    public AmazonStockPage(WebDriver driver) {
        super(driver);
    }

    By forwardDevidentAndYieldAmazon = By.xpath("//td[@data-test='DIVIDEND_AND_YIELD-value']");
    By statisticPageAmazon = By.xpath("//li[@data-test='STATISTICS']//a[@href='/quote/AMZN/key-statistics?p=AMZN']");
    By priceBookAmazon = By.xpath("//span[text()='Price/Book']/../following-sibling::td");

    public WebElement amazonDev() {
        return driver.findElement(forwardDevidentAndYieldAmazon);
    }
    public WebElement statisticPageAmazon() {
        return driver.findElement(statisticPageAmazon);
    }
    public WebElement priceBookAmazon() {
        return driver.findElement(priceBookAmazon);
    }

}
