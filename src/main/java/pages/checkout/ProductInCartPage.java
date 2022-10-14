package pages.checkout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductInCartPage {

    public ProductInCartPage(WebElement productFromCart) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productFromCart), this);
    }

    @FindBy(css = ".product-line-info a")
    private WebElement productFromCartName;

    @FindBy(css = ".current-price > span")
    private WebElement unitPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantity;

    @FindBy(css = ".price > .product-price")
    private WebElement totalPrice;

    @FindBy(css = ".remove-from-cart > .material-icons")
    private WebElement removeFromCartBtn;


    public String getProductFromCartName() {
        return productFromCartName.getText();
    }

    public double getUnitPrice() {
        return Double.parseDouble(unitPrice.getText().substring(1));
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getAttribute("value"));
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }
}
