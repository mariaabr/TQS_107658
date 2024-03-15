package ua.tqs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.hamcrest.core.IsEqual.equalTo;

import org.openqa.selenium.support.ui.Select;

public class BlazeDemoSteps {

    private final WebDriver driver = new ChromeDriver();

    @Given("a page of the BlazeDemo")
    public void openWeb() {
        driver.get("https://www.blazedemo.com");
    }

    @When("I search for a flight from 'Boston' to 'New York'")
    public void searchFlight(String from, String to) {

        Select departure = new Select(driver.findElement(By.name("fromPort")));
        departure.selectByVisibleText(from);

        Select destination = new Select(driver.findElement(By.name("toPort")));
        destination.selectByVisibleText(to);

        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }

    // @Then("{int} flights should have been found")
    // public void verifyAmountOfFlightsFound(final int flightsFound) {
    //     assertThat(result.size(), equalTo(flightsFound));
    // }

    @Then("I choose the third flight and click {string}")
    public void chooseFlight(String buttonString) {
        driver.findElement(By.xpath("//input[@value='" + buttonString + "']")).click();
    }

    @Then("fill the name input with {string}")
    public void fillName(String name) {
        driver.findElement(By.id("inputName")).sendKeys(name);
    }

    @Then("fill the address input with {string}")
    public void fillAddress(String address) {
        driver.findElement(By.id("address")).sendKeys(address);
    }

    @Then("fill the city input with {string}")
    public void fillCity(String city) {
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @Then("fill the state input with {string}")
    public void fillState(String state) {
        driver.findElement(By.id("state")).sendKeys(state);
    }

    @Then("fill the zipcode input with {string}")
    public void fillZipCode(String zipCode) {
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
    }

    @Then("fill the select the card type {string}")
    public void selectCard(String card) {
        Select cardObject = new Select(driver.findElement(By.name("cardType")));
        cardObject.selectByVisibleText(card);
    }

    @Then("fill the credit card number input with {int}")
    public void cardNumber(int cardNumber) {
        String cardNumberS = Integer.toString(cardNumber);
        driver.findElement(By.id("creditCardNumber")).sendKeys(cardNumberS);
    }

    @Then("cfill the month input with {int}")
    public void cardMonth(int month) {
        String monthString = Integer.toString(month);
        driver.findElement(By.id("creditCardMonth")).sendKeys(monthString);
    }

    @Then("fill the year input with {int}")
    public void cardYear(int year) {
        String yearString = Integer.toString(year);
        driver.findElement(By.id("creditCardYear")).sendKeys(yearString);
    }

    @Then("fill the the name on card input with {string}")
    public void cardName(String name) {
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
    }

    @Then("select the remember me option")
    public void rememberMe() {
        driver.findElement(By.id("rememberMe")).click();
    }

    @Then("click the 'Purchase Flight' button")
    public void clickButton() {
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }

    @Then("receive the confirmation")
    public void confirmation() {
        assertTrue(driver.getTitle().contains("Confirmation"));
        driver.quit();
    }
}
