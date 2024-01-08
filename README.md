# Resilience4j 를 활용하여 Fault Tolerance 어플리케이션 구축하기. 

## Fault Tolerance 개요

- Fault Tolerance 는 시스템의 일부 요소가 장애를 일으켜도, 전체 서비스가 정상적으로 동작할 수 있도록 하는 것을 말한다. 
- 시스템이 예상치 못한 문제 또는 장애 상황에서도 부분적으로 또는 전체적으로 기능을 유지하고 회복할수 있는 능력을 의미한다. 

### Fault Tolerance 기술 소개 

- Fault Tolerance 는 다양한 기술이 존재하며 다음과 같다. 

#### Retry (재시도)

- 호출이 실패하면 지정된 횟수나 시간동안 재시도 하는 메커니즘이다. 
- Exponential Backoff 와 같은 Backoff 전약을 사용하여 재시도 하는 간격을 조절할 수 있다. 
- 예를 들어 첫번째 시도오류시 다음 재시도까지 2초대기 그다음은 4초 등으로 대기 간격을 두고 재시도 한다. 

#### Circuit Breaker Pattern (서킷 브레이커 패턴)

- 일정 시간 동안 일정 횟수 이상의 실패가 발생하면 회로를 열어 호출을 차단하는 메커니즘이다. 
- 이를 통해 서버 부하를 방지하고, 장애가 발생할 때 빠르게 실패를 감지할 수 있다. 

#### Fallback (폴백)

- 호출이 실패한 경우 대체 동작을 수행하여 정상적으로 동작하도록 한다. 
- 사용자에게 유용한 정보를 제공하거나, 기본값을 반환하는 전약이다. 

#### Timeout

- 호출에 대한 응답을 기다리는 최대 시간을 설정하여 일정 시간내에 응답이 없으면 실패로 처리하는 메커니즘이다. 
- 무한정 기다려서 스레드를 모두 소진하는 것을 방지하고 Fast Fail(빠른실패) 를 유도한다. 

## Spring Boot 에서 Fault Tolerance 프레임워크 

- Spring Boot는 여러가지 Fault Tolerance 를 구현할 수 있도록 의존성 라이브러리를 제공한다. 
- 주요 기능을 위해 Hystrix와 Resilience4j 가 대표적이다. 

### Hystrix:

- Netflix에서 개발한 오픈소스 라이브러이다. 
- 서킷브레이커, 폴백, 재시도 등 기능을 제공한다. 
- Spring Cloud Netflix 프로젝트의 일부로 사용되며 @HystrixCommand 어노테이션을 이용한다. 

### Resilience4j:

- 자바 8, 함수형 프로그래밍을 활용하여 회복성 라이브러리이며, 서킷 브레이커, 리트라이, 벌크헤드, 타임아웃 등 다양한 기능을 제공한다. 
- Spring Boot와 편리하게 사용할 수 있는 통합 기능을 제공한다. @Retry, @CircuitBreaker, @RateLImit 등 어노테이션을 제공한다. 

## WrapUp

- Fault Tolerance에 대해서 알아 보았고, 어떠한 도구들이 존재하는지 알 수 있었다. 
- 여기서는 Resilience4j 를 활용한 실제 코드를 작성해 볼 것이다. 