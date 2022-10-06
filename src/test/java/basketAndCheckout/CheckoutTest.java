package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends Pages {

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER, YOUR ORDER IS CONFIRMED, 29.00, $7.00")
    void shouldCheckout(String category, String productName, String orderConfirmationMsg, String unitPrice, String shipping) throws InterruptedException {
        headerPage.clickOnSignInButton()
                .logIn();
        headerPage.clickOnCategory(category);
        popularProductsPage.openProduct(productName);
        productPage.addToCart()
                .clickProceedToCheckout()
                .processOrder();

        assertThat(checkoutPage.getOrderConfirmationMsg()).isEqualTo(orderConfirmationMsg);
        assertThat(checkoutPage.getProductName()).isEqualTo(productName);
        assertThat(checkoutPage.getUnitPrice()).isEqualTo(unitPrice);
        assertThat(checkoutPage.getPriceSum()).isEqualTo(checkoutPage.priceSumShouldBe());
        assertThat(checkoutPage.getShippingPrice()).isEqualTo(shipping);
        assertThat(checkoutPage.getPaymentMethod()).isEqualTo(System.getProperty("payment"));
        assertThat(checkoutPage.getShippingMethod()).isEqualTo(System.getProperty("shippingMethod"));
        assertThat(checkoutPage.getTotalPriceFromCheck()).isEqualTo(checkoutPage.getTotalPriceFromOrder());

        headerPage.goToUserAccount()
                .goToOrderHistory();
        Thread.sleep(5000);




    }
}
