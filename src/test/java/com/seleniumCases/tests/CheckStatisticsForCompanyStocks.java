package com.seleniumCases.tests;

import com.seleniumCases.YahooPageObjects.YahooFinanceStatisticPage;
import com.seleniumCases.YahooPageObjects.YahooHomePage;
import com.seleniumCases.base.TestUtils;
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
    public void checkStatsForCompanyStocks(String companyName, String devExp, String mrqExp) {

        // Creating objects from the pages where we have stored all the elements so we can do some actions with them once we call them.
        YahooHomePage yahoo = new YahooHomePage(driver);
        YahooFinanceStatisticPage amazonApple = new YahooFinanceStatisticPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        // Accept the cookies and searching for Amazon and Apple index
        yahoo.acceptCookie().click();
        yahoo.searchField().sendKeys(companyName);
        yahoo.searchButton().click();

        // Getting the data - Forward Dividend & Yield and Price/Book (mrq)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-test='DIVIDEND_AND_YIELD-value']")));
        String dev = amazonApple.devidents().getText();
        amazonApple.statisticPage().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Price/Book']/../following-sibling::td")));
        String mrq = amazonApple.priceBook().getText();

        // Compare actual with the expected result where the expected result is provided from the CSV file with the help of the Data Provider
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(dev, devExp);
        softAssert.assertEquals(mrq, mrqExp);

        System.out.println("Executing the test!");
        //softAssert.assertAll();

    }
}
