package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected WebDriver driver;
    SoftAssert softAssert;


    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser){
        driver = DriverManager.getInstance().setDriver(browser);
        softAssert = new SoftAssert();
        //driver.get("https://practicesoftwaretesting.com/#/");
        new Environment(driver).openBrowser();
    }
    public WebDriver getDriver() {
        return driver;
    }



}
