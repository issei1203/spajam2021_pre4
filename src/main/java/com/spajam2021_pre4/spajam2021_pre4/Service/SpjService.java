package com.spajam2021_pre4.spajam2021_pre4.Service;

import com.spajam2021_pre4.spajam2021_pre4.Repository.Entity.LogsEntity;
import com.spajam2021_pre4.spajam2021_pre4.Repository.SampleRepository;
import com.spajam2021_pre4.spajam2021_pre4.Repository.SpjRepository;
import model.PresentData;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SpjService {

    private SpjRepository spjRepository;
    private SampleRepository sampleRepository;
    private PresentData presentData;
    private String userid;
    private String firstTime = "";
    private boolean isRun = false;
    private Pattern pattern = Pattern.compile("\"到着予定時刻:.............");

    private Logger logger = Logger.getLogger("DataConvert");

    public SpjService(SpjRepository spjRepository,SampleRepository sampleRepository){
        this.spjRepository = spjRepository;
        this.sampleRepository = sampleRepository;
        System.setProperty("webdriver.chrome.driver","/home/ec2-user/chromedriver");
    }

    @Scheduled(fixedDelay = 7000)
    private void cronJob(){
        if(isRun) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless", "--no-sandbox");

            WebDriver webDriver = new ChromeDriver(chromeOptions);
            webDriver.get(presentData.getUrl());
            try {
                    Thread.sleep(3000);
                    String body = webDriver.getPageSource();
                    Matcher matcher = pattern.matcher(body);
                    List<String> matchBodylist = new ArrayList<String>();
                    while (matcher.find()) {
                        matchBodylist.add(matcher.group());
                    }
                    if (matchBodylist.size() == 1) {;
                        String time = matchBodylist.get(0);
                        logger.log(Level.INFO,"time: " + time);
                        time = time.replace("\"到着予定時刻: ", "");
                        logger.log(Level.INFO,"time: " + time);
                        time = time.split("\"")[0];
                        logger.log(Level.INFO,"time: " + time);

                        if(firstTime.equals("")){
                            firstTime = time;
                        }

                        String st = convertUnixTime(presentData.getArrivalTime());
                        String ft = convertUnixTime(firstTime);
                        String ct = convertUnixTime(time);
                    spjRepository.save(new LogsEntity(0,st,userid,ft,ct));
                }
            } catch (InterruptedException e) {
            }

            webDriver.close();
        }

    }
    public String setLogs(PresentData pd, String uid){
        presentData = pd;
        userid = uid;
        isRun = true;
        return "";
    }

    public Long getSampleCount(){
        return sampleRepository.count();
    }

    public String unSetLogs(){
        isRun = false;
        firstTime = "";
        return "";
    }

    public Long getLogCount(String uid){
        return spjRepository.countByUserId(uid);
    }

    public String getCurrentTimeInLog(Integer id){
        if(id == 0){
            return "";
        }
        String rawTime = spjRepository.getById(id).getCurrentTime();

        return rawTime;
    }

    public String getStartTimeInLog(Integer id){
        if(id == 0){
            return "";
        }
        String rawTime = spjRepository.getById(id).getStartTime();

        return rawTime;
    }

    public String convertUnixTime(String time){
        logger.log(Level.INFO,time);
        String[] timeList = time.split(":");
        String hour = timeList[0];
        String[] timeList2 = timeList[1].split(" ");
        String minute = timeList2[0];
//        String meridian = timeList2[1];
//        if(meridian.equals("PM")){
//            hour = String.valueOf(Integer.parseInt(hour) + 12);
//        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ");
        logger.log(Level.INFO,"hour: " + hour + " minute: " + minute);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2021, 10, 10, Integer.parseInt(hour), Integer.parseInt(minute), 0, 0, ZoneId.systemDefault());
        long epochSecond = zonedDateTime.toEpochSecond();
        logger.log(Level.INFO, String.valueOf(epochSecond));
        return String.valueOf(epochSecond);
    }

    public boolean isRun() {
        return isRun;
    }
}
