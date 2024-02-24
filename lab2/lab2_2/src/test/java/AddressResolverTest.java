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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.tqs.geocoding.*;
import ua.tqs.connection.*;

@ExtendWith(MockitoExtension.class)
public class AddressResolverTest {
    
    @Mock
    ISimpleHttpClient mockHttpClient;

    @InjectMocks
    AddressResolverService addressResolverService;

    @BeforeEach
    public void SetUp(){
        addressResolverService = new AddressResolverService(mockHttpClient);
    }
    
    final private String geocodeInfo = "{ \"info\": { \"statuscode\": 0, \"copyright\": { \"text\": \"© 2024 MapQuest, Inc.\", \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\","
                                    + "\"imageAltText\": \"© 2024 MapQuest, Inc.\" }, \"messages\": [] }, \"options\": { \"maxResults\": 1, \"ignoreLatLngInput\": false }, \"results\": [ {"
                                    + "\"providedLocation\": { \"latLng\": { \"lat\": 41.0067, \"lng\": -8.64433 } }, \"locations\": [ { \"street\": \"Avenida 8\", \"adminArea6\": \"Espinho\","
                                    + "\"adminArea6Type\": \"Neighborhood\", \"adminArea5\": \"Espinho\", \"adminArea5Type\": \"City\", \"adminArea4\": \"Aveiro\", \"adminArea4Type\": \"County\","
                                    + "\"adminArea3\": \"\", \"adminArea3Type\": \"State\", \"adminArea1\": \"PT\", \"adminArea1Type\": \"Country\", \"postalCode\": \"4500-208\", \"geocodeQualityCode\": \"P1AAA\","
                                    + "\"geocodeQuality\": \"POINT\", \"dragPoint\": false, \"sideOfStreet\": \"R\", \"linkId\": \"0\", \"unknownInput\": \"\", \"type\": \"s\", \"latLng\": {"
                                    + "\"lat\": 41.00672, \"lng\": -8.6445 }, \"displayLatLng\": { \"lat\": 41.00661, \"lng\": -8.64435 }, \"mapUrl\": \"\" } ] } ] }";

    @DisplayName("test for the findAddressForLocation")
    @Test
    public void testFindAddressForLocation() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {
        
        when(mockHttpClient.doHttpGet(anyString())).thenReturn(geocodeInfo);

        Optional<Address> address = addressResolverService.findAddressForLocation(41.0067, -8.64433);

        assertEquals(Optional.of(new Address("Avenida 8", "Espinho", "4500-208", "")), address);

        verify(mockHttpClient, times(1)).doHttpGet(anyString());     
    }
    
    @DisplayName("test for bad coordinates")
    @Test
    public void testBadCoordinates() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {
        Optional<Address> address1 = addressResolverService.findAddressForLocation(60.1520, -195);
        Optional<Address> address2 = addressResolverService.findAddressForLocation(120.7826, -4);
        Optional<Address> address3 = addressResolverService.findAddressForLocation(78.0283, -220.48715);

        assertFalse(address1.isPresent());
        assertFalse(address2.isPresent());
        assertFalse(address3.isPresent());
    }

    // @DisplayName("test for null coordinates")
    // @Test
    // public void testNullCoordinates() throws URISyntaxException, IOException, ParseException, org.json.simple.parser.ParseException {
    //     Optional<Address> address = addressResolverService.findAddressForLocation(null, null);

    //     assertFalse(address.isPresent());
    // }
}
