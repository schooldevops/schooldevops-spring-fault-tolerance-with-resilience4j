package com.schooldevops.springjava.resilience4jtutorials.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public String greeting(String command) {

        log.info("--------------- Greeting Service ...");
        if ("S".equals(command)) {
            return "Hello ~~~ MyFriends";
        } else {
            throw new RuntimeException("Exception ~~~ Greeing");
        }
    }

    public String greeting2(String command) {

        log.info("--------------- Greeting Service 2...");
        if ("S".equals(command)) {
            return "Hello ~~~ MyFriends";
        } else {
            return command;
        }
    }
}
