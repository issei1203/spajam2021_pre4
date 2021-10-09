package com.spajam2021_pre4.spajam2021_pre4.Controller;

import com.spajam2021_pre4.spajam2021_pre4.Service.SpjService;
import model.PresentData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpjController {

    SpjService spjService;

    public SpjController(SpjService spjService){
        this.spjService = spjService;
    }

    @GetMapping("/")
    ResponseEntity<String> hello(){
        return new ResponseEntity<String>("Hello! This is spajam2021_pre4", HttpStatus.OK);
    }

    @GetMapping("/sample")
    ResponseEntity<String> sample(){
        String count = spjService.getSampleCount().toString();
        return new ResponseEntity<String>(count, HttpStatus.OK);
    }

    @GetMapping("/arrival")
    ResponseEntity<String> getArrival(@RequestParam("uid") String uid){
        Integer count = Math.toIntExact(spjService.getLogCount());
        String body = "{\"firstArrivalTime\":\"" + spjService.getStartTimeInLog(count) + "\",\"currentArrivalTime\":\"" + spjService.getCurrentTimeInLog(count) + "\"}";
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }

    @PostMapping("/arrival")
    ResponseEntity<String> setSettings(@RequestParam("googleTripProgressUrl") String url,@RequestParam("startedTime") String startedTime,@RequestParam("uid")String uid){
        PresentData presentData = new PresentData(url,startedTime);
        String body = spjService.setLogs(presentData, uid);
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }
}
