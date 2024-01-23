package com.schooldevops.springjava.resilience4jtutorials.controller;

import com.schooldevops.springjava.resilience4jtutorials.service.CircuitBreakerService;
import com.schooldevops.springjava.resilience4jtutorials.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    RetryService retryService;

    @Autowired
    CircuitBreakerService circuitBreakerService;

    @GetMapping("/retry/{command}")
    public ResponseEntity<?> greetingRetry(@PathVariable("command") String command) {
        return ResponseEntity.ok(retryService.greeting(command));
    }

    @GetMapping("/retry/pred/{command}")
    public ResponseEntity<?> greetingRetryPred(@PathVariable("command") String command) {
        return ResponseEntity.ok(retryService.greeting2(command));
    }

    @GetMapping("/circuit/{command}")
    public ResponseEntity<?> greetingCircuit(@PathVariable("command") String command) {
        return ResponseEntity.ok(circuitBreakerService.greeting(command));
    }
}
