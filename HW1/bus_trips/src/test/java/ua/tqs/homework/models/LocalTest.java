package ua.tqs.homework.models;

import ua.tqs.homework.models.Country;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.tqs.homework.models.City;
import ua.tqs.homework.models.Location;

public class LocalTest {
    
    Local localStation;
    
    @BeforeEach
    public void setUp() throws Exception {
        Country country = new Country("pt", "Portugal");
        City city = new City("Aveiro", 13708, "86584bb1-a08f-44e0-a576-c911e85996e5", "aveiro");
        Location location = new Location(40.64419290354335, -8.639249163131286);

        localStation = new Local("3800-111", 22.32546, country, "R. de Artur de Almeida Eça", city, "Aveiro (Bus Station)", location, "aveiro-green-lines", false);
    }

    @DisplayName("return a local with the right data")
    @Test
    void getLocalTest(){

        assertAll(
            () -> assertEquals("3800-111", localStation.getZipcode()),
            () -> assertEquals(22.32546, localStation.getScore()),
            () -> assertEquals("pt", localStation.getCountry().getCode()),
            () -> assertEquals("Portugal", localStation.getCountry().getName()),
            () -> assertEquals("R. de Artur de Almeida Eça", localStation.getAddress()),
            () -> assertEquals("Aveiro", localStation.getCity().getName()),
            () -> assertEquals(13708, localStation.getCity().getLegacyId()),
            () -> assertEquals("86584bb1-a08f-44e0-a576-c911e85996e5", localStation.getCity().getId()),
            () -> assertEquals("aveiro", localStation.getCity().getSlug()),
            () -> assertEquals("Aveiro (Bus Station)", localStation.getName()),
            () -> assertEquals(40.64419290354335, localStation.getLocation().getLatitude()),
            () -> assertEquals(-8.639249163131286, localStation.getLocation().getLongitude()),
            () -> assertEquals("aveiro-green-lines", localStation.getSlug()),
            () -> assertEquals(false, localStation.getIsTrain())
        );
    }

    @DisplayName("test to string method for a local")
    @Test
    public void testLocalToString(){
        assertEquals(localStation.toString(), "{ zipcode='3800-111', score='22.32546', country='{ code='pt', name='Portugal'}', address='R. de Artur de Almeida Eça', city='{ name='Aveiro', legacyId='13708', id='86584bb1-a08f-44e0-a576-c911e85996e5', slug='aveiro'}', name='Aveiro (Bus Station)', location='{ latitude='40.64419290354335', longitude='-8.639249163131286'}', slug='aveiro-green-lines', isTrain='false' }");
    }
}
