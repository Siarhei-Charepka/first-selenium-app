import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTest {
    private static final String URL = "http://the-internet.herokuapp.com/dropdown";
    private static final String EXPECTED_OPTION = "Option 1";
    private static final String VALUE = "1";

    private WebDriver driver;

    private final By DROPDOWN = By.xpath(".//select [@id = 'dropdown']");

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
    public void dropDownTest() {

        WebElement dropdownElement = driver.findElement(DROPDOWN);
        Select dropdown = new Select(dropdownElement);

        dropdown.selectByValue(VALUE);
        String selectedOption = dropdown.getFirstSelectedOption().getText();

        Assert.assertEquals(selectedOption, EXPECTED_OPTION);
    }
}
