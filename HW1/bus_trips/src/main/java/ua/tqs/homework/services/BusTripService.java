package ua.tqs.homework.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ua.tqs.homework.models.BusTrip;
import ua.tqs.homework.models.City;
import ua.tqs.homework.models.Country;
import ua.tqs.homework.models.Fare;
import ua.tqs.homework.models.Local;
import ua.tqs.homework.models.Location;
import ua.tqs.homework.services.HandlingRequest;

@Service
public class BusTripService {

    private List<BusTrip> tripsList = new ArrayList<>();
    private Local localData;

    public List<BusTrip> getBusTripsBetweenCities(String from_id, String to_id, String date, Integer passengers, String currency) throws InterruptedException, IOException {

        HandlingRequest request = new HandlingRequest();

        if (currency == null) {
            String endpoint = "/trips?from_id=" + from_id + "&to_id=" + to_id + "&date=" + date + "&adult="
                    + passengers;
            String data = request.connectAPI(endpoint);
            // System.out.println("data: " + data);

            JSONObject jsonObject = new JSONObject(data);
            JSONArray journeysArray = (JSONArray) jsonObject.get("journeys");
            // System.out.println("journeys array: " + journeysArray);

            tripsList = getBusTripList(journeysArray);

        } else {
            String endpoint = "/trips?from_id=" + from_id + "&to_id=" + to_id + "&date=" + date + "&adult=" + passengers
                    + "&currency=" + currency;
            String data = request.connectAPI(endpoint);
            // System.out.println("data: " + data);

            JSONObject jsonObject = new JSONObject(data);
            JSONArray journeysArray = (JSONArray) jsonObject.get("journeys");
            // System.out.println("journeys array: " + journeysArray);

            tripsList = getBusTripList(journeysArray);
        }

        // System.out.println("tripsList aquiiiiiiiiiii retornadas: " + tripsList);

        return tripsList;
    }

    public Local getLocal(String query) throws InterruptedException, IOException {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getLocalCityId'");

        HandlingRequest request = new HandlingRequest();

        if (!query.equals("")) {
            String endpoint = "/autocomplete?query=" + query;
            String data = request.connectAPI(endpoint);
            // System.out.println("data: " + data);

            JSONArray jsonArray = new JSONArray(data);
            // System.out.println("json array: " + jsonArray);

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            // System.out.println("json object: " + jsonObject);

            localData = getLocalObject(jsonObject);

            String CityId = localData.getCity().getId();
            String CityName = localData.getCity().getName();

            // System.out.println("CityId: " + CityId);
            // System.out.println("CityName: " + CityName);

        }

        return localData;
    }

    // helpfull methods

    public List<BusTrip> getBusTripList(JSONArray jsonArray) {
        List<BusTrip> trips = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObjectTrip = (JSONObject) jsonArray.get(i);
            // BusTrip tripData = getTripObject(objectJSON);
            // System.out.println("Trip data: " + tripData);

            String depOffset = jsonObjectTrip.get("dep_offset").toString();
            String depName = jsonObjectTrip.get("dep_name").toString();
            String arrName = jsonObjectTrip.get("arr_name").toString();

            // if (depOffset.equals(expectedDepOffset) && depName.equals(expectedFromId) &&
            // arrName.equals(expectedToId)) {

            String arrOffset = jsonObjectTrip.get("arr_offset").toString();

            String duration = jsonObjectTrip.get("duration").toString();
            // Integer changeovers = Integer.parseInt(jsonObjectTrip.get("changeovers").toString());
            // String deeplink = jsonObjectTrip.get("deeplink").toString();

            JSONArray faresArray = (JSONArray) jsonObjectTrip.get("fares");

            List<Fare> fares = new ArrayList<>();

            // verify array size
            if (faresArray != null) {
                // create Fares
                System.out.println("objeto fare hereeeeeeee: " + faresArray.getJSONObject(0).toString());
                for (int j = 0; j < faresArray.length(); j++) {
                    // for (Object fareObj : faresArray) {
                        JSONObject fareJson = (JSONObject) faresArray.getJSONObject(j);
    
                        // initialize object Fare
                        if (fareJson.has("price") && fareJson.has("currency")) {
                            Double farePrice = Double.parseDouble(fareJson.get("price").toString());
                            String fareCurrency = fareJson.get("currency").toString();
    
                            // if (fareCurrency.equals(expectedCurrency)) {
                            // add Fare to the fares list
                            Fare fare = new Fare(farePrice, fareCurrency);
                            fares.add(fare);
                        } else if (fareJson.has("price")){

                            Double farePrice = Double.parseDouble(fareJson.get("price").toString());

                            Fare fare = new Fare(farePrice);
                            fares.add(fare);
                        }
                    }
            }

            BusTrip trip = new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares);
            System.out.println("trip hereeeeeeeeeeee: " + trip.toString());

