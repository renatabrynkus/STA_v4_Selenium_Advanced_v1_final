package pages.checkout;

import orderDetails.OrderDetails;
import orderDetails.Product;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    OrderDetails orderDetailsCart = new OrderDetails();
    ArrayList<ProductInCartPage> productsInCartPage = new ArrayList<>();

    @FindBy(css = ".cart-item")
    private List<WebElement> productsInCart;

    @FindBy(css = ".cart-total > .value")
    private WebElement totalOrderValue;

    @FindBy(css = ".remove-from-cart")
    private WebElement removeBtn;

    @FindBy(css = ".no-items")
    private WebElement noItemsMessage;

    public ArrayList<ProductInCartPage> getProductsInCartPage() {
        return productsInCartPage;
    }

    private double getTotalOrderValueDouble() {
        return Double.parseDouble(totalOrderValue.getText().substring(1));
    }

    public String getTotalOrderValue() {
        return df.format(getTotalOrderValueDouble()).replaceAll(",", ".");
    }

    public String getNoItemsMessage() {
        return noItemsMessage.getText();
    }

    public OrderDetails getOrderDetailsCart() {
        try {
            convertProductsInCartToProductsList();
        } catch (StaleElementReferenceException ignored) {
        }
        return orderDetailsCart;
    }

    public void addProductsFromCart() {
        for (WebElement line : productsInCart) {
            productsInCartPage.add(new ProductInCartPage(line));
        }
    }

    public void convertProductsInCartToProductsList() {
        for (ProductInCartPage productInCart : productsInCartPage) {
            orderDetailsCart.addToProductsToTheCart(new Product(productInCart.getProductFromCartName(),
                    productInCart.getUnitPrice(), productInCart.getQuantity(), productInCart.getTotalPrice()));
            logger.info("-----> Product {} added to productsList", productInCart.getProductFromCartName() + " <-----");
        }
    }

    public void removeProductFromCart(Product productToRemove) throws InterruptedException {
        logger.info("-----> Removing product {} from the cart", productToRemove.getName() + " <-----");
        orderDetailsCart.getProductsInCart().remove(productToRemove);
        removeBtn.click();
        Thread.sleep(500);
    }

    public Product getProductFromOrderList() {
        return orderDetailsCart.getProductsInCart().get(0);
    }
}
