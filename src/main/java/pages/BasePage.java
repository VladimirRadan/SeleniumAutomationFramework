package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.Utils;

public class BasePage {


    protected WebDriver driver;
    private WebDriverWait wait;
    Faker faker;
    private static final Logger log = LogManager.getLogger(BasePage.class.getName());
    private long waitTime =Long.parseLong(Utils.dotEnv().get("EXPLICIT_WAIT_TIME"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        faker = new Faker(Locale.ENGLISH);
    }


    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void typeIn(By locator, String text) {
        WebElement element = getElement(locator);
        //element.clear();
        element.sendKeys(text);
    }

    protected void clickOnRandomElement(By locator) {
        List<WebElement> list = driver.findElements(locator);
        Random random = new Random();
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();
    }

    protected void hover(By locator, long wait) {
        WebElement element = getElement(locator);
        new Actions(driver)
                .moveToElement(element)
                .pause(wait)
                .build()
                .perform();
    }

    protected void hoverAndClick(By locator, long wait) {
        WebElement element = getElement(locator);
        new Actions(driver)
                .moveToElement(element)
                .pause(wait)
                .click()
                .build()
                .perform();
    }

    protected void clickOnElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
       try {
           wait.until(ExpectedConditions.elementToBeClickable(locator));
           hoverAndClick(locator, 0);
       }catch (ElementClickInterceptedException ec){
           log.warn("ElementClickInterceptedException happened!");
           wait.until(ExpectedConditions.presenceOfElementLocated(locator));
           hoverAndClick(locator, 0);
       }catch (StaleElementReferenceException stale){
           log.warn("StaleElementReferenceException happened!");
           //driver.findElement(locator).click();
           getElement(locator).click();
       }catch (TimeoutException te){
           te.printStackTrace();
           log.warn("TimeoutException happened");
           WebElement element = getElement(locator);
           js.executeScript("arguments[0].click()", element);
       }catch (Exception e){
           e.printStackTrace();
           log.error("Unable to click on element!");
       }
    }

    protected boolean matchesExpectedText(By locator, String expectedText){
        WebElement element = getElement(locator);
        if (element.getText().trim().equals(expectedText)){
            log.info("PASSED - Text found in element: " + element.getText() + " matches expected text: " + expectedText);
            return true;
        }else {
            log.error("FAILED - Text found in element: " + element.getText() + " is not matched expected text: " + expectedText);
        }
        return false;
    }


}
