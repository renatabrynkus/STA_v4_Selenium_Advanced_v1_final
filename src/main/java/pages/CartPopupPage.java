package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPopupPage extends BasePage {
    public CartPopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#blockcart-modal")
    private WebElement cartPopup;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = "")
    private WebElement unitPrice;

    @FindBy(css = "")
    private WebElement priceSum;

    @FindBy(css = "")
    private WebElement quantity;

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(cartPopup));
        return productName.getText();
    }
//
//    public () {
//        wait.until(ExpectedConditions.visibilityOf(cartPopup));
//
//    }
}
