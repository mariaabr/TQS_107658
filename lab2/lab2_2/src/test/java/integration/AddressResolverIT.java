package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ua.tqs.geocoding.*;
import ua.tqs.connection.*;

public class AddressResolverIT {

    private AddressResolverService addressResolverService;

    @BeforeEach
    public void SetUp(){
        addressResolverService = new AddressResolverService(new TqsBasicHttpClient());
    }

    @DisplayName("test for the findAddressForLocation")
    @Test
    public void testFindAddressForLocation() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {
        Optional<Address> address = addressResolverService.findAddressForLocation(41.0067, -8.64433);

        assertEquals(Optional.of(new Address("Avenida 8", "Espinho", "4500-208", "")), address);
    }
    
    @DisplayName("test for bad coordinates")
    @Test
    public void testBadCoordinates() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {
        Optional<Address> address1 = addressResolverService.findAddressForLocation(60.1520, -195);
        Optional<Address> address2 = addressResolverService.findAddressForLocation(78.0283, -220.48715);

        assertFalse(address1.isPresent());
        assertFalse(address2.isPresent());
    }
}