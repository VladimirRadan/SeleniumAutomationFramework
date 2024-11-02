package core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Utils;

import java.time.Duration;

import static utils.Utils.dotEnv;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverManager {

        private static final String browser = dotEnv().get("BROWSER");

        @Getter
        private static final DriverManager instance = new DriverManager();

    public WebDriver setDriver(){

                if (browser.equalsIgnoreCase("chrome")){
                    return new ChromeDriver(OptionsManager.getChromeOptions());
                }else if (browser.equalsIgnoreCase("firefox")){
                    return new FirefoxDriver(OptionsManager.getFirefoxOptions());
                } else if (browser.equalsIgnoreCase("edge")) {
                    return new EdgeDriver();
                }
             return null;
        }

//    public WebDriver setDriver(String browser){
//        if (browser.equalsIgnoreCase("chrome")){
//            driver = new ChromeDriver(OptionsManager.getChromeOptions());
//        }else if (browser.equalsIgnoreCase("firefox")){
//            driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
//        } else if (browser.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        }
//        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
//        return driver;
//    }


}
