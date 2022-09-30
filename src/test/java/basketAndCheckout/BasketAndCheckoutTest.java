package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketAndCheckoutTest extends Pages {

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER, 3")
    void shouldAddProductToCart(String category, String productName, int quantity) throws InterruptedException {
        headerPage.clickOnCategory(category);
        productsPage.openProduct(productName);
        productPage.changeQuantity(quantity);


        assertThat(cartPopupPage.getProductName()).isEqualTo(productName);



        Thread.sleep(5000);

    }
}
