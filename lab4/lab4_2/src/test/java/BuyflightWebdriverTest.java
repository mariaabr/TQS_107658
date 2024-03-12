// package ua.tqs;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class BuyflightWebdriverTest {
    
    @Test
    public void test(FirefoxDriver driver) {
        // 1 | open | / |
        driver.get("https://blazedemo.com/");
        // 2 | setWindowSize | 550x691 |
        driver.manage().window().setSize(new Dimension(550, 691));
        // 3 | click | name=fromPort |
        driver.findElement(By.name("fromPort")).click();
        // 4 | select | name=fromPort | label=Boston
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
        }
        // 5 | click | css=.form-inline:nth-child(1) > option:nth-child(3) |
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(3)")).click();
        // 6 | click | name=toPort |
        driver.findElement(By.name("toPort")).click();
        // 7 | select | name=toPort | label=New York
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
        }
        // 8 | click | css=.form-inline:nth-child(4) > option:nth-child(5) |
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(5)")).click();
        // 9 | click | css=.container:nth-child(6) |
        driver.findElement(By.cssSelector(".container:nth-child(6)")).click();
        // 10 | click | css=.btn-primary |
        driver.findElement(By.cssSelector(".btn-primary")).click();
        // 11 | click | css=tr:nth-child(3) .btn |
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
        // 12 | click | id=inputName |
        driver.findElement(By.id("inputName")).click();
        // 13 | type | id=inputName | FN
        driver.findElement(By.id("inputName")).sendKeys("FN");
        // 14 | click | id=address |
        driver.findElement(By.id("address")).click();
        // 15 | type | id=address | 123 Street NY
        driver.findElement(By.id("address")).sendKeys("123 Street NY");
        // 16 | click | id=city |
        driver.findElement(By.id("city")).click();
        // 17 | type | id=city | Chicago
        driver.findElement(By.id("city")).sendKeys("Chicago");
        // 18 | click | id=state |
        driver.findElement(By.id("state")).click();
        // 19 | type | id=state | Chicago
        driver.findElement(By.id("state")).sendKeys("Chicago");
        // 20 | click | id=zipCode |
        driver.findElement(By.id("zipCode")).click();
        // 21 | type | id=zipCode | 12345
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        // 22 | click | id=creditCardNumber |
        driver.findElement(By.id("creditCardNumber")).click();
        // 23 | type | id=creditCardNumber | 2345
        driver.findElement(By.id("creditCardNumber")).sendKeys("2345");
        // 24 | click | id=creditCardMonth |
        driver.findElement(By.id("creditCardMonth")).click();
        // 25 | click | id=creditCardYear |
        driver.findElement(By.id("creditCardYear")).click();
        // 26 | click | id=nameOnCard |
        driver.findElement(By.id("nameOnCard")).click();
        // 27 | click | id=creditCardYear |
        driver.findElement(By.id("creditCardYear")).click();
        // 28 | click | id=creditCardYear |
        driver.findElement(By.id("creditCardYear")).click();
        // 29 | type | id=creditCardYear | 2027
        driver.findElement(By.id("creditCardYear")).sendKeys("2027");
        // 30 | click | id=creditCardMonth |
        driver.findElement(By.id("creditCardMonth")).click();
        // 31 | type | id=creditCardMonth | 08
        driver.findElement(By.id("creditCardMonth")).sendKeys("08");
        // 32 | click | id=nameOnCard |
        driver.findElement(By.id("nameOnCard")).click();
        // 33 | type | id=nameOnCard | FN
        driver.findElement(By.id("nameOnCard")).sendKeys("FN");
        // 34 | click | id=rememberMe |
        driver.findElement(By.id("rememberMe")).click();
        // 35 | click | css=.btn-primary |
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // assert title
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}