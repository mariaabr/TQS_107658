package ua.tqs.homework.controller;

import java.io.IOException;

// import org.apache.http.HttpStatus;
// import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import ua.tqs.homework.models.BusTrip;
import ua.tqs.homework.models.Local;
import ua.tqs.homework.services.BusTripService;
import ua.tqs.homework.services.HandlingRequest;

@RestController
@RequestMapping("/api")
public class BusTripController {

    @Autowired
    private BusTripService busTripService;

    @GetMapping("/trips")
     public List<BusTrip> getBusTripsBetweenCities(@RequestParam(value = "from_id") String from_id, @RequestParam(value = "to_id") String to_id, @RequestParam(value = "date") String date, @RequestParam(value = "adult") Integer passengers, @RequestParam(value = "currency", required = false) String currency) throws IOException, InterruptedException{
        System.out.println("date: " + date);
        
        // String[] date_info = date.split("-");
        // System.out.println("date_info: " + date_info.toString());
        // // format dd.mm.yyyy
        // String new_date = date_info[2] + "." + date_info[1] + "." + date_info[0];

        // System.out.println("new_date: " + new_date);
        List <BusTrip> busTrips = busTripService.getBusTripsBetweenCities(from_id, to_id, date, passengers, currency);

        return busTrips;
    }

    @GetMapping("/autocomplete")
     public Local getLocal(@RequestParam(value = "query") String name) throws IOException, InterruptedException, org.apache.tomcat.util.json.ParseException{
        Local local = busTripService.getLocal(name);

        return local;
    }
}