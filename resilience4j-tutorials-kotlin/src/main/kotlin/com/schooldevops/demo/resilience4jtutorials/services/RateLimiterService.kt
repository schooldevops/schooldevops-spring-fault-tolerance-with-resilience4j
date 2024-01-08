package com.schooldevops.demo.resilience4jtutorials.services

import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.stereotype.Service

@Service
class RateLimiterService {

    /**
     * @RateLimiter:
     *      이 주석은 클래스나 특정 메소드에 적용될 수 있다
     *      클래스에 적용하는 것은 모든 퍼블릭 메소드에 적용하는 것과 동일하다.
     *      주석은 적용된 모든 메소드에 대해 조절을 활성화 한다.
     *      제한 모니터링은 속도를 제한히기를 통해 수행된다.
     *      io.github.resilience4j.ratelimeter 를 참조하자.
     *      Spring을 사용하는 경우 SpEL을 사용하여 이름/fallbackMethod를 확인할 수 있다.
     */
    @RateLimiter(
        name = "myRateLimiter",
        fallbackMethod = "fallbackMethod"
    )
    fun getData(): String {
        return callExternalService()
    }

    fun fallbackMethod(throwable: Throwable) : String {
        return "Fallback Data: " + throwable.message
    }

    private fun callExternalService(): String {
        throw RuntimeException("External Service is unavailable")
    }
}