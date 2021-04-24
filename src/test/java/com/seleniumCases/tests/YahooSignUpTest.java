package com.seleniumCases.tests;

import com.seleniumCases.YahooPageObjects.YahooHomePage;
import com.seleniumCases.YahooPageObjects.YahooLoginPage;
import com.seleniumCases.YahooPageObjects.YahooSignUpPage;
import com.seleniumCases.base.TestUtils;
import com.seleniumCases.utils.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class YahooSignUpTest extends TestUtils {

    @DataProvider(name = "signUp-data-file")
    public static Object[][] dataProviderFromCsvFileFirstTest() throws IOException {
        return CsvReader.readCsvFile("src/test/resources/signUp-data.csv");
    }

    @Test(dataProvider = "signUp-data-file")
    public void NegativeRegistration(String fName, String lName, String emailAddr, String pass, String phoneN, String d, String y,String gender) throws InterruptedException {

        // Creating objects from the pages where we have stored all the elements so we can do some actions with them once we call them.
        YahooHomePage hp = new YahooHomePage(driver);
        YahooLoginPage lp = new YahooLoginPage(driver);
        YahooSignUpPage sp = new YahooSignUpPage(driver);

        // Accept the cookies and redirect to signUP page
        hp.acceptCookie().click();
        hp.signIn().click();
        lp.signUp().click();

        // Populating the data which is being provide from the from the CSV file with the help of Data Provider
        sp.firstName().sendKeys(fName);
        sp.lastName().sendKeys(lName);
        sp.email().sendKeys(emailAddr);
        sp.password().sendKeys(pass);
        sp.phoneNum().sendKeys(phoneN);

        // From the drop down menu we choose "March"
        Select list = new Select(sp.dropDownElements());
        list.selectByValue("3");
        sp.day().sendKeys(d);
        sp.year().sendKeys(y);
        sp.enterGender().sendKeys(gender);

        // Getting the text from all the error messages
        String emailErrorMess = driver.findElement(By.xpath("//div[@id='reg-error-yid']")).getText();
        String passwordErrorMess = driver.findElement(By.cssSelector("#reg-error-password")).getText();
        String phoneNumErrorMess = driver.findElement(By.id("reg-error-phone")).getText();

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement birthDayEr = driver.findElement(By.id("reg-error-birthDate"));
        wait.until(ExpectedConditions.visibilityOf(birthDayEr));
        String birthDayErrorMess = birthDayEr.getText();

        // Verifying all errors messages for each field
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(emailErrorMess, "A Yahoo account already exists with this email address. Sign in.");
        softAssert.assertEquals(passwordErrorMess, "Your password isn’t strong enough, try making it longer.");
        softAssert.assertEquals(phoneNumErrorMess, "That doesn’t look right, please re-enter your phone number.");
        softAssert.assertEquals(birthDayErrorMess, "That doesn’t look right, please re-enter your birthday.");
        System.out.println("Executing the test!");
        softAssert.assertAll();
    }
}
