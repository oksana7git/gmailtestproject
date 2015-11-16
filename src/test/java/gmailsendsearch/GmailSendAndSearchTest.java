package gmailsendsearch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static gmailsendsearch.MailLogin.getLogin;
import static gmailsendsearch.MailLogin.getPassword;
import static gmailsendsearch.Tools.*;
import static java.lang.System.currentTimeMillis;

public class GmailSendAndSearchTest {

    public static WebDriver driver = new FirefoxDriver();
    By mails = by("[role=\"main\"] .zA .y6");

    @BeforeClass
    public static void openPage() {
        Tools.driver = driver;
        driver.get("http://gmail.com");
        driver.manage().window().maximize();
    }

    @Test
    public void testSendAndSearchMail() {
        String subject = Long.toString(currentTimeMillis());
        signIn(getLogin, getPassword);
        clickButton("COMPOSE");
        fillInField("to", "oksana.kondratovych@gmail.com");
        fillInField("subjectbox", subject);
        clickButton("Send");
        fillInFieldAndPressEnter("q", subject);
        hold().until(ExpectedConditions.presenceOfAllElementsLocatedBy(mails));
        assertFoundMailsAre(mails, subject);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
