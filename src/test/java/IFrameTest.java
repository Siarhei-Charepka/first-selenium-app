import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameTest {

    private WebDriver driver;

    private static final String URL = "http://the-internet.herokuapp.com/iframe";
    private static final String TEXT = "I will work as QA Automation Tester this year!";
    private static final String FRAME_ID = "mce_0_ifr";
    private final By CONTENT_BODY = By.className("mce-content-body");

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
    public void iFrameTest() {
        driver.switchTo().frame(FRAME_ID);
        WebElement iframe = driver.findElement(CONTENT_BODY);
        iframe.clear();
        driver.findElement(CONTENT_BODY).sendKeys(TEXT);
        Assert.assertEquals(TEXT, iframe.getText());
    }
}
