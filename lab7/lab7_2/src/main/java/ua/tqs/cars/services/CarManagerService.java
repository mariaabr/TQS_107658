package ua.tqs.cars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.tqs.cars.data.CarRepository;
import ua.tqs.cars.model.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    // public CarManagerService(CarRepository carRepository) {
    //     this.carRepository = carRepository;
    // }

    public Car save(Car oneCar) {
        return carRepository.save(oneCar);
    }

    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    // public Optional<Car> getCarDetails(Long carId) {

    //     Car car = carRepository.findByCarId(carId);

    //     if (car == null)
    //         return Optional.empty();

    //     return Optional.of(car);
    // }

    public Optional<Car> getCarDetails(Long carId) {
        Car car = carRepository.findByCarId(carId);
        return car == null ? Optional.empty() : Optional.of(carRepository.findByCarId(carId));
    }
}
