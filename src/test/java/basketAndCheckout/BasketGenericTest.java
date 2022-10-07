package basketAndCheckout;

import base.Pages;
import orderDetails.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketGenericTest extends Pages {

    @ParameterizedTest
    @CsvSource("5")
    void test(int randomProductsAddedToCart) throws InterruptedException {
        popularProductsPage.openAllProducts();
        for (int i = 0; i < randomProductsAddedToCart; i++) {
            allProductsPage.selectRandomProduct();
            productPage.addToCartRandomQty();
            cartPopupPage.clickContinueShopping();
            driver.navigate().back();
        }
        allProductsPage.goToCart();
        cartPage.addProductsFromCart();

        assertThat(cartPage.getOrderDetailsCart()).usingRecursiveComparison().isEqualTo(productPage.getOrderDetails());
        assertThat(cartPage.getTotalOrderValue()).isEqualTo(productPage.getTotalPriceSum());

        for (int i = 0; i < cartPage.getProductsInCartPage().size(); i++) {
            cartPage.removeProductFromCart(cartPage.getProductFromOrderList());
            Product productToRemoveFromExpected = productPage.getOrderDetails().getProductsInCart().get(0);
            productPage.getOrderDetails().removeProduct(productToRemoveFromExpected);
            assertThat(cartPage.getOrderDetailsCart()).usingRecursiveComparison().isEqualTo(productPage.getOrderDetails());
//            productPage.getPrice();
//            assertThat(cartPage.getTotalOrderValue()).isEqualTo(productPage.getTotalPriceSum());
            //expected suma musi się zmniejszać



        }
        assertThat(cartPage.getNoItemsMessage()).isEqualTo(System.getProperty("cartMessage"));

        Thread.sleep(5000);
    }
}
