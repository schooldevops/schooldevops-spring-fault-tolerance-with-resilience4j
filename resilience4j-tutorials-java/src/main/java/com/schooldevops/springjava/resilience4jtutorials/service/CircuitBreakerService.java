package com.schooldevops.springjava.resilience4jtutorials.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {

    private static final String CIRCUIT_BREAKER_NAME = "example-circuit";


    @Autowired
    TestService testService;

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackMessage")
    public String greeting(String command) {
        return testService.greeting(command);
    }

    public String fallbackMessage(String command, RuntimeException ex) {
        return String.format("Fallback for CircuitBreaker ~. %s - %s", command, ex.getMessage());
    }
}