            trips.add(trip);
            // }
        }
        return trips;
    }

    // public List<BusTrip> getBusTripListCurrency(JSONArray jsonArray) {
    //     List<BusTrip> trips = new ArrayList<>();

    //     for (int i = 0; i < jsonArray.length(); i++) {

    //         JSONObject jsonObjectTrip = (JSONObject) jsonArray.get(i);
    //         // BusTrip tripData = getTripObject(objectJSON);
    //         // System.out.println("Trip data: " + tripData);

    //         String depOffset = jsonObjectTrip.get("dep_offset").toString();
    //         String depName = jsonObjectTrip.get("dep_name").toString();
    //         String arrName = jsonObjectTrip.get("arr_name").toString();

    //         // if (depOffset.equals(expectedDepOffset) && depName.equals(expectedFromId) &&
    //         // arrName.equals(expectedToId)) {

    //         JSONArray faresArray = (JSONArray) jsonObjectTrip.get("fares");
    //         System.out.println("faresArrayyyyyyyyyyyyy: " + faresArray);

    //         List<Fare> fares = new ArrayList<>();

    //         // verify array size
    //         if (faresArray != null) {
    //             // create Fares
    //             System.out.println("objeto fare hereeeeeeee: " + faresArray.getJSONObject(0).toString());
    //             for (int j = 0; j < faresArray.length(); j++) {
    //             // for (Object fareObj : faresArray) {
    //                 JSONObject fareJson = (JSONObject) faresArray.getJSONObject(j);

    //                 // initialize object Fare
    //                 if (fareJson.has("price") && fareJson.has("currency")) {
    //                     Double farePrice = Double.parseDouble(fareJson.get("price").toString());
    //                     String fareCurrency = fareJson.get("currency").toString();

    //                     // if (fareCurrency.equals(expectedCurrency)) {
    //                     // add Fare to the fares list
    //                     Fare fare = new Fare(farePrice, fareCurrency);
    //                     fares.add(fare);
    //                 }

    //                 String arrOffset = jsonObjectTrip.get("arr_offset").toString();

    //                 String duration = jsonObjectTrip.get("duration").toString();
    //                 // Integer changeovers = Integer.parseInt(jsonObjectTrip.get("changeovers").toString());
    //                 // String deeplink = jsonObjectTrip.get("deeplink").toString();

    //                 BusTrip trip = new BusTrip(depOffset, arrOffset, depName, arrName, duration, fares);

    //                 if (trips.contains(trip) == false) {
    //                     trips.add(trip);
    //                 }
    //                 // }
    //             }
    //         }
    //         // }

    //     }
    //     return trips;
    // }

    // public BusTrip getTripObject(JSONObject obj, String expectedDepOffset, String
    // expectedFromId, String expectedToId) {

    // String depOffset = obj.get("dep_offset").toString();
    // String depName = obj.get("dep_name").toString();
    // String arrName = obj.get("arr_name").toString();

    // if (depOffset.equals(expectedDepOffset) && depName.equals(expectedFromId) &&
    // arrName.equals(expectedToId)) {
    // trips.add(getTripObject(tripJson));
    // }

    // String arrOffset = obj.get("arr_offset").toString();

    // String duration = obj.get("duration").toString();
    // Integer changeovers = Integer.parseInt(obj.get("changeovers").toString());
    // String deeplink = obj.get("deeplink").toString();

    // JSONArray faresArray = (JSONArray) obj.get("fares");

    // List<Fare> fares = new ArrayList<>();

    // // verify array size
    // if (faresArray != null) {
    // // create Fares
    // for (Object fareObj : faresArray) {
    // JSONObject fareJson = (JSONObject) fareObj;

    // // initialize object Fare
    // Double farePrice = Double.parseDouble(fareJson.get("price").toString());
    // String fareCurrency = fareJson.get("currency").toString();

    // // add Fare to the fares list
    // Fare fare = new Fare(farePrice, fareCurrency);
    // fares.add(fare);
    // }
    // }

    // BusTrip trip = new BusTrip(depOffset, arrOffset, depName, arrName, duration,
    // changeovers, deeplink, fares);

    // return trip;
    // }

    private Local getLocalObject(JSONObject obj) {

        System.out.println("hello");

        String zipcode = obj.get("zipcode").toString();
        Double score = Double.parseDouble(obj.get("score").toString());
        JSONObject countryObj = obj.getJSONObject("country");
        String address = obj.get("address").toString();
        JSONObject cityObj = obj.getJSONObject("city");
        String name = obj.get("name").toString();
        // Integer legacyId = Integer.parseInt(obj.get("legacy_id").toString());
        JSONObject locationObj = obj.getJSONObject("location");
        // String id = obj.get("id").toString();
        // Integer importanceOrder =
        // Integer.parseInt(obj.get("importance_order").toString());
        String slug = obj.get("slug").toString();
        Boolean isTrain = Boolean.parseBoolean(obj.get("is_train").toString());

        // initialize object Country
        System.out.println("Country: " + countryObj.toString());

        String countryCode = countryObj.getString("code");
        String countryName = countryObj.getString("name");

        Country country = new Country(countryCode, countryName);

        // initialize object City
        System.out.println("City: " + cityObj.toString());

        String cityName = cityObj.getString("name");
        Integer cityLegacyId = cityObj.getInt("legacy_id");
        String cityId = cityObj.getString("id");
        String citySlug = cityObj.getString("slug");

        City city = new City(cityName, cityLegacyId, cityId, citySlug);

        // initialize object Location
        System.out.println("Location: " + locationObj.toString());

        Double locationLon = locationObj.getDouble("lon");
        Double locationLat = locationObj.getDouble("lat");

        Location location = new Location(locationLat, locationLon);

        Local local = new Local(zipcode, score, country, address, city, name, location, slug, isTrain);

        return local;
    }
}