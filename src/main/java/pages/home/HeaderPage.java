package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.search.CategoryPage;
import pages.order.OrderHistoryPage;

import java.util.List;

public class HeaderPage extends BasePage {
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchField;

    @FindBy(css = ".search")
    private WebElement searchBtn;

    @FindBy(css = ".ui-corner-all > .product")
    private List<WebElement> dropdownList;

    @FindBy(css = ".ui-autocomplete")
    private WebElement dropdown;

    @FindBy(css = "#top-menu > .category")
    private List<WebElement> categories;

    @FindBy(css = ".cart-products-count")
    private WebElement cartCount;

    @FindBy(css = ".user-info .hidden-sm-down")
    private WebElement signInBtn;

    @FindBy(css = ".account > .hidden-sm-down")
    private WebElement userAccount;

    public HeaderPage enterTextToSearchField(String search) {
        searchField.sendKeys(search);
        return this;
    }

    public void clickSearch() {
        searchBtn.click();
    }

    public int getDropdownSize() {
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        return dropdownList.size();
    }

    public String getProductFromDropdownStr(int i) {
        return dropdownList.get(i).getText();
    }

    public CategoryPage clickOnCategory(String categoryName) {
        for (WebElement category : categories) {
            if (category.getText().equals(categoryName)) {
                category.click();
            }
        }
        return new CategoryPage(driver);
    }

    public String getCategoryName(WebElement category) {
        return category.getText();
    }

    public CategoryPage goToCategory(WebElement category) {
        category.click();
        return new CategoryPage(driver);
    }

    public int getCategoriesSize() {
        return categories.size();
    }

    public WebElement getCategory(int i) {
        return categories.get(i);
    }

    public int getCartCount() {
        logger.info("-----> Cart count is {}", cartCount.getText());
        return Integer.parseInt(cartCount.getText().replaceAll("\\(", "").replaceAll("\\)", ""));
    }

    public LoginPage clickOnSignInButton() {
        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        click(signInBtn);
        return new LoginPage(driver);
    }

    public OrderHistoryPage goToUserAccount () {
        click(userAccount);
        return new OrderHistoryPage(driver);
    }
}
