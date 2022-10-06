package basketAndCheckout;

import base.Pages;
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
            productPage.addToCart();
            cartPopupPage.clickContinueShopping();
            driver.navigate().back();
        }
        allProductsPage.goToCart();
        cartPage.addProductsFromCart();

        assertThat(cartPage.getOrderDetailsCart()).usingRecursiveComparison().isEqualTo(productPage.getOrderDetails());

        Thread.sleep(5000);
    }
}
