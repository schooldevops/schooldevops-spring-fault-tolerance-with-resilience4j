package com.schooldevops.demo.resilience4jtutorials.services

import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TimeLimiterService {

    /**
     * @TimeLimiter:
     *      이 주석은 클래스나 특정 메소드에 적용될 수 있다.
     *      클래스에 적용하는 것은 모든 퍼블릭 메소드에 적용하는것과 동일하다.
     *      주석은 적용되는 모든 메소드에 대해 시간제한을 활성화 한다.
     *      SpEL을 사용하여 name및 fallbackMethod를 확인할 수 있다.
     */
    @TimeLimiter(
        name = "myTimeLimiter",
        fallbackMethod = "fallbackMethod"
    )
    fun getData(): CompletableFuture<String> {
        return CompletableFuture.supplyAsync{ callExternalService() }
    }

    fun fallbackMethod(throwable: Throwable): CompletableFuture<String>{
        return CompletableFuture.completedFuture("Fallback Data: " + throwable.message);
    }

    private fun callExternalService(): String {
        throw RuntimeException("External Service is unavailable");
    }
}