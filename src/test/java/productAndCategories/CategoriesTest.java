package productAndCategories;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.HeaderPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTest extends TestBase {

    @Test
    void shouldCheckCategories() {
        logger.info("----------> started test shouldCheckCategories <-----------");
        HeaderPage headerPage = new HeaderPage(driver);
        List<WebElement> categories = headerPage.getCategories();

        for (int i = 0; i < categories.size(); i++) {
            CategoryPage categoryPage = headerPage.goToCategory(categories.get(i));

            assertThat(categoryPage.getCategoryName()).isEqualTo(headerPage.getCategoryName(categories.get(i)));
            assertThat(categoryPage.isSideFilterDisplayed()).isTrue();
            assertThat(categoryPage.getNumberOfProductsInCategoryMsg()).isEqualTo(categoryPage.countProductsInCategory());
        }
    }

    @Test
    void shouldCheckSubcategories() {
        logger.info("----------> Started test shouldCheckSubcategories() <----------");
        HeaderPage headerPage = new HeaderPage(driver);
        List<WebElement> categories = headerPage.getCategories();

        for (int i = 0; i < categories.size(); i++) {
            CategoryPage categoryPage = headerPage.goToCategory(categories.get(i));
            if (categoryPage.areSubcategoriesAvailable()) {
                List<WebElement> subcategories = categoryPage.getSubcategories();

                for (int j = 0; j < subcategories.size(); j++) {
                    WebElement currentCategory = subcategories.get(j);
                    String subcategoryName = categoryPage.getSubcategoryName(currentCategory);
                    currentCategory.click();

                    assertThat(categoryPage.getCategoryName()).isEqualTo(subcategoryName);
                    assertThat(categoryPage.isSideFilterDisplayed()).isTrue();
                    assertThat(categoryPage.getNumberOfProductsInCategoryMsg()).isEqualTo(categoryPage.countProductsInCategory());
                    driver.navigate().back();
                }
            }
        }
    }
}
