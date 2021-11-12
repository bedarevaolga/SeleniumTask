import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BaseForm {


    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='https://catalog.onliner.by/']")
    private WebElement spnCatalog;

    public CatalogPage catalogClick() {
        spnCatalog.click();
        return new CatalogPage(driver);
    }

}
