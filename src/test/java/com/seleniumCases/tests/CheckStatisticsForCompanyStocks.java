package com.seleniumCases.tests;

import com.opencsv.exceptions.CsvException;
import com.seleniumCases.comparingStatisticsPageObjects.AmazonStockPage;
import com.seleniumCases.comparingStatisticsPageObjects.AppleStockPage;
import com.seleniumCases.base.TestUtils;
import com.seleniumCases.signUpYahooPageObjects.YahooHomePage;
import com.seleniumCases.utils.Base;
import com.seleniumCases.utils.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CheckStatisticsForCompanyStocks extends TestUtils {

/*
    @DataProvider(name = "stocks-data-file")
    public static Object[] dataProvidedFromTheCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/stocks-data.csv");
    }

*/

    @Test
    public void checkStatsForCompanyStocks() throws InterruptedException {

        YahooHomePage yahoo = new YahooHomePage(driver);
        AmazonStockPage amazon = new AmazonStockPage(driver);
        AppleStockPage apple = new AppleStockPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);


        yahoo.acceptCookie().click();
        yahoo.searchField().sendKeys("Amazon");
        yahoo.searchButton().click();


        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-test='DIVIDEND_AND_YIELD-value']")));
        String amazonDev = amazon.amazonDev().getText();
        amazon.statisticPageAmazon().click();
        String mrqAmazon = amazon.priceBookAmazon().getText();


        yahoo.searchField().sendKeys("Apple");
        yahoo.searchButton().click();


        wait.until(ExpectedConditions.visibilityOf(apple.appleDev()));
        String appleDev = apple.appleDev().getText();
        apple.statisticApplePage().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price/Book']/../following-sibling::td")));
        String mrqApple = apple.priceBookApple().getText();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(amazonDev, "N/A (N/A)");
        softAssert.assertEquals(mrqAmazon, "16.79");
        softAssert.assertEquals(appleDev, "0.82 (0.67%)");
        softAssert.assertEquals(mrqApple, "30.79");

        //softAssert.assertAll();

    }
}
