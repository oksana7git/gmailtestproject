package gmailsendsearch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static gmailsendsearch.MailLogin.getLogin;
import static gmailsendsearch.MailLogin.getPassword;
import static gmailsendsearch.Tools.*;
import static java.lang.System.currentTimeMillis;

public class GmailSendAndSearchTest {

    public static WebDriver driver = new FirefoxDriver();

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

        searchForMails(subject);
        assertFoundMailsAre(subject);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
