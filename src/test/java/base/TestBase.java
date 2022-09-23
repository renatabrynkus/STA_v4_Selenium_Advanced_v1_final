package base;

import configuration.AppProperties;
import configuration.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;
    protected DriverFactory driverFactory;
    private static AppProperties appProperties;

    @BeforeAll
    static void beforeAll() {
        logger.info("----------> Getting properties from config.yaml file <----------");
        appProperties = AppProperties.getInstance();
    }

    @BeforeEach
    void setUp() {
        driverFactory = new DriverFactory();
        driver = driverFactory.getDriver();
        logger.info("---------> Opening the website <----------");
        driver.get(System.getProperty("appUrl"));
    }

    @AfterEach
    void quit() {
        logger.info("----------> Quitting driver <----------");
        driver.quit();
    }
}
