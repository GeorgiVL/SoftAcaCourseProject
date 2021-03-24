package com.seleniumCases.comparingStatisticsPageObjects;

import com.seleniumCases.utils.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppleStockPage extends Base {

    public AppleStockPage(WebDriver driver) {
        super(driver);
    }

    By forwardDevidentAndYieldApple = By.xpath("//span[text()='Forward Dividend & Yield']/../following-sibling::td");
    By statisticPageApple = By.xpath("//li[@data-test='STATISTICS']");
    By priceBookApple = By.xpath("//span[text()='Price/Book']/../following-sibling::td");


    public WebElement appleDev() {
        return driver.findElement(forwardDevidentAndYieldApple);
    }
    public WebElement statisticApplePage() {
        return driver.findElement(statisticPageApple);
    }
    public WebElement priceBookApple() {
        return driver.findElement(priceBookApple);
    }

}
