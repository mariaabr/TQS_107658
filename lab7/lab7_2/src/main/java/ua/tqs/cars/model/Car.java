package ua.tqs.cars.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import ua.tqs.cars.dto.CarDto;

import java.util.Objects;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String maker;
    private String model;

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    // public Car(Long carId, String maker, String model) {
    //     this.carId = carId;
    //     this.maker = maker;
    //     this.model = model;
    // }

    public Car(CarDto carDto) {
        this(carDto.getMaker(), carDto.getModel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId.equals(car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }

    // public Long getCarId() {
    //     return carId;
    // }

    // public void setCarId(Long carId) {
    //     this.carId = carId;
    // }

    // public String getMaker() {
    //     return maker;
    // }

    // public void setMaker(String maker) {
    //     this.maker = maker;
    // }

    // public String getModel() {
    //     return model;
    // }

    // public void setModel(String model) {
    //     this.model = model;
    // }
}
