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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    public Long getLogCount(){
        return spjRepository.count();
    }

    public String getTimeInLog(Integer id){
        if(id == 0){
            return "";
        }
        String rawTime = spjRepository.getById(id).getTime();

        return convertUnixTime(rawTime);
    }

    public String convertUnixTime(String time){
        String[] timeList = time.split(":");
        String hour = timeList[0];
        String[] timeList2 = timeList[1].split(" ");
        String minute = timeList2[0];
        String meridian = timeList2[1];
        if(meridian.equals("PM")){
            hour = String.valueOf(Integer.parseInt(hour) + 12);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2021, 10, 10, Integer.parseInt(hour), Integer.parseInt(minute), 0, 0, ZoneId.systemDefault());
        long epochSecond = zonedDateTime.toEpochSecond();
        return String.valueOf(epochSecond);
    }
}
