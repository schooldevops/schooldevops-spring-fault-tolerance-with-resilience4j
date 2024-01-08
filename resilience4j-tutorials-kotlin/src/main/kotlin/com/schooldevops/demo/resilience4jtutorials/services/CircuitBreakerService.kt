package com.schooldevops.demo.resilience4jtutorials.services

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class CircuitBreakerService {

    /**
     * @CircuitBreaker:
     *      이 어노테이션은 클래스나 특정 메소드에 적용될 수 있다.
     *      클래스에 적용하는 것은 모든 퍼블릿 메소드에 적용하는것과 동일하다.
     *      주석을 활성화 하면 주석이 적용된 모든 메소드에 대해서 백앤드 모니터링을 할 수 있다.
     *      백엔드 모니터링은 circuit breaker 를 통해 수행가능하다.
     *      자세한 내용은 io.github.resilience4j.circuitbreaker.CircuitBreaker 를 참조하자.
     *      Spring을 사용하는 경우 SpEL을 사용하여 name및 fallbackMethod를 확인할 수 있다.
     */
    @CircuitBreaker(
        name = "myCircuitBreaker",
        fallbackMethod = "fallbackMethod"
    )
    fun getData(): String {
        return callExternalService();
    }

    fun fallbackMethod(throwable: Throwable) : String {
        return "Fallback Data: " + throwable.message
    }

    private fun callExternalService(): String {
        throw RuntimeException("External Service is unavailable")
    }
}