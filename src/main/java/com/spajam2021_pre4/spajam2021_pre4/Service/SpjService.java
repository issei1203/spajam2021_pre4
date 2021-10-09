package com.spajam2021_pre4.spajam2021_pre4.Service;

import com.spajam2021_pre4.spajam2021_pre4.Repository.SampleRepository;
import com.spajam2021_pre4.spajam2021_pre4.Repository.SpjRepository;
import model.PresentData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SpjService {

    private SpjRepository spjRepository;
    private SampleRepository sampleRepository;

    public SpjService(SpjRepository spjRepository,SampleRepository sampleRepository){
        this.spjRepository = spjRepository;
        this.sampleRepository = sampleRepository;
    }

    public String getExpectedArrival(PresentData presentData){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(
                URI.create(presentData.getUrl())
        ).build();
        String body;
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String redirectUrl = httpResponse.headers().map().get("location").get(0);

            HttpClient httpClient2 = HttpClient.newHttpClient();
            HttpRequest httpRequest2 = HttpRequest.newBuilder(
                    URI.create(redirectUrl)
            ).build();
            HttpResponse<String> httpResponse2 = httpClient2.send(httpRequest2, HttpResponse.BodyHandlers.ofString());
            body = httpResponse2.body();

        }catch (IOException | InterruptedException ignored){
            body = "";
        }
        return body;
    }

    public Long getSampleCount(){
        return sampleRepository.count();
    }
}
