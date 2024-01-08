package com.schooldevops.demo.resilience4jtutorials.services

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import org.springframework.stereotype.Service

@Service
class BulkheadService {

    /**
     * @Bulkhead
     *      이 주석은 클래스나 특정 메소드에 적용될 수 있다.
     *      클래스에 적용하는 것은 공용 메소드에 적용하는 것과 동일하다.
     *      스프링을 사용하는 경우 SpEL을 사용하여 name, fallbackMethod를 확인할 수 있다.
     */
    @Bulkhead(
        name = "myBulkhead",
        fallbackMethod = "fallbackMethod",
        type = Bulkhead.Type.SEMAPHORE,
    )
    fun getData(): String{
        return callExternalService();
    }

    fun fallbackMethod(throwable: Throwable): String{
        return "Fallback Data: " + throwable.message
    }

    private fun callExternalService(): String {
        throw RuntimeException("External Service is unavailable")
    }
}