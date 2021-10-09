package com.spajam2021_pre4.spajam2021_pre4.Service;

import com.spajam2021_pre4.spajam2021_pre4.Repository.SampleRepository;
import com.spajam2021_pre4.spajam2021_pre4.Repository.SpjRepository;
import model.PresentData;
import org.springframework.stereotype.Service;

@Service
public class SpjService {

    private SpjRepository spjRepository;
    private SampleRepository sampleRepository;

    public SpjService(SpjRepository spjRepository,SampleRepository sampleRepository){
        this.spjRepository = spjRepository;
        this.sampleRepository = sampleRepository;
    }

//    private String getExpectedArrival(PresentData presentData){
//
//        return "";
//    }

    public Long getSampleCount(){
        return sampleRepository.count();
//        long l = 1;
//        return l;
    }
}
