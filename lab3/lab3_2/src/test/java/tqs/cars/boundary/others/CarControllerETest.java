package tqs.cars.boundary.others;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
// import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CarControllerETest {
    
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @DisplayName("create a car")
    @Test
    public void createCarTest() {
        
        Car c1 = new Car("ford", "c max");
        Car c2 = new Car("fiat", "500");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", c1, Car.class);
        entity = restTemplate.postForEntity("/api/cars", c2, Car.class);

        assertThat(carRepository.findAll()).extracting(Car::getMaker).containsOnly("ford", "fiat");
    }

    @DisplayName("get all cars")
    @Test
    public void allCarsTest() {
        
        Car c1 = new Car("audi", "a5");
        carRepository.saveAndFlush(c1);
        Car c2 = new Car("mini", "cooper");
        carRepository.saveAndFlush(c2);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("audi", "mini");
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("a5", "cooper");
    }

    @DisplayName("valid car id")
    @Test
    public void validIdCarTest() {
        
        Car c1 = new Car("mercedes", "c 220");
        carRepository.saveAndFlush(c1);
        ResponseEntity<Car> entity = restTemplate.exchange("/api/cars/"+ c1.getCarId(), HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {});

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).extracting(Car::getMaker).isEqualTo("mercedes");
    }

    // @DisplayName("invalid car id")
    // @Test
    // public void invalidIdCarTest() {
        
    //     ResponseEntity<Car> entity = restTemplate.exchange("/api/cars/578", HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {});

    //     assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    //     // assertThat(entity.getBody()).isNull();
    // }
}
