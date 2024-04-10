package ua.tqs.cars;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.tqs.cars.boundary.CarController;
import ua.tqs.cars.data.CarRepository;
import ua.tqs.cars.model.Car;
import ua.tqs.cars.services.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.cars.JsonUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
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

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GsCarsContainersApplication.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
@AutoConfigureMockMvc
public class IntegrationTestRestAssured {
    
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
        .withUsername("admin")
        .withPassword("admin")
        .withDatabaseName("cars");

    @Autowired
    private CarRepository repository;

    @Autowired
    private MockMvc mvc;
    
    @LocalServerPort
    int testPort;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    Car car1, car2, car3;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
        car1 = repository.save(new Car("fiat", "500"));
        car2 = repository.save(new Car("mini", "cooper"));
        car3 = new Car("land rover", "discovery");
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @DisplayName("post a car implies create one car")
    @Test
    public void postCar_thenCreateCarTest( ) throws Exception {
        
        RestAssuredMockMvc.given()
            .contentType("application/json")
            .body(JsonUtils.toJson(car1))
            .when()
            .post("/api/cars")
            .then()
            .statusCode(201)
            .and().body("maker", equalTo(car1.getMaker()))
            .and().body("model", equalTo(car1.getModel()));
        
    }

    @DisplayName("with many cars and a get implies return json array of all cars")
    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArrayTest() throws Exception {

        List<Car> allCars = Arrays.asList(car1, car2, car3);

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
        
    }

    @DisplayName("if car exists return details")
    @Test
    public void carExists_thenReturnDetailsTest() throws Exception {
        
        car1.setCarId(2L);

        RestAssuredMockMvc.given()
            .contentType("application/json")
            .get("/api/cars/" + car2.getCarId())
            .then()
            .body("maker", equalTo(car2.getMaker()))
            .and().body("model", equalTo(car2.getModel()));
        
    }
}
