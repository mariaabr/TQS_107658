import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class PageObjectPatternTest {
    private WebDriver driver;

    private static String PAGE_URL = "https://blazedemo.com/";

    @FindBy(name = "fromPort")
    private WebElement fromPort;

    @FindBy(xpath = "//option[. = 'Boston']")
    private WebElement bostonOption;

    @FindBy(css = ".form-inline:nth-child(1) > option:nth-child(3)")
    private WebElement button1;

    @FindBy(name = "toPort")
    private WebElement toPort;

    @FindBy(xpath = "//option[. = 'New York']")
    private WebElement newYorkOption;

    @FindBy(css = ".form-inline:nth-child(4) > option:nth-child(5)")
    private WebElement button2;

    @FindBy(css = "tr:nth-child(6) .btn")
    private WebElement button3;

    @FindBy(css = ".btn-primary")
    private WebElement button4;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement button5;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(id = "rememberMe")
    private WebElement rememberMe;

    @FindBy(css = ".btn-primary")
    private WebElement button6;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        // vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void reset() {
        driver.quit();
    }

    @Test
    public void everythingTest() {
        driver.get(PAGE_URL);
        driver.manage().window().setSize(new Dimension(1600, 900));
        PageFactory.initElements(driver, this);

        fromPort.click();
        bostonOption.click();
        button1.click();

        toPort.click();
        newYorkOption.click();
        button2.click();

        button3.click();
        button4.click();
        button5.click();

        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Purchase");

        nameInput.sendKeys("FN");
        addressInput.sendKeys("123 Street NY");
        cityInput.sendKeys("Chicago");
        stateInput.sendKeys("Chicago");
        zipCodeInput.sendKeys("12345");

        creditCardNumber.sendKeys("2345");
        creditCardYear.sendKeys("2027");
        creditCardMonth.sendKeys("08");
        
        nameOnCard.sendKeys("FN");
        
        rememberMe.click();
        button5.click();

        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}
