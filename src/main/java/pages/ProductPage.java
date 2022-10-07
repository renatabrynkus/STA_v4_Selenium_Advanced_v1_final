package pages;

import orderDetails.OrderDetails;
import orderDetails.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private OrderDetails orderDetailsAdded = new OrderDetails();
    double totalPriceSumWithShipping = 7.00;

    @FindBy(css = ".h1")
    private WebElement name;
    @FindBy(css = ".bootstrap-touchspin")
    private WebElement quantity;

    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement quantityUp;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = ".current-price span")
    private WebElement price;

    @FindBy(css = "quantity_wanted")
    private WebElement quantityToAdd;

    @FindBy(css = ".h1")
    private WebElement productName;

    public void changeQuantity(int qtyUp) {
        wait.until(ExpectedConditions.visibilityOf(quantity));
        for (int i = 1; i < qtyUp; i++) {
            clickOnQty();
        }
        addToCartRandomQty();
    }

    public String getPrice() {
        return price.getText();
    }

    private void clickOnQty() {
        logger.info("-----> Increasing quantity <-----");
        quantityUp.click();
    }

    public double addToCartRandomQty() {
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
        int currentQuantity = changeQtyToRandom();
        click(addToCartBtn);
        Product productToAdd = new Product(productName.getText(),
                Double.parseDouble(price.getText().substring(1)), currentQuantity,
                Double.parseDouble(price.getText().substring(1)) *
                        Double.parseDouble(String.valueOf(currentQuantity)));
        orderDetailsAdded.addToProductsToTheCart(productToAdd);
        totalPriceSumWithShipping += productToAdd.getTotalPrice();
        BigDecimal bd = new BigDecimal(totalPriceSumWithShipping).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
        click(addToCartBtn);
    }

    public String getTotalPriceSum() {
        return df.format(totalPriceSumWithShipping).replaceAll(",", ".");
    }

    public OrderDetails getOrderDetails() {
        return orderDetailsAdded;
    }

    private int changeQtyToRandom() {
        int currentQuantity = 1;
        int numberOfQtyToIncrease = random.nextInt(4);
        for (int i = 0; i < numberOfQtyToIncrease; i++) {
            currentQuantity += 1;
            quantityUp.click();
            logger.info("-----> Increased quantity +1 <-----");
        }
        return currentQuantity;
    }
}