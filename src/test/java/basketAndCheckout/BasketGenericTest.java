package basketAndCheckout;

import base.Pages;
import orderDetails.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketGenericTest extends Pages {

    @ParameterizedTest
    @CsvSource("5")
    void basketShouldBeGeneric(int randomProductsAddedToCart) throws InterruptedException {
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

        double totalPriceSum = Double.parseDouble(productPage.getTotalPriceSum());
        for (int i = 0; i < cartPage.getProductsInCartPage().size(); i++) {
            double totalPriceSumProductRemoved = totalPriceSum -
                    productPage.getOrderDetails().getProductsInCart().get(0).getPrice() *
                            productPage.getOrderDetails().getProductsInCart().get(0).getQuantity();
            totalPriceSum = totalPriceSumProductRemoved;

            BigDecimal bd = new BigDecimal(totalPriceSumProductRemoved).setScale(2, RoundingMode.HALF_UP);
            double totalPriceSumProductRemoved2 = bd.doubleValue();

            cartPage.removeProductFromCart(cartPage.getProductFromOrderList());
            Product productToRemoveFromExpected = productPage.getOrderDetails().getProductsInCart().get(0);
            productPage.getOrderDetails().removeProduct(productToRemoveFromExpected);

            assertThat(cartPage.getOrderDetailsCart()).usingRecursiveComparison().isEqualTo(productPage.getOrderDetails());
            if (i == cartPage.getProductsInCartPage().size() - 1) {
                assertThat(Double.parseDouble(cartPage.getTotalOrderValue())).isEqualTo(totalPriceSumProductRemoved2 - 7.00);
            } else {
                assertThat(Double.parseDouble(cartPage.getTotalOrderValue())).isEqualTo(totalPriceSumProductRemoved2);
            }
        }
        assertThat(cartPage.getNoItemsMessage()).isEqualTo(System.getProperty("cartMessage"));
    }
}
