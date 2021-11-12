import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement extends BaseEntity  {

    protected By locator;
    protected WebElement element;

    public BaseElement(final By loc) {
        locator = loc;
    }

//    public void click() {
//
//        element.click();
//    }


}
