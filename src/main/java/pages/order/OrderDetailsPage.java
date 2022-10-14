package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class OrderDetailsPage extends BasePage {

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-history td:nth-child(1)")
    private WebElement orderDate;

    @FindBy(css = "#order-history td:nth-child(2)")
    private WebElement status;

    @FindBy(css = ".line-total td:nth-child(2)")
    private WebElement totalPrice;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    public String getOrderDate() {
        logger.info("-----> Order's date is {}", orderDate.getText() + " <-----");
        return orderDate.getText();
    }

    public String getStatus() {
        logger.info("-----> Order's status is {}", status.getText() + " <-----");
        return status.getText();
    }

    public String getTotalPrice() {
        logger.info("-----> Order's total price is {}", totalPrice.getText() + " <-----");
        return totalPrice.getText();
    }

    public String getDeliveryAddress() {
        logger.info("-----> Delivery address is {}", deliveryAddress.getText() + " <-----");
        return deliveryAddress.getText();
    }

    public String getInvoiceAddress() {
        logger.info("-----> Invoice address is {}", invoiceAddress.getText() + " <-----");
        return invoiceAddress.getText().strip();
    }
}
