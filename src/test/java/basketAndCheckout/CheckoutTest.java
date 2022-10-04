package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends Pages {

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER, YOUR ORDER IS CONFIRMED, $29.00, $7.00")
    void shouldCheckout(String category, String productName, String orderConfirmationMsg, String unitPrice, String shipping) throws InterruptedException {
        headerPage.clickOnSignInButton()
                .logIn();
        headerPage.clickOnCategory(category);
        productsPage.openProduct(productName);
        productPage.addToCart()
                .clickProceedToCheckout()
                .processOrder();

        assertThat(cartPage.getOrderConfirmationMsg()).isEqualTo(orderConfirmationMsg);
        assertThat(cartPage.getProductName()).isEqualTo(productName);
        assertThat(cartPage.getUnitPrice()).isEqualTo(unitPrice);
        assertThat(cartPage.getPriceSum()).isEqualTo(cartPage.priceSumShouldBe());
        assertThat(cartPage.getShipping()).isEqualTo(shipping);



    }
}
