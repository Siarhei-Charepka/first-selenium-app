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

import java.util.concurrent.TimeUnit;

public class LoginMailRUTest {

    private static final String MAIL_RU_URL = "https://mail.ru/";
    private static final String LOGIN = "seleniumTests10";
    private static final String PASSWORD = "060788avavav";

    private WebDriver driver;
    private WebDriverWait waiter;

    private final By USER_NAME_LOCATOR = By.cssSelector("input.email-input");
    private final By ENTER_PASSWORD_BUTTON_LOCATOR = By.cssSelector("button.button");
    private final By PASSWORD_LOCATOR = By.cssSelector(".password-input");
    private final By ENTER_BUTTON = By.cssSelector("button.second-button");
    private final By IN_MAIL_BOX_LOCATOR = By.xpath("//div[@aria-label='seleniumtests10@mail.ru']");

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        waiter = new WebDriverWait(driver, 5);
        driver.get(MAIL_RU_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loginTest() {
        WebElement loginNameInput = driver.findElement(USER_NAME_LOCATOR);
        loginNameInput.sendKeys(LOGIN);
        WebElement enterPasswordButton = driver.findElement(ENTER_PASSWORD_BUTTON_LOCATOR);
        enterPasswordButton.click();

        WebElement passwordInput = driver.findElement(PASSWORD_LOCATOR);
        passwordInput.sendKeys(PASSWORD);
        WebElement enterButton = driver.findElement(ENTER_BUTTON);
        enterButton.click();

        waiter.until(ExpectedConditions.visibilityOfElementLocated(IN_MAIL_BOX_LOCATOR));
        WebElement inMailBox = driver.findElement(IN_MAIL_BOX_LOCATOR);
        Assert.assertTrue(inMailBox.isDisplayed());
    }
}
