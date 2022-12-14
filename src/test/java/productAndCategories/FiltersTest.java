package productAndCategories;

import base.Pages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest extends Pages {

    @ParameterizedTest
    @CsvSource(value = {"ART, 9.00, 10.00"})
    void shouldDisplayCorrectProductsForFilter(String category, double lowerBound, double higherBound) {
        logger.info("-----> Started test shouldDisplayCorrectProductsForFilter() <-----");
        headerPage.clickOnCategory(category)
                .isSideFilterDisplayed();
        int noOfProductsInCategory = popularProductsPage.getProductsSize(popularProductsPage.getAllPopularProducts());

        filterPage.setPriceFilter(lowerBound, higherBound);
        assertThat(popularProductsPage.areProductsInPriceRange(lowerBound, higherBound)).isTrue();
        filterPage.clearFilter();
        assertThat(noOfProductsInCategory).isEqualTo(popularProductsPage.getProductsSize(popularProductsPage.getAllPopularProducts()));
    }
}
