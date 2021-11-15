package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class Page {

    public final WebDriver driver;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected WebElement findElementByName(String locator, String name) {
        return driver.findElement(By.xpath(String.format(locator, name)));
    }
    protected List<WebElement> findElementsByName(String locator, String name) {
        List<WebElement> list = driver.findElements(By.xpath(String.format(locator, name)));
        return list;
    }
}
