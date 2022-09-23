package searchTests;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.HeaderPage;
import pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends TestBase {

    @Test
    void shouldSearchForRandomProduct() {
        logger.info("----------> Started test shouldSearchForRandomProduct() <----------");
        ProductsPage productsPage = new ProductsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
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
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.enterTextToSearchField(productName);
        assertThat(headerPage.ifAllDropdownProductsContainName(productName)).isEqualTo(true);
    }
}