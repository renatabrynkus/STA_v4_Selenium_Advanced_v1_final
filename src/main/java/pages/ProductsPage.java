package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product")
    private List<WebElement> popularProducts;

    public List<ProductMiniaturePage> getAllProducts() {
        List<ProductMiniaturePage> allPopularProductsPOP = new ArrayList<>();
        for (WebElement popularProduct : popularProducts) {
            allPopularProductsPOP.add(new ProductMiniaturePage(popularProduct));
        }
        return allPopularProductsPOP;
    }

    public int getProductsSize(List<ProductMiniaturePage> products) {
        logger.info("---------> The size of products list is {} ", products.size() + " <----------");
        return products.size();
    }

    public boolean areProductsInPriceRange(int lowerBound, int higherBound) {
        for (ProductMiniaturePage popularProduct : getAllProducts()) {
            if (!(popularProduct.getPrice() >= lowerBound) && !(popularProduct.getPrice() <= higherBound)) {
                logger.info("----------> The product {} is not in the specified range", popularProduct.getProductName());
                return false;
            }
        }
        logger.info("----------> All products are in the specified price range <----------");
        return true;
    }

    public String getProductName() {
        String productName = getAllProducts().get(0).getProductName();
        logger.info("----------> Product found is {}", productName + " <----------");
        return productName;
    }

    public String getRandomProductName() {
        Random random = new Random();
        String randomProductName = getAllProducts().get(random.nextInt(popularProducts.size())).getProductName();
        logger.info("----------> Random product name is {} ", randomProductName + " <----------");
        return randomProductName;
    }

}
