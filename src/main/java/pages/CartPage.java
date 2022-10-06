package pages;

import orderDetails.OrderDetails;
import orderDetails.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public OrderDetails getOrderDetailsCart() {
        convertProductsInCartToProductsList();
        return orderDetailsCart;
    }

    public void addProductsFromCart() {
        for (WebElement line : productsInCart) {
            productsInCartPage.add(new ProductInCartPage(line));
            logger.info("-----> Added a product from cart to list with ProductsFromCart <------");
        }
    }

    public void convertProductsInCartToProductsList() {
        for (ProductInCartPage productInCart : productsInCartPage) {
            orderDetailsCart.addToProductsToTheCart(new Product(productInCart.getProductFromCartName(),
                    productInCart.getUnitPrice(), productInCart.getQuantity()));
            logger.info("-----> Product {} added to productsList", productInCart.getProductFromCartName() + " <-----");
        }
    }
}
