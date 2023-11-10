package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By emailField = By.cssSelector("input[data-test='email']");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("input[data-test='login-submit']");

    private By signInLink = By.cssSelector("a[data-test='nav-sign-in']");

    private By errorMessage = By.cssSelector(".alert");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginForm(){
        clickOnElement(signInLink);
        return this;
    }

    public LoginPage loginUser(String username, String password){
        typeIn(emailField, username);
        typeIn(passwordField, password);
        clickOnElement(loginButton);
        return this;
    }

    public boolean isErrorMessagePresent(){
        return getElement(errorMessage).isDisplayed();
    }






}
