package base;

import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class Pages extends TestBase {

    public HeaderPage headerPage;
    public ProductsPage productsPage;
    public FilterPage filterPage;
    public ProductPage productPage;
    public CartPopupPage cartPopupPage;

    @BeforeEach
    public void pagesSetup() {
        headerPage = new HeaderPage(driver);
        productsPage = new ProductsPage(driver);
        filterPage = new FilterPage(driver);
        productPage = new ProductPage(driver);
        cartPopupPage = new CartPopupPage(driver);
    }
}
