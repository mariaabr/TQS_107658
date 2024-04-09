// package ua.tqs.homework.cucumber;

// import static java.lang.invoke.MethodHandles.lookup;
// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.core.IsEqual.equalTo;
// import static org.slf4j.LoggerFactory.getLogger;

// import org.apache.commons.compress.harmony.pack200.NewAttributeBands.Integral;
// import org.apache.logging.log4j.message.StringFormattedMessage;
// import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;
// import org.slf4j.Logger;

// import io.cucumber.java.DataTableType;
// import io.cucumber.java.ParameterType;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;
// import ua.tqs.homework.models.BusTrip;
// import ua.tqs.homework.models.Fare;
// import ua.tqs.homework.services.BusTripService;

// import java.io.IOException;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;
// import java.util.*;

// public class BusTripSteps {
//     private List<BusTrip> trips = new ArrayList<>();
//     private List<BusTrip> searchResult = new ArrayList<>();
//     private BusTripService busTripService;
    
//     @ParameterType("([0-9]{2}).([0-9]{2}).([0-9]{4})")
//     public LocalDate formatdate(String dateString) {
//         // LocalDate date = LocalDate.of(year, month, day);

//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//         LocalDate date = LocalDate.parse(dateString, formatter);

//         System.out.println("date: " + date);
        
//         return date;
//     }
    
//     @ParameterType("([0-9]{2}):([0-9]{2})")
//     public LocalTime formathour(String hourString) {
//         String[] parts = hourString.split(":");

//         Integer hour = Integer.parseInt(parts[0]);
//         Integer minute = Integer.parseInt(parts[1]);
        
//         LocalTime time = LocalTime.of(hour, minute);
//         System.out.println("time: " + time);
        
//         return time;
//     }
    
//     @Given("a trip from {string} to {string} day {formatdate} with departure at {formathour} and arrival at {formathour} with duration {formathour} and price {double}")
//     public void addNewTrip(String depName, String arrName, LocalDate day, LocalTime depTime, LocalTime arrTime, LocalTime durationTime, Double price) {
//         List<Fare> fares = new ArrayList<>();
//         fares.add(new Fare(price));
        
//         LocalDateTime depDateTime = day.atTime(depTime);
//         LocalDateTime arrDateTime = depDateTime.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute());

//         String depOffset = depDateTime.toString();
//         System.out.println("depOffset = " + depOffset);

//         String arrOffset = arrDateTime.toString();
//         System.out.println("arrOffset = " + arrOffset);

//         String duration = durationTime.toString();
//         System.out.println("duration = " + duration);

        
//         trips.add(new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares));
//     }

//     @Given("another trip from {string} to {string} day {formatdate} with departure at {formathour} and arrival at {formathour} with duration {formathour} and price {double}")
// 	public void addAnotherTrip(String depName, String arrName, LocalDate day, LocalTime depTime, LocalTime arrTime, LocalTime durationTime, Double price) {
// 		List<Fare> fares = new ArrayList<>();
//         fares.add(new Fare(price));
        
//         LocalDateTime depDateTime = day.atTime(depTime);
//         LocalDateTime arrDateTime = depDateTime.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute());

//         String depOffset = depDateTime.toString();
//         System.out.println("depOffset = " + depOffset);

//         String arrOffset = arrDateTime.toString();
//         System.out.println("arrOffset = " + arrOffset);

//         String duration = durationTime.toString();
//         System.out.println("duration = " + duration);

        
//         trips.add(new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares));
// 	}

//     @Given("a trip from {string} to {string} day {formatdate} with departure at {formathour} and arrival at {formathour} with duration {formathour} and price {double} with currency {string}")
//     public void addNewTrip(String depName, String arrName, LocalDate day, LocalTime depTime, LocalTime arrTime, LocalTime durationTime, Double price, String currency) {
//         List<Fare> fares = new ArrayList<>();
//         fares.add(new Fare(price, currency));
        
//         LocalDateTime depDateTime = day.atTime(depTime);
//         LocalDateTime arrDateTime = depDateTime.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute());

//         String depOffset = depDateTime.toString();
//         System.out.println("depOffset = " + depOffset);

//         String arrOffset = arrDateTime.toString();
//         System.out.println("arrOffset = " + arrOffset);

//         String duration = durationTime.toString();
//         System.out.println("duration = " + duration);

        
//         trips.add(new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares));
//     }

//     @Given("another trip from {string} to {string} day {formatdate} with departure at {formathour} and arrival at {formathour} with duration {formathour} and price {double} with currency {string}")
// 	public void addAnotherTrip(String depName, String arrName, LocalDate day, LocalTime depTime, LocalTime arrTime, LocalTime durationTime, Double price, String currency) {
// 		List<Fare> fares = new ArrayList<>();
//         fares.add(new Fare(price, currency));
        
//         LocalDateTime depDateTime = day.atTime(depTime);
//         LocalDateTime arrDateTime = depDateTime.plusHours(durationTime.getHour()).plusMinutes(durationTime.getMinute());

//         String depOffset = depDateTime.toString();
//         System.out.println("depOffset = " + depOffset);

//         String arrOffset = arrDateTime.toString();
//         System.out.println("arrOffset = " + arrOffset);

//         String duration = durationTime.toString();
//         System.out.println("duration = " + duration);

        
//         trips.add(new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares));
// 	}

//     @When("the user searches from a trip between {string} and '{string} day {formatdate}")
//     public void searchTripsBetween2Cities(String from_id, String to_id, String date) throws InterruptedException, IOException {

//         List<BusTrip> searchResult = busTripService.getBusTripsBetweenCities(from_id, to_id, date, 1, null);
//     }

//     @When("the user searches from a trip between {string} and {string} day {formatdate} with currency {string}")
//     public void searchTripsBetween2CitiesCurrency(String from_id, String to_id, String date, String currency) throws InterruptedException, IOException {

//         List<BusTrip> searchResult = busTripService.getBusTripsBetweenCities(from_id, to_id, date, 1, currency);
//     }

//     @Then("{int} 1 trips should be returned")
//     public void verifyTripsFound(Integer trips) {

//         assertThat(searchResult.size(), equalTo(trips));
//     }

//     @Then("Trip {int} should be departe at {formathour} of {formatdate} day")
// 	public void verifyTripAtPosition(Integer position, LocalDate day, LocalTime depTime) {
//         LocalDateTime depDateTime = day.atTime(depTime);
//         String depOffset = depDateTime.toString();

// 		assertThat(searchResult.get(position - 1).getDepOffset(), equalTo(depOffset));
// 	}
    
//     @Then("Trip {int} should be departe at {formathour} of {formatdate} day with the currency {string}")
// 	public void verifyTripAtPositionWithCurrency(Integer position, LocalDate day, LocalTime depTime, String currency) {
//         LocalDateTime depDateTime = day.atTime(depTime);
//         String depOffset = depDateTime.toString();

// 		assertThat(searchResult.get(position - 1).getDepOffset(), equalTo(depOffset));
//         assertThat(searchResult.get(position - 1).getFares().get(0).getCurrency(), equalTo(currency));
// 	}
// }
