package com.schooldevops.demo.resilience4jtutorials

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.retry.Retry
import io.github.resilience4j.retry.RetryConfig
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.Duration

@SpringBootApplication
class Resilience4jTutorialsApplication: CommandLineRunner {
	override fun run(vararg args: String?) {
//		Retry 설정
		val retryConfig = RetryConfig.custom<RetryConfig>()
			.maxAttempts(3)
			.waitDuration(Duration.ofMillis(500))
			.build()

		val retry = Retry.of("myRetry", retryConfig)

//		Circuit Breaker Configuration
		val circuitBreakerConfig = CircuitBreakerConfig.custom()
			.failureRateThreshold(50.0F)
			.waitDurationInOpenState(Duration.ofMillis(1000))
			.permittedNumberOfCallsInHalfOpenState(2)
			.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
			.slidingWindowSize(2)
			.build()

		val circuitBreaker = CircuitBreaker.of("myCircuitBreaker", circuitBreakerConfig)

//		외부 서비스 호출하기 이때 Retry와 Fallback을 적용한다.
		val result = Retry.decorateSupplier(retry) {
			CircuitBreaker.decorateSupplier(circuitBreaker) {
				try {
					callExternalService()
				} catch (e: Exception) {
					fallbackMethod()
				}
			}
		}

		print(result)
	}

	fun callExternalService(): String {
		throw RuntimeException("External Service is unavailable")
	}

	fun fallbackMethod(): String {
		return "Fallback Data: Service is currently unavailable"
	}
}

fun main(args: Array<String>) {
	runApplication<Resilience4jTutorialsApplication>(*args)
}
