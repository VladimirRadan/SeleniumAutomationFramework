package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.Utils;

public class RegisterPage extends BasePage {


    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By dobField = By.cssSelector("input[formcontrolname='dob']");
    private By addressField = By.id("address");
    private By postCodeField = By.id("postcode");
    private By cityField = By.id("city");
    private By stateField = By.id("state");
    private By countryDropdown = By.id("country");
    private By phoneCodeField = By.id("phone");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By registerButton = By.cssSelector(".form-group.mb-3+button");

    private By signInLink = By.cssSelector("a[data-test='nav-sign-in']");
    private By goToRegisterFormLink = By.cssSelector("a[data-test='register-link']");

    private By myAccountPageTitle = By.cssSelector("h1[data-test='page-title']");
    private By myAccountMenuProfile = By.cssSelector("a[data-test='nav-profile']");

    private String username;
    private String password;


    public RegisterPage goToRegisterPage() {
        clickOnElement(signInLink);
        clickOnElement(goToRegisterFormLink);
        return this;
    }


    public RegisterPage registerUser() {
        username = faker.internet().emailAddress();
        password = faker.internet().password();
        typeIn(firstNameField, faker.name().firstName());
        typeIn(lastNameField, faker.name().lastName());
//        getElement(dobField).sendKeys(Keys.TAB);
//        typeIn(dobField, "2000");
        typeIn(dobField, dobInput());
        typeIn(addressField, faker.address().fullAddress());
        typeIn(postCodeField, faker.address().zipCode());
        typeIn(cityField, faker.address().city());
        typeIn(stateField, faker.address().state());
        selectCountry();
        typeIn(phoneCodeField, faker.number().digits(8));
        typeIn(emailField, username);
        typeIn(passwordField, password);
        clickOnElement(registerButton);
        Utils.waitForSeconds(2);
        return this;
    }

    public RegisterPage registerNewUser() {

        return this;
    }

    private void selectCountry() {
        Select select = new Select(getElement(countryDropdown));
        select.selectByValue("US");
    }

    private String dobInput() {
        if (driver instanceof ChromeDriver) {
            return "1212" + Keys.TAB + "1929";
        } else if (driver instanceof FirefoxDriver) {
            return "12/12/1999";
        } else if (driver instanceof EdgeDriver) {
            return "12/12/1999";
        }
        return null;
    }

    public boolean isUserRegisteredAndLoggedIn(){
        return matchesExpectedText(myAccountPageTitle, "My account")
                && matchesExpectedText(myAccountMenuProfile,"Profile");
    }

    public String actualText(){
        return getElement(myAccountPageTitle).getText();
    }

    public void isUserRegisteredAndLoggedIn2(){
        Assert.assertTrue(matchesExpectedText(myAccountPageTitle, "My account"));
        Assert.assertTrue(matchesExpectedText(myAccountMenuProfile,"Profile"));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
