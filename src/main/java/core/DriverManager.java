package core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Utils;

import java.time.Duration;

import static utils.Utils.dotEnv;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverManager {

        private static String browser = dotEnv().get("BROWSER");

        public static WebDriver setDriver(){
            WebDriver driver = null;
             if (browser.equalsIgnoreCase("chrome")){
                 driver = new ChromeDriver();
             }else if (browser.equalsIgnoreCase("firefox")){
                 driver = new FirefoxDriver();
             } else if (browser.equalsIgnoreCase("edge")) {
                 driver = new EdgeDriver();
             }
             driver.manage().window().maximize();
             driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
             return driver;
        }


}
