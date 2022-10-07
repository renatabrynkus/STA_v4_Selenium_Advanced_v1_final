package orderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {

    public List<Product> productsInCart = new ArrayList<>();

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public void addToProductsToTheCart(Product product) {
        if (productsInCart.stream().anyMatch(o -> o.getName().equals(product.getName()))) {
            for (Product productInCart : productsInCart) {
                if (productInCart.getName().equals(product.getName())) {
                    productInCart.setQuantity(productInCart.getQuantity() + product.getQuantity());
                }
            }
        } else {
            getProductsInCart().add(product);
        }
    }

    public void removeProduct(Product product) {
        productsInCart.remove(product);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "productsInCart=" + productsInCart +
                '}';
    }
}
