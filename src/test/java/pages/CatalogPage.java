package pages;

import org.openqa.selenium.WebDriver;

public class CatalogPage extends Page {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    private final String sectionLocator = "//span[text()='%s']";
    private final String subSectionLocator = "//div[contains(text(),'%s')]";
    private final String itemCategoryLocator = "//span[contains(text(),'%s')]";

    public TVPage findItemCategory(String sectionName, String subSectionName, String itemCategory) {
        findElementByName(sectionLocator, sectionName).click();
        findElementByName(subSectionLocator, subSectionName).click();
        findElementByName(itemCategoryLocator, itemCategory).click();
        return new TVPage(driver);
    }
}
