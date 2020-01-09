package com.elemonated.networker.rest;

import com.elemonated.networker.model.HelloMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardRest {

    @GetMapping("/hello")
    public HelloMessage getHelloMessage() {
        return new HelloMessage("Hello from JAVA");
    }
}
