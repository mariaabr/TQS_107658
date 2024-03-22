package tqs.cars.model;

import java.util.Objects;

public class CarDTO {
    private Long carId;
    private String maker;
    private String model;

    public static CarDTO fromCarEntity(Car car) {
        return new CarDTO(car.getCarId(), car.getMaker(), car.getModel());
    }

    public Car toCarEntity() {
        return new Car( getCarId(), getMaker(), getModel());
    }

    public CarDTO() {
    }

    public CarDTO(Long carId, String maker, String model) {
        this.carId = carId;
        this.maker = maker;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId.equals(car.getCarId()) && Objects.equals(maker, car.getMaker()) && Objects.equals(model, car.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

