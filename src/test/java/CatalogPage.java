import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {

    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[text()='Электроника']")
    private WebElement spnElectronics;
    @FindBy(xpath = "//div[contains(text(),\"Телевидение\")]")
    private WebElement divTVandVidio;
    @FindBy(xpath = " //a[@href='https://catalog.onliner.by/tv']")
    private WebElement divTV;

    public TVPage findTVSection() {
        spnElectronics.click();
        divTVandVidio.click();
        divTV.click();
        return new TVPage(driver);
    }
}
