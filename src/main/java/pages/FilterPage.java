package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FilterPage extends BasePage {

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-slider-handle")
    private List<WebElement> sliderHandles;

    @FindBy(css = ".ui-slider :nth-child(2)")
    private WebElement leftSlider;

    @FindBy(css = ".ui-slider :nth-child(3)")
    private WebElement rightSlider;

    @FindBy(css = "li p")
    private WebElement priceRange;

    @FindBy(css = ".close")
    private WebElement filterClearance;

    public String[] getPricesFromPriceRange() {
        String priceRangeTxt = priceRange.getText().replaceAll("\\$", "").replaceAll(".00", "");

        String[] priceBounds = priceRangeTxt.split(" - ");
        logger.info("-----------> Lower bound is {}", priceBounds[0] + " <----------");
        logger.info("-----------> Higher bound is {}", priceBounds[1] + " <----------");

        return priceBounds;
    }

    public FilterPage setPriceFilter(int lowerBoundToSet, int higherBoundToSet) throws InterruptedException {
        String[] priceBounds = getPricesFromPriceRange();
        int lowerBound = Integer.parseInt(priceBounds[0]);
        int higherBound = Integer.parseInt(priceBounds[1]);
        getPriceRange();

        if (lowerBoundToSet > lowerBound && lowerBoundToSet < higherBound) {
            for (int i = lowerBound; i < lowerBoundToSet; i++) {
                leftSlider.sendKeys(Keys.ARROW_RIGHT);
                Thread.sleep(300);
                logger.info("----------> Moved left slider to the right <----------");
            }
        } else {
            logger.info("----------> Lower bound is already set <----------");
        }

        if (higherBoundToSet < higherBound && higherBoundToSet > lowerBoundToSet) {
            for (int i = higherBound; i > higherBoundToSet; i--) {
                rightSlider.sendKeys(Keys.ARROW_LEFT);
                Thread.sleep(300);
                logger.info("----------> Moved right slider to the left <----------");
            }
        } else {
            logger.info("----------> Higher bound is already set <----------");
        }
        getPriceRange();
        return this;
    }

    public String getPriceRange() {
        logger.info("----------> Current price range is {}", priceRange.getText() + " <----------");
        return priceRange.getText();
    }

    public void clearFilter() throws InterruptedException {
        logger.info("----------> Clearing the filter <----------");
        filterClearance.click();
        Thread.sleep(300);
    }
}
