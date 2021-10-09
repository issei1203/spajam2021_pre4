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
    ResponseEntity<String> getArrival(){
        Integer count = Math.toIntExact(spjService.getLogCount());
        String body = spjService.getTimeInLog(count);
        return new ResponseEntity<String>(body, HttpStatus.OK);
    }

    @PostMapping("/arrival")
    ResponseEntity<String> setSettings(@RequestParam String url){
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
}
