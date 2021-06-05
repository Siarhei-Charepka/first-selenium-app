import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicLoadingTest {

    private WebDriver driver;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_loading";
    private static final By EXAMPLE_1 = By.xpath(".//a[@href = '/dynamic_loading/1']");
    private final By START_BUTTON = By.cssSelector("#start button");
    private final By MESSAGE = By.id("finish");

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
    public void dynamicLoadingTest() {
        driver.findElement(EXAMPLE_1).click();
        driver.findElement(START_BUTTON).click();

        WebElement message = driver.findElement(MESSAGE);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(message));
        String messageText = message.getText();

        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(messageText, "Hello World!");
    }
}
