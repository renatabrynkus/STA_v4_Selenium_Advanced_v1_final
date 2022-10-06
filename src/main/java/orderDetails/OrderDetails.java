package orderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {

    public List<Product> productsInCart = new ArrayList<>();

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void addToProductsToTheCart(Product product) {
            getProductsInCart().add(product);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "productsInCart=" + productsInCart +
                '}';
    }
}
