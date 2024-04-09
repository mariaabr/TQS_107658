package ua.tqs.homework.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.hamcrest.core.IsEqual.equalTo;

import org.openqa.selenium.support.ui.Select;

public class BusTripFrontendSteps {
    
    private WebDriver driver;

    @ParameterType("([0-9]{2}).([0-9]{2}).([0-9]{4})")
    public LocalDate formatdate(String dateString) {
        // LocalDate date = LocalDate.of(year, month, day);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        System.out.println("date: " + date);
        
        return date;
    }

    @Given("a page of the BusTripFrontend")
    public void openWeb() {
        driver = new ChromeDriver();

        driver.get("https://localhost:9091");
    }

    @When("I search for a bus trip from {string} to {string} at {formatdate} and click the button")
    public void searchBusTrip(String from, String to, LocalDate formatdate) {

        driver.findElement(By.id("from-input")).sendKeys(from);
        driver.findElement(By.id("to-input")).sendKeys(to);

        driver.findElement(By.cssSelector(".form-control:nth-child(3)")).sendKeys(formatdate.toString());

        Select passengers = new Select(driver.findElement(By.name(".form-select")));
        passengers.selectByVisibleText("1");

        driver.findElement(By.id("search-trip-btn")).click();
    }

    @Then("I choose the third bus trip and click {string}")
    public void chooseBusTrip(String buttonString) {
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click(); // mal
    }

    @Then("fill the name input with {string}")
    public void fillName(String name) {

        driver.findElement(By.cssSelector(".form-control:nth-child(2)")).sendKeys(name);
    }

    @Then("fill the age input with {string}")
    public void fillAge(String age) {

        driver.findElement(By.cssSelector(".form-control:nth-child(4)")).sendKeys(age);
    }

    @Then("fill the address input with {string}")
    public void fillAddress(String address) {

        driver.findElement(By.cssSelector(".form-control:nth-child(6)")).sendKeys(address);
    }

    @Then("fill the location input with {string}")
    public void fillLocation(String location) {

        driver.findElement(By.cssSelector(".form-control:nth-child(8)")).sendKeys(location);
    }

    @Then("fill the city input with {string}")
    public void fillCity(String city) {

        driver.findElement(By.cssSelector(".form-control:nth-child(10)")).sendKeys(city);
    }

    @Then("fill the zipcode input with {string}")
    public void fillZipcode(String zipcode) {

        driver.findElement(By.cssSelector(".form-control:nth-child(12)")).sendKeys(zipcode);
    }

    @Then("fill the credit card input with {string}")
    public void fillCreditCard(String creditCard) {

        driver.findElement(By.cssSelector(".form-control:nth-child(19)")).sendKeys(creditCard);
    }

    @Then("fill the credit card month input with {string}")
    public void fillCreditCardMonth(String creditCardMonth) {

        driver.findElement(By.cssSelector(".col-sm-5:nth-child(2) > .form-control")).sendKeys(creditCardMonth);
    }

    @Then("fill the credit card year input with {string}")
    public void fillCreditCardYear(String creditCardYear) {

        driver.findElement(By.cssSelector(".col-sm-5:nth-child(3) > .form-control")).sendKeys(creditCardYear);
    }

    @Then("fill the name on card input with {string}")
    public void fillCreditCardName(String creditCardName) {

        driver.findElement(By.cssSelector(".form-control:nth-child(22)")).sendKeys(creditCardName);
    }

    @Then("click the {string} button")
    public void searchButton() {
        driver.findElement(By.id("search-trip-btn")).click();
    }
}
