package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TVPage extends Page {

    public TVPage(WebDriver driver) {
        super(driver);
    }

    private final String paramLocator = "//span[text()='%s']";

    @FindBy(xpath = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']")
    private WebElement maxPrice;
    @FindBy(xpath = "//select[contains(@data-bind,\"facet.value.from\")]")
    private WebElement minDiagonalFolder;
    @FindBy(xpath = "//select[contains(@data-bind,\"facet.value.to\")]")
    private WebElement maxDiagonalFolder;

    public ItemPage chooseItemsByParams(String producer, String price, String minDiagonal, String maxDiagonal, String screen) {
        findElementByName(paramLocator, producer).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maxPrice);
        maxPrice.click();
        maxPrice.sendKeys(price);
        WebElement screenElement = findElementByName(paramLocator, screen);
        new WebDriverWait(driver, 20).until(ExpectedConditions
                .elementToBeClickable(screenElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", screenElement);
        screenElement.click();
        chooseItemsByDiagonal(minDiagonalFolder, minDiagonal);
        new WebDriverWait(driver, 20).until(ExpectedConditions
                .elementToBeClickable(maxDiagonalFolder));
        chooseItemsByDiagonal(maxDiagonalFolder, maxDiagonal);
        return new ItemPage(driver);
    }

    public void chooseItemsByDiagonal(WebElement element, String diagonal) {
        Select select = new Select(element);
        select.selectByVisibleText(diagonal);

    }
}