package com.seleniumCases.YahooPageObjects;

import com.seleniumCases.utils.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooFinanceStatisticPage extends BaseDriver {

    public YahooFinanceStatisticPage(WebDriver driver) {
        super(driver);
    }

    By forwardDevidentAndYield = By.xpath("//td[@data-test='DIVIDEND_AND_YIELD-value']");
    By statisticPage = By.xpath("//li[@data-test='STATISTICS']");
    By priceBook = By.xpath("//span[text()='Price/Book']/../following-sibling::td");

    public WebElement devidents() {
        return driver.findElement(forwardDevidentAndYield);
    }
    public WebElement statisticPage() {
        return driver.findElement(statisticPage);
    }
    public WebElement priceBook() {
        return driver.findElement(priceBook);
    }
}
