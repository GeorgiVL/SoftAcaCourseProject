package com.seleniumCases.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String IGNORE_CERTIFICATE = "--ignore-certificate-errors";

    public static WebDriver getFirefoxDriver(int wait) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(IGNORE_CERTIFICATE);
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        return driver;
    }

    // Homework - implement the same for chrome
    public static WebDriver getChromeDriver(int wait) {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments(IGNORE_CERTIFICATE);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(opt);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        return driver;
    }
}
