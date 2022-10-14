package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.product.AllProductsPage;
import pages.BasePage;
import pages.product.ProductMiniaturePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopularProductsPage extends BasePage {

    public PopularProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product")
    private List<WebElement> popularProducts;

    @FindBy(css = ".all-product-link")
    private WebElement allProductsBtn;

    @FindBy(css = ".h1")
    private WebElement homeField;

    public List<ProductMiniaturePage> getAllPopularProducts() {
        List<ProductMiniaturePage> allPopularProductsPOP = new ArrayList<>();
        for (WebElement popularProduct : popularProducts) {
            allPopularProductsPOP.add(new ProductMiniaturePage(popularProduct));
        }
        return allPopularProductsPOP;
    }

    public void openProduct(String productToOpen) {
        for (ProductMiniaturePage productMiniature : getAllPopularProducts()) {
            if (productMiniature.getProductName().equals(productToOpen)) {
                logger.info("-----> Clicking on {}", productMiniature.getProductName());
                productMiniature.getThumbnail().click();
                break;
            }
        }
    }

    public int getProductsSize(List<ProductMiniaturePage> products) {
        return products.size();
    }

    public boolean areProductsInPriceRange(double lowerBound, double higherBound) {
        for (ProductMiniaturePage popularProduct : getAllPopularProducts()) {
            if (!(popularProduct.getCurrentPrice() >= lowerBound) && !(popularProduct.getCurrentPrice() <= higherBound)) {
                return false;
            }
        }
        return true;
    }

    public String getProductName() {
        return getAllPopularProducts().get(0).getProductName();
    }

    public String getRandomProductName() {
        Random random = new Random();
        return getAllPopularProducts().get(random.nextInt(popularProducts.size())).getProductName();
    }

    public void openAllProducts() {
        click(allProductsBtn);
        wait.until(ExpectedConditions.visibilityOf(homeField));
        new AllProductsPage(driver);
    }
}
