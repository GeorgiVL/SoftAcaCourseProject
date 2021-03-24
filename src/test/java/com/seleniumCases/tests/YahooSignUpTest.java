package com.seleniumCases.tests;

import com.seleniumCases.base.TestUtils;
import com.opencsv.exceptions.CsvException;
import com.seleniumCases.signUpYahooPageObjects.YahooHomePage;
import com.seleniumCases.signUpYahooPageObjects.YahooLoginPage;
import com.seleniumCases.signUpYahooPageObjects.YahooSignUpPage;
import com.seleniumCases.utils.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.io.IOException;


public class YahooSignUpTest extends TestUtils {

    @DataProvider(name="signUp-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/signUp-data.csv");
    }


    @Test(dataProvider = "signUp-data-file")
    public void NegativeRegistration(String fName, String lName, String emailAddr, String pass, String phoneN, String d, String y) throws InterruptedException {

        YahooHomePage hp = new YahooHomePage(driver);
        YahooLoginPage lp = new YahooLoginPage(driver);
        YahooSignUpPage sp = new YahooSignUpPage(driver);

        hp.acceptCookie().click();
        hp.signIn().click();
        lp.signUp().click();

        sp.firstName().sendKeys(fName);
        sp.lastName().sendKeys(lName);
        sp.email().sendKeys(emailAddr);
        sp.password().sendKeys(pass);
        sp.phoneNum().sendKeys(phoneN);

        Select list = new Select(sp.dropDownElements());
        list.selectByValue("3");
        list.selectByVisibleText("March");

        sp.day().sendKeys(d);
        sp.year().sendKeys(y);
        sp.signUpButton().click();

        String emailErrorMess = driver.findElement(By.cssSelector("#reg-error-yid")).getText();
        String passwordErrorMess = driver.findElement(By.cssSelector("#reg-error-password")).getText();
        String phoneNumErrorMess = driver.findElement(By.xpath("//div[@data-error='messages.INVALID_PHONE_NUMBER']")).getText();
        String birthDayErrorMess = driver.findElement(By.cssSelector("#reg-error-birthDate")).getText();

        /*
        Assert.assertEquals(emailErrorMess, "This email address is not available for sign up, try something else");
        */

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(emailErrorMess, "This email address is not available for sign up, try something else");
        softAssert.assertEquals(passwordErrorMess, "Your password isn’t strong enough, try making it longer.");
        softAssert.assertEquals(phoneNumErrorMess,"That doesn’t look right, please re-enter your phone number.");
        softAssert.assertEquals(birthDayErrorMess, "That doesn’t look right, please re-enter your birthday.");

        softAssert.assertAll();
    }
}
