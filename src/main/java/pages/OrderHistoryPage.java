package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @FindBy(css = "#content .links")
    private WebElement userAccountLinks;

    @FindBy(css = "#history-link .link-item")
    private WebElement orderHistoryBtn;

    @FindBy(css = "#content-wrapper")
    private WebElement orderHistory;

    @FindBy(css = "tbody tr")
    private List<WebElement> orders;


    public void goToOrderHistory() {
        wait.until(ExpectedConditions.visibilityOf(userAccountLinks));
        orderHistoryBtn.click();
        wait.until(ExpectedConditions.visibilityOf(orderHistory));
        openPreviousOrder();

        //order z carta musi sie zapisac wczesniej
    }

    private void openPreviousOrder() {
        for (OrderHistoryRowPage order : getAllOrders()) {
            if (order.getOrderReference().getText().equals(checkoutPage.getOrderReference())) {
                click(order.getDetails());
            }
        }
    }

    public List<OrderHistoryRowPage> getAllOrders() {
        List<OrderHistoryRowPage> allOrders = new ArrayList<>();
        for (WebElement order : orders) {
            allOrders.add(new OrderHistoryRowPage(order));
        }
        return allOrders;
    }


}
