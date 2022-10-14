package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.text.DecimalFormat;
import java.util.List;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private static final DecimalFormat df = new DecimalFormat("#.00");

    @FindBy(css = ".text-sm-center > .btn-primary")
    private WebElement proceedToCheckoutBtn;

    @FindBy(css = "[data-link-action=\"different-invoice-address\"]")
    private WebElement differentBillingAddress;

    @FindBy(css = "#checkout-addresses-step")
    private WebElement addressForm;

    @FindBy(css = "[name=\"address1\"]")
    private WebElement address;

    @FindBy(css = "[name=\"postcode\"]")
    private WebElement postcode;

    @FindBy(css = "[name=\"city\"]")
    private WebElement city;

    @FindBy(css = ".form-footer .continue")
    private WebElement addressContinueBtn;

    @FindBy(css = "#js-delivery > .continue")
    private WebElement deliveryContinueBtn;

    @FindBy(css = "#delivery_option_2")
    private WebElement myCarrierBtn;

    @FindBy(css = ".col-sm-1 > .custom-radio")
    private List<WebElement> shippingOptions;

    @FindBy(css = "#payment-option-1")
    private WebElement payByCheckBtn;

    @FindBy(css = ".custom-checkbox")
    private WebElement termsOfService;

    @FindBy(css = ".ps-shown-by-js > .btn")
    private WebElement placeOrderBtn;

    @FindBy(css = "[href=\"http://146.59.32.4/index.php?controller=order&newAddress=invoice\"]")
    private WebElement addInvoiceAddress;

    @FindBy(css = ".card-title")
    private WebElement orderConfirmationMsg;

    @FindBy(css = ".details")
    private WebElement productName;

    @FindBy(css = ".text-xs-left")
    private WebElement unitPrice;

    @FindBy(css = ".row > .bold")
    private WebElement priceSum;

    @FindBy(css = ".total-value td:nth-child(2)")
    private WebElement totalPrice;

    @FindBy(css = ".row > .col-xs-4:nth-child(2)")
    private WebElement quantity;

    @FindBy(css = "tr:nth-child(2) td:nth-child(2)")
    private WebElement shippingPrice;

    @FindBy(css = "#order-details li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "#order-details li:nth-child(3)")
    private WebElement shippingMethod;

    @FindBy(css = ".price strong")
    private WebElement totalPriceFromCheck;

    @FindBy(css = "#order-details li:nth-child(1)")
    private WebElement orderReference;

    private void proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutBtn));
        proceedToCheckoutBtn.click();
        wait.until(ExpectedConditions.visibilityOf(differentBillingAddress));
    }

    public void processOrder() {
        proceedToCheckout();
        click(differentBillingAddress);
        addInvoiceAddress();
        chooseShippingMethod();
        payByCheckBtn.click();
        termsOfService.click();
        placeOrderBtn.click();
    }

    private void chooseShippingMethod() {
        myCarrierBtn.click();
        click(deliveryContinueBtn);
    }

    private void addInvoiceAddress() {
        wait.until(ExpectedConditions.visibilityOf(addressForm));
        addInvoiceAddress.click();
        address.sendKeys(System.getProperty("address"));
        postcode.sendKeys(System.getProperty("postcode"));
        city.sendKeys(System.getProperty("city"));
        click(addressContinueBtn);
    }

    public String getOrderConfirmationMsg() {
        return orderConfirmationMsg.getText().substring(1);
    }

    public String getProductName() {
        return productName.getText().substring(0, 22);
    }

    public String getUnitPrice() {
        return unitPrice.getText().substring(1);
    }

    public String getPriceSum() {
        return priceSum.getText().substring(1);
    }

    public String priceSumShouldBe() {
        double unitPrice = Double.parseDouble(getUnitPrice().replaceAll("\\$", ""));
        logger.info("-----> Price sum is {}", df.format(unitPrice *
                Integer.parseInt(quantity.getText())).replaceAll(",", ".") + " <-----");
        return df.format(unitPrice * Integer.parseInt(quantity.getText())).replaceAll(",", ".");
    }

    public String getShippingPrice() {
        return shippingPrice.getText();
    }

    public String getTotalPriceFromOrder() {
        return totalPrice.getText();
    }

    public String getPaymentMethod() {
        return paymentMethod.getText().substring(16);
    }

    public String getShippingMethod() {
        return shippingMethod.getText().substring(17, 27);
    }

    public String getTotalPriceFromCheck() {
        return totalPriceFromCheck.getText();
    }

    public String getOrderReference() {
        return orderReference.getText().substring(17);
    }

}
