package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='https://catalog.onliner.by/']")
    private WebElement spnCatalog;

    public CatalogPage catalogClick() {
        spnCatalog.click();
        return new CatalogPage(driver);
    }
}
