package pages.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class OrderHistoryRowPage {

    public OrderHistoryRowPage(WebElement row) {
        PageFactory.initElements(new DefaultElementLocatorFactory(row), this);
    }

    @FindBy(css = "th")
    private WebElement orderReference;

    @FindBy(css = ".order-actions > a:nth-child(1)")
    private WebElement details;

    public WebElement getOrderReference() {
        return orderReference;
    }

    public WebElement getDetails() {
        return details;
    }
}
