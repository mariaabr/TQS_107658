package tqs.cars.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.cars.model.Car;
import tqs.cars.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @DisplayName("return all cars")
    @Test
    public void allCarsTest() {
        
        Car c1 = new Car("renault", "arkana");
        Car c2 = new Car("land rover", "discovery");
        Car c3 = new Car("nissan", "qashqai");

        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(c1.getMaker(), c2.getMaker(), c3.getMaker());
    }

    @DisplayName("return a car")
    @Test
    public void returnCarTest() {
        
        Car car = new Car("seat", "ibiza");
        entityManager.persistAndFlush(car); //ensure data is persisted at this point

        assertThat(carRepository.findByCarId(car.getCarId())).isEqualTo(car);
    }

    @DisplayName("return empty if the id is invalid")
    @Test
    public void emptyInvalidIdTest() { 
         
        assertThat(carRepository.findByCarId(5L)).isNull();
    }
    
}
