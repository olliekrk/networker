package com.elemonated.networker;

import com.elemonated.networker.service.GoogleCalendarObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NetApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetApplication.class, args);
    }
}
