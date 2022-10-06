package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        logger.info("----> The size of products list is {} ", products.size() + " <-----");
        return products.size();
    }

    public boolean areProductsInPriceRange(double lowerBound, double higherBound) {
        for (ProductMiniaturePage popularProduct : getAllPopularProducts()) {
            if (!(popularProduct.getCurrentPrice() >= lowerBound) && !(popularProduct.getCurrentPrice() <= higherBound)) {
                logger.info("----------> The product {} is not in the specified range", popularProduct.getProductName());
                return false;
            }
        }
        logger.info("-----> All products are in the specified price range <-----");
        return true;
    }

    public String getProductName() {
        String productName = getAllPopularProducts().get(0).getProductName();
        logger.info("----------> Product found is {}", productName + " <----------");
        return productName;
    }

    public String getRandomProductName() {
        Random random = new Random();
        String randomProductName = getAllPopularProducts().get(random.nextInt(popularProducts.size())).getProductName();
        logger.info("----------> Random product name is {} ", randomProductName + " <----------");
        return randomProductName;
    }

    public AllProductsPage openAllProducts() {
        click(allProductsBtn);
        wait.until(ExpectedConditions.visibilityOf(homeField));
        return new AllProductsPage(driver);
    }
}
