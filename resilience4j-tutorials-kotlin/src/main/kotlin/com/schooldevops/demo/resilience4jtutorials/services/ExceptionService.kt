package com.schooldevops.demo.resilience4jtutorials.services

import org.springframework.stereotype.Service

@Service
class ExceptionService {

    fun callExternalServcie(): String {
        throw RuntimeException("External Service is unavailable.")
    }
}