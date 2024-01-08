package com.schooldevops.demo.resilience4jtutorials.services

import io.github.resilience4j.retry.annotation.Retry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RetryService(
    @Autowired val exceptionService: ExceptionService
) {

    /**
     * @Retry 어노테이션
     *      이 어노테이션은 클래스나 특정 메소드에 적용이 가능하다.
     *      클래스에 적용하는 것이나 public method에 적용하는 것은 동일하다.
     *      어노테이션은 적용된 위치 모든 메소드에 대해서 백앤드 재시도를 수행할 수 있도록 해준다.
     *      백엔드 재시도는 retry를 통해서 수행된다.
     *      스프링을 이용한다면 name와 fallbackMethod은 Spring Expression Language(SpEL)을 이용하여 해석할 수 있다.
     *  -----------------------------------------
     *  - name:
     *      동기화 재시도에 대한 이름을 설정한다.
     *      SpEL 표현으로 작성할 수 있다.
     *      메소드의 첫번재 이름을 이용하고자 한다면 root.args[0], #p0 또는 #a0 으로 표현하면 된다.
     *      메소드 이름은 #root.methodName을 통해 액세스 가능하다.
     *      Spring Bean에서 메소드를 호출하려면 @yourBean.yourMethod(#a0)을 전달할 수 있다.
     *  - fallbackMethod:
     *      폴백 메시드 이름이다.
    **/
    @Retry(
        name = "myRetry",
        fallbackMethod = "fallbackMethod"
    )
    fun getData() : String {
        return exceptionService.callExternalServcie()
    }

    fun fallbackMethod(throwable: RuntimeException): String {
        return "Fallback Data: " + throwable.message
    }



}