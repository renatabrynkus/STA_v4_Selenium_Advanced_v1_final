package searchTests;

import base.Pages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends Pages {

    @Test
    void shouldSearchForRandomProduct() {
        logger.info("----------> Started test shouldSearchForRandomProduct() <----------");
        String expectedProduct = productsPage.getRandomProductName();
        headerPage.enterTextToSearchField(expectedProduct)
                .clickSearch();
        assertThat(productsPage.getProductName()).isEqualTo(expectedProduct);
    }

    @ParameterizedTest
    @ValueSource(strings = "HUMMINGBIRD")
    void shouldAutocompleteResultsContainProduct(String productName) {
        logger.info("----------> Started test shouldAutocompleteResultsContainProduct <----------");
        logger.info("----------> Checking if all products have a word {} ", productName + " <----------");
        headerPage.enterTextToSearchField(productName);
        for (int i = 0; i < headerPage.getDropdownSize(); i++) {
            assertThat(headerPage.getProductFromDropdownStr(i)).contains("HUMMINGBIRD");
        }
    }
}