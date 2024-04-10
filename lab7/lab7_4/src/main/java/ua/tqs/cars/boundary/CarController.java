package ua.tqs.cars.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.tqs.cars.dto.CarDto;
import ua.tqs.cars.model.Car;
import ua.tqs.cars.services.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private final CarManagerService carManagerService;

    public CarController(CarManagerService injectedCarManagerService) {
        this.carManagerService = injectedCarManagerService;
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> createCar(@RequestBody CarDto carDto) {
        Car saved = carManagerService.save(new Car(carDto));
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId){
        return ResponseEntity.of(carManagerService.getCarDetails(carId));
    }

}
