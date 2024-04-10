package ua.tqs.cars.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.cars.data.CarRepository;
import ua.tqs.cars.services.CarManagerService;
import ua.tqs.cars.model.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class CarService {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    Car c1 = new Car("renault", "clio");
    Car c2 = new Car("land rover", "defender");
    Car c3 = new Car("opel", "corsa");

    @BeforeEach
    public void setUp() {

        List<Car> allCars = Arrays.asList(c1, c2, c3);

        Mockito.when(carRepository.save(c1)).thenReturn(c1);
        Mockito.when(carRepository.findById(c1.getCarId())).thenReturn(Optional.of(c1));
        Mockito.when(carRepository.findById(c2.getCarId())).thenReturn(Optional.of(c2));
        Mockito.when(carRepository.findById(c3.getCarId())).thenReturn(Optional.of(c3));
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findById(25L)).thenReturn(Optional.empty());
    }

    @DisplayName("get all cars")
    @Test
     void getAllCarsTest() {
        List<Car> allCars = carService.getAllCars();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();

        assertAll(
            () -> assertThat(allCars.size()).isEqualTo(3),
            () -> assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(c1.getMaker(), c2.getMaker(), c3.getMaker()),
            () -> assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(c1.getModel(), c2.getModel(), c3.getModel()),
            () -> assertThat(allCars.get(0).getMaker()).isEqualTo("renault"),
            () -> assertThat(allCars.get(1).getMaker()).isEqualTo("land rover"),
            () -> assertThat(allCars.get(2).getMaker()).isEqualTo("opel"),
            () -> assertThat(allCars.get(0).getModel()).isEqualTo("clio"),
            () -> assertThat(allCars.get(1).getModel()).isEqualTo("defender"),
            () -> assertThat(allCars.get(2).getModel()).isEqualTo("corsa")
        );
    }

    @DisplayName("return valid car")
    @Test
     void returnValidCarTest() {

        assertThat(carService.save(c1).getModel()).isEqualTo("clio");
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).save(c1);
    }


    @DisplayName("verify invalid id")
    @Test
     void invalidIdTest() {

        Optional <Car> invalidCar = carService.getCarDetails(5L);
        assertThat(invalidCar).isEmpty();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(5L);
    }
}
