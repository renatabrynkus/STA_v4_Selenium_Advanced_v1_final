package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver() {
        String browserName = System.getProperty("browserName");
        switch (browserName) {
            case "FIREFOX" -> driver = getFirefox();
            case "IE" -> driver = getIE();
            case "EDGE" -> driver = getEdge();
            default -> driver = getChrome();
        }
        return driver;
    }

    private WebDriver getChrome() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("--disable-notifications");
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(optionsChrome);
        return driver;
    }
    private WebDriver getFirefox() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("--disable-notifications");
        optionsFirefox.addArguments("start-maximized");
        driver = new FirefoxDriver(optionsFirefox);
        return driver;
    }
    private WebDriver getIE() {
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver(optionsIE);
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriver getEdge() {
        EdgeOptions optionsEdge = new EdgeOptions();
        WebDriverManager.chromedriver().setup();
        optionsEdge.addArguments("--disable-notifications");
        optionsEdge.addArguments("start-maximized");
        driver = new EdgeDriver(optionsEdge);
        return driver;
    }
}
