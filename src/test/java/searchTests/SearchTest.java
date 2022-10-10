package searchTests;

import base.Pages;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends Pages {

    @RepeatedTest(10)
    void shouldSearchForRandomProduct() {
        logger.info("-----> Started test shouldSearchForRandomProduct() <-----");
        String expectedProduct = popularProductsPage.getRandomProductName();
        headerPage.enterTextToSearchField(expectedProduct)
                .clickSearch();
        assertThat(popularProductsPage.getProductName()).isEqualTo(expectedProduct);
    }

    @ParameterizedTest
    @ValueSource(strings = "HUMMINGBIRD")
    void shouldAutocompleteResultsContainProduct(String productName) {
        logger.info("-----> Started test shouldAutocompleteResultsContainProduct <-----");
        logger.info("-----> Checking if all products have a word {} ", productName + " <-----");
        headerPage.enterTextToSearchField(productName);
        for (int i = 0; i < headerPage.getDropdownSize(); i++) {
            assertThat(headerPage.getProductFromDropdownStr(i)).contains(productName);
        }
    }
}