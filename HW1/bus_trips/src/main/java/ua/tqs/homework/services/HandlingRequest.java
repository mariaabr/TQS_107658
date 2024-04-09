package ua.tqs.homework.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;


public class HandlingRequest {
    // public HandlingRequest() throws InterruptedException {
    //     this.client = HttpClients.createDefault();
    // }

    private String API_HOST = "flixbus2.p.rapidapi.com";
    private String URI_API = "https://flixbus2.p.rapidapi.com";
    private String API_KEY = "db23f18c3cmshe60f626dc73822cp1c9610jsn64c56d9a3ea5";

    public String connectAPI(String endpoint) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(URI_API + endpoint))
        .header("X-RapidAPI-Host", API_HOST)
        .header("X-RapidAPI-Key", API_KEY)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

        String response =  HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
        return response;
    }
}