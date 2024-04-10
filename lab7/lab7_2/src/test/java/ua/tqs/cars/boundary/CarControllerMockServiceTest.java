package ua.tqs.cars.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.cars.data.CarRepository;
import ua.tqs.cars.model.Car;
import ua.tqs.cars.services.CarManagerService;
import ua.tqs.cars.boundary.CarController;
import ua.tqs.cars.JsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarController.class)
public class CarControllerMockServiceTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @DisplayName("post a car implies create one car")
    @Test
    public void postCar_thenCreateCarTest( ) throws Exception {
        Car car = new Car("volkswagen", "passat");

        when(service.save(Mockito.any())).thenReturn(car);
        mvc.perform(
                        post("/api/cars")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is(car.getMaker())))
                .andExpect(jsonPath("$.model", is(car.getModel())));
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
        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
                .andExpect(jsonPath("$[0].model", is(car1.getModel())))
                .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
                .andExpect(jsonPath("$[1].model", is(car2.getModel())))
                .andExpect(jsonPath("$[2].maker", is(car3.getMaker())))
                .andExpect(jsonPath("$[2].model", is(car3.getModel())));
                
        verify(service, times(1)).getAllCars();
    }

    @DisplayName("if car exists return details")
    @Test
    public void carExists_thenReturnDetailsTest() throws Exception {
        
        Car car = new Car("fiat", "punto");
        car.setCarId(1L);

        when(service.getCarDetails(car.getCarId())).thenReturn(Optional.of(car));

        mvc.perform(get("/api/cars/" + car.getCarId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.maker", is(car.getMaker())))
                .andExpect(jsonPath("$.model", is(car.getModel())));
        verify(service, times(1)).getCarDetails(car.getCarId());
    }
}
