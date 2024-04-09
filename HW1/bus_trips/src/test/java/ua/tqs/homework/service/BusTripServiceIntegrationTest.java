package ua.tqs.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class BusTripServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBusTripsBetweenCities() throws Exception {
        String from_id = "86584bb1-a08f-44e0-a576-c911e85996e5";
        String to_id = "219d6bc8-e9a6-4cef-a40f-20011f772c4e";
        String date = "09.04.2024";
        Integer passengers = 1;
        String currency = null;

        // api url with parameters
        String url = "/api/trips?from_id=" + from_id + "&to_id=" + to_id + "&date=" + date + "&adult=" + passengers + "&currency=" + currency;

        // get request api
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        // add verifications
    }
}