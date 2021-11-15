package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TVPage extends Page {

    public TVPage(WebDriver driver) {
        super(driver);
    }

    private final String paramLocator = "//span[text()='%s']";
    private final String paramLocatorForChecking = "//span[contains(@data-bind,'%s')]";

    @FindBy(xpath = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']")
    private WebElement maxPrice;
    @FindBy(xpath = "//select[contains(@data-bind,\"facet.value.from\")]")
    private WebElement minDiagonalFolder;
    @FindBy(xpath = "//select[contains(@data-bind,\"facet.value.to\")]")
    private WebElement maxDiagonalFolder;
    @FindBy(xpath = "//div[@class='schema-product']")
    private List<WebElement> items;
//    @FindBy(xpath = "//div[@class='schema-product']")
//    private List<WebElement>items;
//span[@data-bind='html: product.extended_name || product.full_name']

    public TVPage chooseItemsByParams(String producer, String price, String minDiagonal, String maxDiagonal, String screen) {
        findElementByName(paramLocator, producer).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maxPrice);
        maxPrice.click();
        maxPrice.sendKeys(price);
        chooseItemsByDiagonal(minDiagonalFolder, minDiagonal);
        chooseItemsByDiagonal(maxDiagonalFolder, maxDiagonal);
        WebElement screenElement = findElementByName(paramLocator, screen);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", screenElement);
        screenElement.click();
        return new TVPage(driver);
    }

    public void chooseItemsByDiagonal(WebElement element, String diagonal) {
        Select select = new Select(element);
        select.selectByVisibleText(diagonal);
    }

    public boolean checkProducer(String producer, String parameter) {
        List<WebElement> params = findElementsByName(paramLocatorForChecking, parameter);
        for (WebElement param : params) {
            if (!param.getText().contains(producer)) {
                return false;
            }
        }
        return true;
    }

    public void checkPrice( String parameter) {
        List<WebElement> params = findElementsByName(paramLocatorForChecking, parameter);
        for (WebElement param : params) {
            String string  = param.getText().trim();
            int price = Integer.parseInt(string.substring(0, string.indexOf("р.")).replace(",", "."));
            System.out.println(price);
    }

}
    }