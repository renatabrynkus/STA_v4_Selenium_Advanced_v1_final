package base;

import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class Pages extends TestBase {

    public HeaderPage headerPage;
    public PopularProductsPage popularProductsPage;
    public FilterPage filterPage;
    public ProductPage productPage;
    public CartPopupPage cartPopupPage;
    public CheckoutPage checkoutPage;
    public AllProductsPage allProductsPage;
    public CartPage cartPage;

    @BeforeEach
    public void pagesSetup() {
        headerPage = new HeaderPage(driver);
        popularProductsPage = new PopularProductsPage(driver);
        filterPage = new FilterPage(driver);
        productPage = new ProductPage(driver);
        cartPopupPage = new CartPopupPage(driver);
        checkoutPage = new CheckoutPage(driver);
        allProductsPage = new AllProductsPage(driver);
        cartPage = new CartPage(driver);
    }
}
