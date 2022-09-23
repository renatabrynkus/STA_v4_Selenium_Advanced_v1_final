package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ProductMiniaturePage {

    public ProductMiniaturePage(WebElement productMiniature) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productMiniature), this);
    }

    @FindBy(css = ".h3.product-title")
    private WebElement productName;

    @FindBy(css = "div .price")
    private WebElement price;

    public String getProductName() {
        return productName.getText();
    }

    public int getPrice() {
        String productPrice = price.getText().replaceAll("\\$", "").replaceAll(".00", "");
        return Integer.parseInt(productPrice);
    }
}
