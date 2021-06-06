import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

public class HoverTest {

    private WebDriver driver;

    private static final String URL = "http://the-internet.herokuapp.com/hovers";
    private static final By AVATAR = By.cssSelector("div .figure");
    private static final By PROFILE_LINK = By.cssSelector("[href *='users']");
    private static final By PROFILE = By.cssSelector("body");
    private static final String PROFILE_TEXT = "Not Found";

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
    public void profileTextTest() {

        Actions action = new Actions(driver);

        WebElement avatar = driver.findElement(AVATAR);
        WebElement profileLink = driver.findElement(PROFILE_LINK);

        action.moveToElement(avatar).perform();
        profileLink.click();

        WebElement profile = driver.findElement(PROFILE);
        String actualText = profile.getText();

        Assert.assertEquals(actualText, PROFILE_TEXT);
    }

    @Test
    public void profileLinksTest() {

        Actions action = new Actions(driver);

        List<WebElement> avatars = driver.findElements(AVATAR);
        List<WebElement> profileLinks = driver.findElements(PROFILE_LINK);

        IntStream.range(0, 3).forEach(i -> {
            action.moveToElement(avatars.get(i)).perform();
            Assert.assertTrue(profileLinks.get(i).isDisplayed());
        });
    }
}
