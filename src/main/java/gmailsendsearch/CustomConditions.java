package gmailsendsearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oksana on 11/19/15.
 */
public class CustomConditions {

    public static ExpectedCondition<Boolean> sizeOf(final By results, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            private int listSize;

            public Boolean apply(WebDriver driver) {
                listSize = driver.findElements(results).size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n to be: %s\n while actual size is: %s\n", results, expectedSize, listSize);
            }
        };
    }

    public static ExpectedCondition<Boolean> exactTexts(final By locatorsList, final String... expectedTexts) {
        if (expectedTexts.length == 0) {
            throw new IllegalArgumentException("Array of expected texts is empty");
        }

        return new ExpectedCondition<Boolean>() {

            private List<String> foundTexts = new ArrayList<String>();

            public Boolean apply(WebDriver driver) {
                List<WebElement> actualElements = driver.findElements(locatorsList);

                for (WebElement mail : actualElements) {
                    foundTexts.add(mail.getText());
                }

                if (expectedTexts.length != foundTexts.size()) {
                    return false;
                }

                for (int j = 0; j < foundTexts.size(); j++) {
                    if (!expectedTexts[j].equals(foundTexts.get(j))) {
                        return false;
                    }
                }
                return true;
            }

            public String toString() {
                return String.format("\ntexts of list: %s\n to be: %s\n while actual texts are: %s\n",
                        locatorsList, Arrays.toString(expectedTexts), foundTexts);
            }
        };
    }

    public static ExpectedCondition<Boolean> listNthElementHasText(
            final By results, final int nth, final String expectedText) {
        return new ExpectedCondition<Boolean>() {
            private String actualText = "";

            public Boolean apply(WebDriver driver) {
                try {
                    actualText = driver.findElements(results).get(nth).getText();
                    return actualText.contains(expectedText);
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }

            public String toString() {
                return String.format(
                        "\ntext: %s \n to be present in %sth element\n of list: %s\n while actual text: %s",
                        expectedText, nth, results, actualText);
            }
        };
    }
}
