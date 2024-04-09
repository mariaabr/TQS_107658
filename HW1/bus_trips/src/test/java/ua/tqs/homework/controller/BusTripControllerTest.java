package ua.tqs.homework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ua.tqs.homework.models.*;
import ua.tqs.homework.services.BusTripService;

@WebMvcTest(RestController.class)
public class BusTripControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    BusTripService busTripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @DisplayName("get all trips between 2 cities x and y")
    @Test
    void  getAllTripsBetween2CitiesTest() throws Exception {
        
        List<BusTrip> trips = new ArrayList<>();

        List<Fare> fares = new ArrayList<>();

        Fare fare = new Fare(3.99, "EUR");
        fares.add(fare);
        
        String from_id = "86584bb1-a08f-44e0-a576-c911e85996e5";
        String to_id = "219d6bc8-e9a6-4cef-a40f-20011f772c4e";
        String date = "2024-04-09";
        Integer passengers = 1;
        String currency = "EUR";

        trips.add(new BusTrip("2024-04-09T09:10:00.000", "2024-04-09T10:15:00.000", "Aveiro  (Bus Station)", "Viseu  (Bus Station)", "01:00", fares));
        trips.add(new BusTrip("2024-04-09T10:15:00.000", "2024-04-09T11:15:00.000", "Aveiro  (Bus Station)", "Viseu  (Bus Station)", "01:00", fares));
        trips.add(new BusTrip("2024-04-09T11:30:00.000", "2024-04-09T12:35:00.000", "Aveiro  (Bus Station)", "Viseu  (Bus Station)", "01:05", fares));
        
        // trips.add(new BusTrip("86584bb1-a08f-44e0-a576-c911e85996e5", "74923e7b-3ace-43d2-8278-41ee00193a85", "Aveiro  (Bus Station)", "Porto (TIC - Campanhã)", "00:50", 0, fares));
        // trips.add(new BusTrip("74923e7b-3ace-43d2-8278-41ee00193a85", "219d6bc8-e9a6-4cef-a40f-20011f772c4e", "Porto (TIC - Campanhã)", "Viseu  (Bus Station)", "01:05", 0, fares));
        
        when(busTripService.getBusTripsBetweenCities(from_id, to_id, date, passengers, currency)).thenReturn(trips);

        mvc.perform(
            get("/api/trips?from_id="+from_id+"&to_id="+to_id+"&date="+date+"&adult="+passengers+"&currency="+currency)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            // Adicione aqui as verificações JSONPath esperadas para os objetos BusTrip retornados
            .andExpect(jsonPath("$.*", hasSize(3)))
            .andExpect(jsonPath("$[0].depName", is("Aveiro  (Bus Station)")))
            .andExpect(jsonPath("$[0].arrName", is("Viseu  (Bus Station)")))
            .andExpect(jsonPath("$[0].fares[0].price", is(3.99))
        );

        verify(busTripService, times(1)).getBusTripsBetweenCities(from_id, to_id, date, passengers, currency);
    }

    @DisplayName("get local with autocompletion")
    @Test
    void  getLocalTest() throws Exception {
        
        Country country = new Country("pt", "Portugal");
        City city = new City("Aveiro", 13708, "86584bb1-a08f-44e0-a576-c911e85996e5", "aveiro");
        Location location = new Location(40.64419290354335, -8.639249163131286);

        Local localStation = new Local("3800-111", 22.32546, country, "R. de Artur de Almeida Eça", city, "Aveiro (Bus Station)", location, "aveiro-green-lines", false);

        String query ="aveiro";

        when(busTripService.getLocal("aveiro")).thenReturn(localStation);

        mvc.perform(
            get("/api/autocomplete?query=aveiro")
            // .param("query", "aveiro")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.zipcode", is("3800-111")))
            .andExpect(jsonPath("$.address", is("R. de Artur de Almeida Eça")))
            .andExpect(jsonPath("$.city.id", is("86584bb1-a08f-44e0-a576-c911e85996e5"))
        );

            
        verify(busTripService, times(1)).getLocal(query);
    }
}
