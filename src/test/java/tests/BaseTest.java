package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseTest {

    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    //protected WebDriver driver;
    SoftAssert softAssert;


    @BeforeMethod(alwaysRun = true)
    //@Parameters("browser")
    public void setup(){
        driverThreadLocal.set(DriverManager.getInstance().setDriver());
        softAssert = new SoftAssert();
        //driver.get("https://practicesoftwaretesting.com/#/");
        driverThreadLocal.get().manage().window().maximize();
        driverThreadLocal.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        new Environment(driverThreadLocal.get()).openBrowser();
    }
    public WebDriver getDriverThreadLocal() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }



}
