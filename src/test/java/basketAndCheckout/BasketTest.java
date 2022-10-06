package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest extends Pages {

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER, 3")
    void shouldAddProductToCart(String category, String productName, int quantity) {
        headerPage.clickOnCategory(category);
        popularProductsPage.openProduct(productName);
        productPage.changeQuantity(quantity);

        assertThat(cartPopupPage.getProductName()).isEqualTo(productName);
        assertThat(cartPopupPage.getUnitPrice()).isEqualTo(productPage.getPrice());
        assertThat(cartPopupPage.getQuantity()).isEqualTo(String.valueOf(quantity));
        assertThat(cartPopupPage.getNumberOfProductsFromMessage()).isEqualTo(String.valueOf(quantity));
        assertThat(cartPopupPage.getTotalValue()).isEqualTo(cartPopupPage.totalValueShouldBe());
        cartPopupPage.clickContinueShopping();
        assertThat(headerPage.getCartCount()).isEqualTo(quantity);
    }
}
