package gmailsendsearch;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;

import static gmailsendsearch.CustomConditions.*;
import static gmailsendsearch.MailLogin.getLogin;
import static gmailsendsearch.MailLogin.getPassword;
import static gmailsendsearch.Tools.*;
import static java.lang.System.currentTimeMillis;

public class GmailSendAndSearchTest {

    @BeforeClass
    public static void openPage() {
        setWebDriver(new FirefoxDriver());
        open("http://gmail.com");
    }

    @Test
    public void testSendAndSearchMail() {
        String subject = Long.toString(currentTimeMillis());

        signIn(getLogin, getPassword);

        find(byText("COMPOSE")).click();
        find(By.name("to")).sendKeys("oksana.kondratovych@gmail.com");
        find(By.name("subjectbox")).sendKeys(subject);
        find(byText("Send")).click();
        find(By.cssSelector("[href$='#inbox']")).click();
        hold().until(listNthElementHasText(mails, 0, subject));

        find(By.name("q")).sendKeys(subject + Keys.ENTER);
        hold().until(sizeOf(mails, 1));
        hold().until(exactTexts(mails, subject));
    }

    @AfterClass
    public static void tearDown() {
        getWebDriver().quit();
    }

    By mails = by("[role=\"main\"] .zA .y6");

    public static void signIn(String login, String password) {
        find(By.name("Email")).sendKeys(login + Keys.ENTER);
        find(By.name("Passwd")).sendKeys(password + Keys.ENTER);
    }
}
