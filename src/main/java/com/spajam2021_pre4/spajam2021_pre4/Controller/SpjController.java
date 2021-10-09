package com.spajam2021_pre4.spajam2021_pre4.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpjController {
    @GetMapping("/")
    ResponseEntity<String> hello(){
        return new ResponseEntity<String>("Hello! This is spajam2021_pre4", HttpStatus.OK);
    }

    @GetMapping("/arrival")
    ResponseEntity<String> getArrival(){
        return new ResponseEntity<String>("getArrival", HttpStatus.OK);
    }

    @PostMapping("/arrival")
    ResponseEntity<String> setSettings(@RequestParam String url){
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
}
