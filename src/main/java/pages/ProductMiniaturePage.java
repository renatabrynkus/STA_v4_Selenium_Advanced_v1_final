package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ProductMiniaturePage {

    public ProductMiniaturePage(WebElement productMiniature) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productMiniature), this);
    }

    @FindBy(css = ".h3.product-title > a")
    private WebElement productName;

    @FindBy(css = "div .price")
    private WebElement currentPrice;

    @FindBy(css = ".regular-price")
    private WebElement priceBeforeSale;

    @FindBy(css = ".thumbnail")
    private WebElement thumbnail;

    public String getProductName() {
        return productName.getText();
    }

    public double getCurrentPrice() {
        String productPrice = currentPrice.getText().replaceAll("\\$", "");
        return Double.parseDouble(productPrice);
    }

    public WebElement getThumbnail() {
        return thumbnail;
    }

    public WebElement getPriceBeforeSale() {
        return priceBeforeSale;
    }
}
