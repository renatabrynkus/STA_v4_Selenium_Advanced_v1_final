package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-md-6 > .form-control")
    private WebElement email;

    @FindBy(css = ".js-visible-password")
    private WebElement password;

    @FindBy(css = ".submit-login")
    private WebElement signInBtn;

    public void logIn() {
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
