package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends Pages {
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER, YOUR ORDER IS CONFIRMED, 29.00, $7.00, ")
    void shouldCheckout(String category, String productName, String orderConfirmationMsg, String unitPrice, String shipping) {
        headerPage.clickOnSignInButton()
                .logIn();
        headerPage.clickOnCategory(category);
        popularProductsPage.openProduct(productName);
        productPage.addToCart();
        cartPopupPage.clickProceedToCheckout()
                .processOrder();

        assertThat(checkoutPage.getOrderConfirmationMsg()).isEqualTo(orderConfirmationMsg);
        assertThat(checkoutPage.getProductName()).isEqualTo(productName);
        assertThat(checkoutPage.getUnitPrice()).isEqualTo(unitPrice);
        assertThat(checkoutPage.getPriceSum()).isEqualTo(checkoutPage.priceSumShouldBe());
        assertThat(checkoutPage.getShippingPrice()).isEqualTo(shipping);
        assertThat(checkoutPage.getPaymentMethod()).isEqualTo(System.getProperty("payment"));
        assertThat(checkoutPage.getShippingMethod()).isEqualTo(System.getProperty("shippingMethod"));
        assertThat(checkoutPage.getTotalPriceFromCheck()).isEqualTo(checkoutPage.getTotalPriceFromOrder());
        String orderRefFromCheckout = checkoutPage.getOrderReference();

        headerPage.goToUserAccount()
                .goToOrderHistory(orderRefFromCheckout);

        assertThat(orderDetailsPage.getOrderDate()).isEqualTo(dateFormat.format(date));
        assertThat(orderDetailsPage.getStatus()).isEqualTo(System.getProperty("status"));
        assertThat(orderDetailsPage.getTotalPrice()).isEqualTo(System.getProperty("totalPrice"));
        assertThat(orderDetailsPage.getDeliveryAddress()).isEqualTo(System.getProperty("deliveryAddress"));
        assertThat(orderDetailsPage.getInvoiceAddress()).isEqualTo(System.getProperty("invoiceAddress"));
    }
}
