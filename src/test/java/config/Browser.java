package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public final class Browser{

    private static Browser instance;

    public static  Browser getInstance() {
        if (instance == null) instance = new Browser();
        return instance;
    }

    private Browser() {

    }
    private WebDriver driver;

    public WebDriver SetUp() {
        System.setProperty("webdriver.chrome.driver", ConfigLoader.getProperty("driverPath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS).implicitlyWait(10,TimeUnit.SECONDS).setScriptTimeout(10,TimeUnit.SECONDS);
        driver.navigate().to(ConfigLoader.getProperty("url"));
        return driver;

    }

    public WebDriver getDriver() {
        return driver;
    }


    public void teardown(){
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
