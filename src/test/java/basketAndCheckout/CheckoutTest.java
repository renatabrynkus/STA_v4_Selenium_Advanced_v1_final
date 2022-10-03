package basketAndCheckout;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CheckoutTest extends Pages {

    @ParameterizedTest
    @CsvSource("ART, THE BEST IS YET POSTER")
    void shouldCheckout(String category, String productName) {
        headerPage.clickOnSignInButton()
                .logIn();
        headerPage.clickOnCategory(category);
        productsPage.openProduct(productName);
        productPage.addToCart()
                .clickProceedToCheckout();


    }
}
