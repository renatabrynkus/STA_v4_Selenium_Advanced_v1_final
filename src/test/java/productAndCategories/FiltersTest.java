package productAndCategories;

import base.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.FilterPage;
import pages.HeaderPage;
import pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest extends TestBase {

    @ParameterizedTest
    @CsvSource(value = {"9, 10"})
    void shouldDisplayCorrectProductsForFilter(int lowerBound, int higherBound) {
        logger.info("----------> Started test shouldDisplayCorrectProductsForFilter() <----------");
        HeaderPage headerPage = new HeaderPage(driver);
        FilterPage filterPage = new FilterPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        headerPage.clickOnCategory("ART")
                .isSideFilterDisplayed();
        int noOfProductsInCategory = productsPage.getProductsSize(productsPage.getAllProducts());

        filterPage.setPriceFilter(lowerBound, higherBound);
        assertThat(productsPage.areProductsInPriceRange(lowerBound, higherBound)).isTrue();
        filterPage.clearFilter();
        assertThat(noOfProductsInCategory).isEqualTo(productsPage.getProductsSize(productsPage.getAllProducts()));
    }
}
