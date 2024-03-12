import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SeleniumJupiter.class)
public class BrowserVariationsTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @Test
    public void testbuyflight(@DockerBrowser(type = BrowserType.CHROME) WebDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(775, 695));
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        driver.findElement(By.cssSelector("tr:nth-child(6) .btn")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("FN");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Chicago");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
}