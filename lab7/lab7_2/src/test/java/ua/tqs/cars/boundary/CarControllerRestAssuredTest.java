package ua.tqs.cars.boundary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.tqs.cars.boundary.CarController;
import ua.tqs.cars.model.Car;
import ua.tqs.cars.services.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.cars.JsonUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CarController.class)
public class CarControllerRestAssuredTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @DisplayName("post a car implies create one car")
    @Test
    public void postCar_thenCreateCarTest( ) throws Exception {
        
        Car c1 = new Car("fiat", "500");
        when(service.save(Mockito.any())).thenReturn(c1);
        RestAssuredMockMvc.given()
            .contentType("application/json")
            .body(JsonUtils.toJson(c1))
            .when()
            .post("/api/cars")
            .then()
            .statusCode(201)
            .and().body("maker", equalTo(c1.getMaker()))
            .and().body("model", equalTo(c1.getModel()));
        
        verify(service, times(1)).save(Mockito.any());
    }

    @DisplayName("with many cars and a get implies return json array of all cars")
    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArrayTest() throws Exception {
        Car car1 = new Car("volkswagen", "passat");
        Car car2 = new Car("bmw", "i8");
        Car car3 = new Car("audi", "a4");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when(service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given()
            .contentType("application/json")
            .get("/api/cars")
            .then()
            .statusCode(200)
            .body("", hasSize(3))
            .and().body("[0].maker", equalTo(car1.getMaker()))
            .and().body("[0].model", equalTo(car1.getModel()))
            .and().body("[1].maker", equalTo(car2.getMaker()))
            .and().body("[1].model", equalTo(car2.getModel()))
            .and().body("[2].maker", equalTo(car3.getMaker()))
            .and().body("[2].model", equalTo(car3.getModel()));
        
        verify(service, times(1)).getAllCars();
    }

    @DisplayName("if car exists return details")
    @Test
    public void carExists_thenReturnDetailsTest() throws Exception {
        
        Car car = new Car("fiat", "punto");
        car.setCarId(1L);

        when(service.getCarDetails(car.getCarId())).thenReturn(Optional.of(car));

        RestAssuredMockMvc.given()
            .contentType("application/json")
            .get("/api/cars/" + car.getCarId())
            .then()
            .body("maker", equalTo(car.getMaker()))
            .and().body("model", equalTo(car.getModel()));
        
        verify(service, times(1)).getCarDetails(car.getCarId());
    }
}
