import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static MainPage mainPage;
    public static WebDriver driver;
    public static CatalogPage catalogPage;
    @BeforeClass
    public static void testSetup() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.onliner.by/");


    }

    @Test
    public void testCatalog() {

mainPage.catalogClick();
catalogPage.findTVSection();


    }
}
