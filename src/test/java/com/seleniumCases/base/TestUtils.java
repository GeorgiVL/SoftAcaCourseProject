package com.seleniumCases.base;

import com.seleniumCases.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtils {

    public WebDriver driver;
    private String url;
    private int impWait;
    private String browser;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        setUpBrowserDriver();
        loadUrl();
    }

    private void loadUrl() {
        driver.get(url);
    }

    private void setUpBrowserDriver() throws InterruptedException {
        try {
            FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("url");
            impWait = Integer.parseInt(config.getProperty("implicitlyWait"));
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case "chrome":
                driver = DriverFactory.getChromeDriver(impWait);
                break;
            case "firefox":
                driver = DriverFactory.getFirefoxDriver(impWait);
                break;
            default:
                // log.error("Unsupported browser!");
                throw new IllegalStateException("Unsupported browser!");
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
