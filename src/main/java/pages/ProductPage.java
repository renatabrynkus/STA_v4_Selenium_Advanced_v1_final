package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".bootstrap-touchspin")
    private WebElement quantity;

    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement quantityUp;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = ".current-price span")
    private WebElement price;

    public void changeQuantity(int qtyUp) {
        wait.until(ExpectedConditions.visibilityOf(quantity));
        for (int i = 1; i < qtyUp; i++) {
            clickOnQty();
        }
        addToCart();
    }

    public String getPrice() {
        return price.getText();
    }

    private void clickOnQty() {
        logger.info("-----> Increasing quantity <-----");
        quantityUp.click();
    }

    public CartPopupPage addToCart() {
        logger.info("----> Clicking Add to cart button <-----");
        click(addToCartBtn);
        return new CartPopupPage(driver);
    }
}
