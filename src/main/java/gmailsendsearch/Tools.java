package gmailsendsearch;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tools {
    public static WebDriver driver;
    public static long timeout = 6;

    public static WebDriverWait hold() {
        return new WebDriverWait(driver, timeout);
    }

    public static By by(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static By byText(String value) {
        return By.xpath(".//div[contains(text(), '" + value + "')]");
    }

    public static void signIn(String login, String password) {
        fillInFieldAndPressEnter("Email", login);
        fillInFieldAndPressEnter("Passwd", password);
    }

    public static boolean assertFoundMailsAre(By items, String subject) {
        String result = "";
        for (WebElement mail : driver.findElements(items)) {
            result += mail.getText();
        }
        if (result == subject) return true;
        else return false;
    }

    public static void clickButton(String name) {
        hold().until(ExpectedConditions.elementToBeClickable(byText(name)));
        driver.findElement(byText(name)).click();
    }

    public static void fillInField(String name, String value) {
        hold().until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
        driver.findElement(By.name(name)).sendKeys(value);
    }

    public static void fillInFieldAndPressEnter(String name, String value) {
        fillInField(name, value);
        driver.findElement(By.name(name)).sendKeys(Keys.ENTER);
    }
}
