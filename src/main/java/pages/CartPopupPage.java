package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;

public class CartPopupPage extends BasePage {
    public CartPopupPage(WebDriver driver) {
        super(driver);
    }

    private static final DecimalFormat df = new DecimalFormat("#.00");

    @FindBy(css = "#blockcart-modal")
    private WebElement cartPopup;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".col-md-6 > .product-price")
    private WebElement unitPrice;

    @FindBy(css = ".subtotal")
    private WebElement priceSum;

    @FindBy(css = ".product-quantity strong")
    private WebElement quantity;

    @FindBy(css = ".shipping")
    private WebElement shippingPrice;

    @FindBy(css = ".cart-content > .cart-products-count")
    private WebElement thereAreXProductsMsg;

    @FindBy(css = ".product-total > .value")
    private WebElement totalValue;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingBtn;

    @FindBy(css = ".cart-content-btn > .btn-primary")
    private WebElement proceedToCheckoutBtn;

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(cartPopup));
        return productName.getText();
    }

    public String getUnitPrice() {
        logger.info("-----> Unit price in the cart is {}", unitPrice.getText());
        return unitPrice.getText();
    }

    public String getQuantity() {
        logger.info("-----> Quantity in the cart is {}", quantity.getText() + " <------");
        return quantity.getText();
    }

    public String getShippingPrice() {
        logger.info("-----> Shipping price is {}", shippingPrice.getText() + " <------");
        return shippingPrice.getText();
    }

    public String getTotalValue() {
        return totalValue.getText().substring(1);
    }

    public String getNumberOfProductsFromMessage() {
        StringBuilder numberOfProducts = new StringBuilder();
        for (int i = 0; i < thereAreXProductsMsg.getText().length(); i++) {
            if (Character.isDigit(thereAreXProductsMsg.getText().charAt(i))) {
                numberOfProducts.append(thereAreXProductsMsg.getText().charAt(i));
            }
        }
        logger.info("-----> There are {}", numberOfProducts + " items in your cart <------");
        return numberOfProducts.toString();
    }

    public String totalValueShouldBe() {
        double total = Double.parseDouble(getUnitPrice().substring(1)) * 3 +
                Double.parseDouble(getShippingPrice().substring(1));
        logger.info("-----> Total price is {}", df.format(total).replaceAll(",", "."));
        return df.format(total).replaceAll(",", ".");
    }
    public void clickContinueShopping() {
        logger.info("-----> Clicking Continue shopping button <-----");
        click(continueShoppingBtn);
    }

    public void clickProceedToCheckout() {
        logger.info("-----> Clicking Proceed to checkout button <------");
        click(proceedToCheckoutBtn);
    }
}
