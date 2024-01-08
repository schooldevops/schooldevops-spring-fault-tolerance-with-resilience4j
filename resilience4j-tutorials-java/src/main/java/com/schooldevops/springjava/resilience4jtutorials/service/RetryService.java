package com.schooldevops.springjava.resilience4jtutorials.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    private final String SERVICE = "service";
    private final String SERVICE2 = "service-predicate";

    @Autowired
    TestService testService;

    @Retry(name = SERVICE, fallbackMethod = "fallbackMessage")
    public String greeting(String command) {
        return testService.greeting(command);
    }

    @Retry(name = SERVICE2, fallbackMethod = "fallbackMessage")
    public String greeting2(String command) {
        return testService.greeting2(command);
    }

    public String fallbackMessage(String command, RuntimeException ex) {
        return String.format("Fallback for retry ~. %s - %s", command, ex.getMessage());
    }
}
