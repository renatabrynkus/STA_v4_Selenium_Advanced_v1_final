package productAndCategories;

import base.Pages;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.WebElement;
import pages.search.CategoryPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTest extends Pages {

    @RepeatedTest(10)
    void shouldCheckCategories() {
        logger.info("-----> started test shouldCheckCategories <------");
        for (int i = 0; i < headerPage.getCategoriesSize(); i++) {
            CategoryPage categoryPage = headerPage.goToCategory(headerPage.getCategory(i));

            assertThat(categoryPage.getCategoryName()).isEqualTo(headerPage.getCategoryName(headerPage.getCategory(i)));
            assertThat(categoryPage.isSideFilterDisplayed()).isTrue();
            assertThat(categoryPage.getNumberOfProductsInCategoryMsg()).isEqualTo(categoryPage.countProductsInCategory());
        }
    }

    @RepeatedTest(10)
    void shouldCheckSubcategories() {
        logger.info("-----> Started test shouldCheckSubcategories() <-----");
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
