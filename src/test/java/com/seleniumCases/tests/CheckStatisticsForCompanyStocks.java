package com.seleniumCases.tests;

import com.seleniumCases.comparingStatisticsPageObjects.AmazonStockPage;
import com.seleniumCases.comparingStatisticsPageObjects.AppleStockPage;
import com.seleniumCases.base.TestUtils;
import com.seleniumCases.signUpYahooPageObjects.YahooHomePage;
import com.seleniumCases.utils.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class CheckStatisticsForCompanyStocks extends TestUtils {


    @DataProvider(name = "stocks-data")
    public static Object[] dataProvidedFromTheCsvFileSecondTest() throws IOException {
        return CsvReader.readCsvFile("D://SoftAcaCourseProject//src//test//resources//stocksCompany-data.csv");
    }



    @Test(dataProvider = "stocks-data")
    public void checkStatsForCompanyStocks(String compAmazon,String devAma, String compApple, String devApp, String mrqAma, String mrqApp) {


        // Creating objects from the pages where we have stored all the elements so we can do some actions with them once we call them.
        YahooHomePage yahoo = new YahooHomePage(driver);
        AmazonStockPage amazon = new AmazonStockPage(driver);
        AppleStockPage apple = new AppleStockPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);

        // Accept the cookies and searching for Amazon index
        yahoo.acceptCookie().click();
        yahoo.searchField().sendKeys(compAmazon);
        yahoo.searchButton().click();

        // Getting Amazon data - Forward Dividend & Yield and Price/Book (mrq)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-test='DIVIDEND_AND_YIELD-value']")));
        String amazonDev = amazon.amazonDev().getText();
        amazon.statisticPageAmazon().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price/Book']/../following-sibling::td")));
        String mrqAmazon = amazon.priceBookAmazon().getText();

        // Searching for Apple index
        yahoo.searchField().sendKeys(compApple);
        yahoo.searchButton().click();

        // Getting Apple data - Forward Dividend & Yield and Price/Book (mrq)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Forward Dividend & Yield']/../following-sibling::td")));
        String appleDev = apple.appleDev().getText();
        apple.statisticApplePage().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price/Book']/../following-sibling::td")));
        String mrqApple = apple.priceBookApple().getText();


        // Compare actual with the expected result where the expected result is provided from the CSV file with the help of the Data Provider
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(amazonDev, devAma);
        softAssert.assertEquals(mrqAmazon, mrqAma);
        softAssert.assertEquals(appleDev, devApp);
        softAssert.assertEquals(mrqApple, mrqApp);

        System.out.println("Executing the test!");
        //softAssert.assertAll();

    }
}
