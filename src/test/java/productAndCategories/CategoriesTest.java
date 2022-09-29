package productAndCategories;

import base.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.HeaderPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTest extends TestBase {

    @Test
    void shouldCheckCategories() {
        logger.info("----------> started test shouldCheckCategories <-----------");
        HeaderPage headerPage = new HeaderPage(driver);

        for (int i = 0; i < headerPage.getCategoriesSize(); i++) {
            CategoryPage categoryPage = headerPage.goToCategory(headerPage.getCategory(i));

            assertThat(categoryPage.getCategoryName()).isEqualTo(headerPage.getCategoryName(headerPage.getCategory(i)));
            assertThat(categoryPage.isSideFilterDisplayed()).isTrue();
            assertThat(categoryPage.getNumberOfProductsInCategoryMsg()).isEqualTo(categoryPage.countProductsInCategory());
        }
    }

    @Test
    void shouldCheckSubcategories() {
        logger.info("----------> Started test shouldCheckSubcategories() <----------");
        HeaderPage headerPage = new HeaderPage(driver);

        for (int i = 0; i < headerPage.getCategoriesSize(); i++) {
            CategoryPage categoryPage = headerPage.goToCategory(headerPage.getCategory(i));
            if (categoryPage.areSubcategoriesAvailable()) {

                for (int j = 0; j < categoryPage.getSubcategoriesSize(); j++) {
                    WebElement currentCategory = categoryPage.getSubcategory(j);
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
