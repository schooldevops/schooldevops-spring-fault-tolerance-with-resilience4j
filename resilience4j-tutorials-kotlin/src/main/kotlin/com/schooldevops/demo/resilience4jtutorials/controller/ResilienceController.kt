package com.schooldevops.demo.resilience4jtutorials.controller

import com.schooldevops.demo.resilience4jtutorials.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class ResilienceController(
    @Autowired val retryService: RetryService,
    @Autowired val circuitBreakerService: CircuitBreakerService,
    @Autowired val rateLimiterService: RateLimiterService,
    @Autowired val bulkheadService: BulkheadService,
    @Autowired val timeLimiterService: TimeLimiterService
) {

    @GetMapping("/retry")
    fun retry() : ResponseEntity<String>{
        return ResponseEntity.ok(retryService.getData())
    }

    @GetMapping("/rate")
    fun rate() : ResponseEntity<String> {
        return ResponseEntity.ok(rateLimiterService.getData())
    }

    @GetMapping("/bulkhead")
    fun bulkhead() : ResponseEntity<String> {
        return ResponseEntity.ok(bulkheadService.getData())
    }

    @GetMapping("/time")
    fun time() : ResponseEntity<String> {
        val data = timeLimiterService.getData()
        return ResponseEntity.ok(data.join())
    }

}