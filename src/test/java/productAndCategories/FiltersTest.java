package productAndCategories;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest extends Pages {

    @ParameterizedTest
    @CsvSource(value = {"9, 10"})
    void shouldDisplayCorrectProductsForFilter(int lowerBound, int higherBound) {
        logger.info("----------> Started test shouldDisplayCorrectProductsForFilter() <----------");

        headerPage.clickOnCategory("ART")
                .isSideFilterDisplayed();
        int noOfProductsInCategory = productsPage.getProductsSize(productsPage.getAllProducts());

        filterPage.setPriceFilter(lowerBound, higherBound);
        assertThat(productsPage.areProductsInPriceRange(lowerBound, higherBound)).isTrue();
        filterPage.clearFilter();
        assertThat(noOfProductsInCategory).isEqualTo(productsPage.getProductsSize(productsPage.getAllProducts()));
    }
}
