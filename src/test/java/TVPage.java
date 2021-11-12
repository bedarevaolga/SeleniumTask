import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TVPage {

    private final WebDriver driver;

    public TVPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//input[@value='samsung']")
    private WebElement inpSamsung;
    @FindBy(xpath = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']")
    private WebElement maxPrice;

    @FindBy(xpath = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']")
    private WebElement maxDiagonal;

//    public TVPage findTVbyParams() {
//        spnElectronics.click();
//        divTVandVidio.click();
//        divTV.click();
//        return new TVPage(driver);
//    }
}
