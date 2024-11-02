package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseTest {

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    //protected WebDriver driver;
    SoftAssert softAssert;


    @BeforeMethod(alwaysRun = true)
    //@Parameters("browser")
    public void setup(){
        System.out.println("Setting up driver for thread: " + Thread.currentThread().getId());
        driverThreadLocal.set(DriverManager.getInstance().setDriver());
        softAssert = new SoftAssert();
        //driver.get("https://practicesoftwaretesting.com/#/");
        driverThreadLocal.get().manage().window().maximize();
        driverThreadLocal.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        new Environment(driverThreadLocal.get()).openBrowser();
        System.out.println("Driver setup complete for thread: " + Thread.currentThread().getId());
    }
    public WebDriver getDriverThreadLocal() {
        if (driverThreadLocal.get() == null) {
            throw new IllegalStateException("Driver instance not set for this thread");
        }
        return driverThreadLocal.get();
    }
    @AfterMethod(alwaysRun = true)
    public synchronized void tearDown(){
        System.out.println("Tearing down driver for thread: " + Thread.currentThread().getId());
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }



}
