package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-md-6 > .form-control")
    private WebElement email;

    @FindBy(css = ".js-visible-password")
    private WebElement password;

    @FindBy(css = "#submit-login")
    private WebElement signInBtn;

    @FindBy(css = "#content")
    private WebElement loginForm;

    public void logIn() {
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        enterEmail();
        enterPassword();
        click(signInBtn);
    }

    private void enterEmail() {
        email.sendKeys("harrypotter12@gmail.com");
    }

    private void enterPassword() {
        password.sendKeys("hogwarts5");
    }


}
