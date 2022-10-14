package base;

import org.junit.jupiter.api.BeforeEach;
import pages.checkout.CartPage;
import pages.checkout.CheckoutPage;
import pages.home.HeaderPage;
import pages.home.PopularProductsPage;
import pages.order.OrderDetailsPage;
import pages.order.OrderHistoryPage;
import pages.product.AllProductsPage;
import pages.product.CartPopupPage;
import pages.product.ProductPage;
import pages.search.FilterPage;

public class Pages extends TestBase {

    public HeaderPage headerPage;
    public PopularProductsPage popularProductsPage;
    public FilterPage filterPage;
    public ProductPage productPage;
    public CartPopupPage cartPopupPage;
    public CheckoutPage checkoutPage;
    public AllProductsPage allProductsPage;
    public CartPage cartPage;
    public OrderDetailsPage orderDetailsPage;
    public OrderHistoryPage orderHistoryPage;

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
        orderDetailsPage = new OrderDetailsPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
    }
}
