package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement category;

    @FindBy(css = "#search_filters")
    private WebElement filterSideMenu;

    @FindBy(css = ".product")
    private List<WebElement> productsInCategory;

    @FindBy(css = ".total-products")
    private WebElement categoryMsg;

    @FindBy(css = ".category-top-menu")
    private WebElement categoryTopMenu;

    @FindBy(css = ".category-sub-menu a")
    private List<WebElement> subcategories;

    public String getCategoryName() {
        String categoryName = category.getText();
        logger.info("----------> Category name is {}", categoryName + " <----------");
        return categoryName;
    }

    public boolean isSideFilterDisplayed() {
        logger.info("----------> Checking if filter side menu is present <-----------");
        return filterSideMenu.isDisplayed();
    }

    public int countProductsInCategory() {
        return productsInCategory.size();
    }

    public String getCategoryMsg() {
        return categoryMsg.getText();
    }

    public List<WebElement> getSubcategories() {
        return subcategories;
    }

    public String getSubcategoryName(WebElement subcategory) {
        String subcategoryName = subcategory.getText();
        logger.info("----------> Subcategory in left menu is {}", subcategoryName + " <----------");
        return subcategoryName.toUpperCase();
    }

    public int getNumberOfProductsInCategoryMsg() {
        String categoryMsg = getCategoryMsg();
        String numberOfProducts = "";
        for (int i = 0; i < categoryMsg.length(); i++) {
            if (Character.isDigit(categoryMsg.charAt(i))) {
                numberOfProducts += categoryMsg.charAt(i);
            }
        }
        logger.info("----------> There are {}", numberOfProducts + " products in this category <----------");
        return Integer.parseInt(numberOfProducts);
    }

    public boolean areSubcategoriesAvailable() {
        logger.info("----------> There are {} ", subcategories.size() + " subcategories");
        return subcategories.size() > 0;
    }
}
