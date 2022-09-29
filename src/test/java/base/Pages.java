package base;

import org.junit.jupiter.api.BeforeEach;
import pages.FilterPage;
import pages.HeaderPage;
import pages.ProductsPage;

public class Pages extends TestBase {

    public HeaderPage headerPage;
    public ProductsPage productsPage;
    public FilterPage filterPage;

    @BeforeEach
    public void pagesSetup() {
        headerPage = new HeaderPage(driver);
        productsPage = new ProductsPage(driver);
        filterPage = new FilterPage(driver);
    }
}
