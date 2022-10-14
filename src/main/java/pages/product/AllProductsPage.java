package pages.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.product.ProductMiniaturePage;
import pages.product.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class AllProductsPage extends BasePage {
    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product")
    private List<WebElement> allProducts;

    public List<ProductMiniaturePage> addProductsToList() {
        List<ProductMiniaturePage> allProductsPOPList = new ArrayList<>();
        for (WebElement product : allProducts) {
            allProductsPOPList.add(new ProductMiniaturePage(product));
        }
        return allProductsPOPList;
    }

    public void selectRandomProduct() {
        ProductMiniaturePage randomProduct = addProductsToList().get(random.nextInt(addProductsToList().size()));
        logger.info("-----> Random product is {}", randomProduct.getProductName());
        randomProduct.getThumbnail().click();
        new ProductPage(driver);
    }

    public void goToCart() {
        driver.get(System.getProperty("basketUrl"));
    }
}
