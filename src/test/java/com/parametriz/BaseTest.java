package com.parametriz;

import config.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.ItemPage;
import pages.MainPage;
import pages.TVPage;


public class BaseTest {

    public static MainPage mainPage;
    public static ItemPage itemPage;
    public static CatalogPage catalogPage;
    public static TVPage tvPage;

    @BeforeClass
    public static void testSetup() {
        Browser.getInstance().SetUp();
        mainPage = new MainPage(Browser.getInstance().getDriver());
        catalogPage = new CatalogPage(Browser.getInstance().getDriver());
        tvPage = new TVPage(Browser.getInstance().getDriver());
        itemPage = new ItemPage(Browser.getInstance().getDriver());
        Assert.assertEquals(Browser.getInstance().getDriver().getCurrentUrl(), "https://www.onliner.by/");
    }

    @Parameters({"producer", "maxPrice", "minDiagonal", "maxDiagonal", "screen"})
    @Test
    public void testCatalog(String producer, String price, String minDiagonal, String maxDiagonal, String screen) {
        mainPage.catalogClick();
        Assert.assertEquals(Browser.getInstance().getDriver().getCurrentUrl(), "https://catalog.onliner.by/");
        catalogPage.findItemCategory("Электроника", "Телевидение", "Телевизоры");
        Assert.assertEquals(Browser.getInstance().getDriver().getCurrentUrl(), "https://catalog.onliner.by/tv");
        tvPage.chooseItemsByParams(producer, price, minDiagonal, maxDiagonal, screen);
        Assert.assertTrue(itemPage.checkParams(producer, price, minDiagonal, maxDiagonal, screen));
    }

    @AfterClass
    public void closeBrowser() {
        Browser.getInstance().teardown();
    }
}


