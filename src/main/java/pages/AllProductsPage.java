package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public ProductPage selectRandomProduct() {
        ProductMiniaturePage randomProduct = addProductsToList().get(random.nextInt(addProductsToList().size()));
        logger.info("-----> Random product is {}", randomProduct.getProductName());
        randomProduct.getThumbnail().click();
        return new ProductPage(driver);
    }

    public void goToCart() {
        driver.get(System.getProperty("basketUrl"));
    }
}
