package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ItemPage extends Page {

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    private static final By pricesBy = By.xpath("//span[contains(@data-bind,'minPrice')]");
    private static final By producersBy = By.xpath("//span[@data-bind='html: product.extended_name || product.full_name']");
    private static final By descriptionsBy = By.xpath("//span[@data-bind='html: product.description']");


    public boolean checkParams(String producer, String price, String minDiagonal, String maxDiagonal, String screen) {

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver
                        .findElement(By.className("schema-product__price")))));

      return (checkPrice(price) && checkProducer(producer) && checkDescription(minDiagonal, maxDiagonal, screen));
    }

    public boolean checkPrice(String price) {
        List<WebElement> prices = driver.findElements(pricesBy);
        for (WebElement element : prices) {
            String str = element.getText().trim();
            double itemPrice = Double.parseDouble(str.substring(0, str.indexOf("р.")).replace(",", "."));
            if (itemPrice > Double.parseDouble(price)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkProducer(String producer) {
        List<WebElement> producers = driver.findElements(producersBy);
        for (WebElement element : producers) {
            if (!element.getText().contains(producer)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkDescription(String minDiagonal, String maxDiagonal, String screen) {
            List<WebElement> descriptions = driver.findElements(descriptionsBy);
            for (WebElement description : descriptions) {
                String desc = description.getText().trim();
                if (!desc.contains(screen)
                        || (getScreenSize(desc) < getScreenSize(minDiagonal)
                        && getScreenSize(desc) > getScreenSize(maxDiagonal))) {
                    return false;
                }
            }
            return true;
        }

    public double getScreenSize(String s) {
        return Double.parseDouble(s.trim().substring(0, s.indexOf("\"")));
    }
}
