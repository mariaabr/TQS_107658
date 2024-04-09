package ua.tqs.homework.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.tqs.homework.models.BusTrip;
import ua.tqs.homework.models.Fare;

public class BusTripTest {

    BusTrip busTripAveiroViseu;

    @BeforeEach
    public void setUp() throws Exception {

        List<Fare> fares = new ArrayList<>();

        Fare fare = new Fare(3.99, "EUR");
        fares.add(fare);
        
        busTripAveiroViseu = new BusTrip("2024-04-09T09:10:00.000", "2024-04-09T10:15:00.000", "Aveiro  (Bus Station)", "Viseu  (Bus Station)", "01:05", fares);

    }

    @DisplayName("return a bus trip with the right data")
    @Test
    void getBusTripTest(){

        assertAll(
            () -> assertEquals("2024-04-09T09:10:00.000", busTripAveiroViseu.getDepOffset()),
            () -> assertEquals("2024-04-09T10:15:00.000", busTripAveiroViseu.getArrOffset()),
            () -> assertEquals("Aveiro  (Bus Station)", busTripAveiroViseu.getDepName()),
            () -> assertEquals("Viseu  (Bus Station)", busTripAveiroViseu.getArrName()),
            () -> assertEquals("01:05", busTripAveiroViseu.getDuration()),
            () -> assertEquals(3.99, busTripAveiroViseu.getFares().get(0).getPrice()),
            () -> assertEquals("EUR", busTripAveiroViseu.getFares().get(0).getCurrency())
        );
    }

    @DisplayName("test to string method for a bus trip")
    @Test
    public void testBusTripToString(){
        assertEquals(busTripAveiroViseu.toString(), "{ depOffset='2024-04-09T09:10:00.000', arrOffset='2024-04-09T10:15:00.000', depName='Aveiro  (Bus Station)', arrName='Viseu  (Bus Station)', duration='01:05', fares='[{ price='3.99', currency='EUR'}]' }");
    }
}
