package com.parametriz;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.CatalogPage;
import pages.MainPage;
import pages.TVPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static MainPage mainPage;
    public static WebDriver driver;
    public static CatalogPage catalogPage;
    public static TVPage tvPage;
    private static Properties testProperties;


    @BeforeClass
    public static void testSetup() throws IOException {
        testProperties = new Properties();
        testProperties.load(new FileInputStream("src/test/resources/selenium.properties"));
        System.setProperty("webdriver.chrome.driver", testProperties.getProperty("driverPath"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        tvPage = new TVPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(testProperties.getProperty("url"));


    }

    @Parameters({"producer", "maxPrice", "minDiagonal", "maxDiagonal", "screen"})
    @Test
   public void testCatalog(String producer, String price, String minDiagonal, String maxDiagonal, String screen) {
        mainPage.catalogClick();
        catalogPage.findItemCategory("Электроника", "Телевидение", "Телевизоры");
        tvPage.chooseItemsByParams(producer, price, minDiagonal, maxDiagonal, screen);
        driver.navigate().refresh();
        Assert.assertTrue(tvPage.checkProducer(producer, "product.full_name"));
//        Assert.assertTrue(tvPage.checkProducer(pri, "product.full_name"));
//        Assert.assertTrue(tvPage.checkProducer(producer, "product.full_name"));
//        Assert.assertTrue(tvPage.checkProducer(producer, "product.full_name"));
  //    tvPage.checkPrice("minPrice");
    }

    @AfterClass
    public static void testTearDown() {
        driver.quit();
    }
}
