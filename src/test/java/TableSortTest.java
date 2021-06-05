import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TableSortTest {

    private static final String URL = "http://the-internet.herokuapp.com/tables";

    private WebDriver driver;

    private final By LAST_NAME_HEADER_LOCATOR = By.xpath(".//span[contains(text() , 'Last Name')][1]");
    private final By LAST_NAMES_LOCATOR = By.cssSelector("#table1 tbody tr");

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void sortNamesTest() {

        List<String> lastNames = driver.findElements(LAST_NAMES_LOCATOR).stream().map(WebElement::getText)
                .sorted().collect(Collectors.toList());

        driver.findElement(LAST_NAME_HEADER_LOCATOR).click();

        List<String> sortedLastNames = driver.findElements(LAST_NAMES_LOCATOR).stream().map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(lastNames, sortedLastNames);
    }
}
