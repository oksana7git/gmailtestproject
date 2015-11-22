package gmailsendsearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tools {

    private static WebDriver driver;
    public static long timeout = 7;

    public static void setWebDriver(WebDriver driver) {
        Tools.driver = driver;
        driver.manage().window().maximize();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void open(String link) {
        driver.get(link);
    }

    public static WebDriverWait hold() {
        return new WebDriverWait(driver, timeout);
    }

    public static By by(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String value) {
        return By.xpath(".//div[contains(text(), '" + value + "')]");
    }

    public static WebElement find(By locator) {
        return hold().until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
