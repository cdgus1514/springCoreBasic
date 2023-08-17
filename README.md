# SpringCoreBasic

* 스프링 핵심원리 기본편 정리
* OCP, DIP 객체지향 설계원칙 준수하여 설계 및 개발


<br><br><br>

---

## 의존관계 분석

1. 추상(인터페이스) 뿐만 아니라 구현 클래스 까지 확인 → 클래스가 아닌 "인터페이스"를 의존하도록 변경필요

- 인터페이스만 의존하도록 변경하려면 별도로 인터페이스 구현 객체를 대신 생성 및 주입해줘야 함.
  → 애플리케이션의 전체 동작방식을 구성하기 위해 구현객체를 "생성"하고 "연결"하는 책임을 가지는 별도 클래스를 설정


- SRP(단일책임원칙) : Appconfig를 통해 구현객체를 생성하고 연결
- DIP(읜존관계역전원칙) : 추상화 인터페이스에만 의존하도록 변경 및 Appconfig를 통해 의존관계를 주입
- OCP(객체지향) : 다형성을 사용하고 DIP를 지킴


<br><br>

---

<br><br>

## IoC

- 프레임워크가 대신 코드를 호출해주는 역활을 하는 것


<br><br>

---

<br><br>

## IoC 컨테이너, DI 컨테이너

- AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는 역활
- 의존관계 주입에 초첨을 맞추어 DI 컨테이너라고 부름 (또는 어샘블러 or 오브젝트 팩토리)


<br><br>

---

<br><br>

## 스프링 컨테이너

- 이전에는 직접 객체를 생성해서 DI를 했지만, 스프링 컨테이너를 통해서 사용
- @Configuration이 붙은 설정정보를 사용해서 @Bean 메서드를 모두 호출해서 반환되는 객체를 스프링 컨테이너에 등록 → 이렇게 등록된 객체를 "스프링 빈"이러고 부름
- 스프링 빈은 @Bean이 붙은 "메서드 이름"으로 사용
- 필요한 객체를 스프링 컨테이너에 등록된 스프링 빈을 불러서 사용 → applicationContext.getBean()을 통해서 메서드를 찾아서 사용


<br><br>

---

<br><br>

## 스프링 컨테이너 생성과정

1) 스프링 컨테이저 생성
- 스프링 컨테이너 생성 시 "스프링 빈 저장소" 생성
- 스프링 컨테이너를 생성할 때는 구성 정보를 지정해줘야 함 (빈 이름/빈 객체)
- 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록

2) 스프링 빈 의존관계 설정
- 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)


<br><br>

---

<br><br>

## BeanFactory와 ApplicationContext

1) BranFactory
- 스프링 컨테이너의 최상위 인터페이스
- 스프링 빈을 관리하고 조회하는 역활 (getBean 제공)

2) ApplicationContext
- BeanFactory 기능을 모두 상속받아서 제공


<br><br>

---

<br><br>

## 스프링 컨테이너 설정방법

1) 자바코드 (어노테이션 사용하는 방법)
2) XML (레거시 프로젝트에서 사용했던 방법)
3) Groovy


<br><br>

---

<br><br>

## 싱글톤 패턴의 문제점 ✦유연성이 떨어짐

1) 구현하는 코드 자체가 많이 들어간다.
2) 의존관계상 클라이언트가 구현 클래스에 의존한다, (DIP 위반)
3) 클라이언트가 구현 클래스에 의존해서 OCP원칙을 위반할 가능성이 높다.
4) 테스트 하기가 어렵다.
5) 내부 속성을 변경하거나 초기화 하기가 어렵다.
6) private 생성자로 자식 클래스를 만들기 어렵다.


<br><br>

---

<br><br>

## 스프링 컨테이너

- 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤(1개만 생성)으로 관리 > 싱글톤 컨테이너 역활
- 싱글톤 패턴 단점을 보안


<br><br>

---

<br><br>

## ★★싱글톤 방식의 주의점

- 여러 클라이언트가 같은 객체를 공유하기 때문에 상태를 유지하게 설계하면 안된다!

>1) 특정 클라이언트에 의존적인 필드가 있으면 안된다.
>2) ⭐특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다
>3) 가급적 읽기만 가능해야 한다 
>4) 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다
>
> ### ⭐스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있다⭐

<br><br>

---

<br><br>

## 싱글톤 @Configuration

- 스프링 컨테이너는 싱글톤 레지스트리로 스프링 빈이 싱글톤이 되도록 보장해야함
- 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용 → @Configuration

> 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 클래스를 상속받은 임의의 다른 클래스를 만들고, 스프링 빈으로 등록
> 
> * @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 해당 빈은 반환하여 "싱글톤을 보장"


<br><br>

---

<br><br>

## 컴포넌트 스캔 - 의존관계 자동주입

* 직접 스프링 빈에 설정정보와 의존관계를 등록하는 것 대신 사용하는 방법

  + 기존 스프링 빈 등록 시 직접 의존관계 주입하는 코드를 따로 작성필요
  + 컴포넌트 스캔을 사용하면 생성자에 @Autowired(의존관계 자동주입)을 사용해서 의존관계를 자동으로 주입



### 1. @ComponentScan (컴포넌트 스캔)

> - @Component가 붙은 모든 클래스를 스프링 빈으로 등록
> - 스프링 빈의 시본 이름은 클래스명을 사용, 맨 앞글자만 소문자를 사용 
>   - ex) MemberService > memberService

### 2. @Autowired (의존관계 자동주입)

> - 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입
>   - 타입이 같은 빈을 찾아서 주입 ex) getBean(MemberService.class)

### 3. 컴포넌트 탐색 위치
> - basePackages = "패키지경로" 를 통해서 필요한 클래스만 사용가능
>   - 탐색위치를 지정하지 않으면 @ComponentScan을 붙인 클래스의 패키지가 시작위치

- 설정 정보 클래스의 위치를 프로젝트 최상단에 두고 사용
  - @SpringBootApplication (스프링 부트도 이 방법을 기본으로 제공)

### 4. 컴포넌트 스캔 대상
- @Component 
- @Controller 
- @Service 
- @Repository 
- @Configuration

<br>

### 5. 컴포넌트 스캔에서 같은 빈 이름으로 등록할 경우

1) 자동 빈 등록으로 중복되는 경우
- ConflictingBeanDefinitionException 발생
<br>
<br>

2) 수동 빈등록과 자동 빈 등록으로 중복되는 경우
- Overriding bean definition for bean
- 수동으로 등록한 빈이 우선권을 가지고 자동 빈을 오바라이딩 해버린다

> * 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나게 되면 오류가 발생하도록 설정
> could not be registered. A bean with that name has already been defined in file [경로] and overriding is disabled.


<br><br>

---

<br><br>

## 의존관계 주입방법

#### 1) 생성자 주입
  > * 생정자를 통해 의존관계를 주입
  > * 생정자 호출시점에 1번만 호출되는것을 보장
  > * ⭐불변/필수 의존관계에 사용
  
#### 2) setter 주입
  > *  필드의 값을 변경하는 수정자 메서드를 통해 의존관계를 주입
  > * 선택/변경 가능성이 있는 의존관계에 사용

#### 3) 필드 주입
  > * 필드에 직접
  > * 외부에서 변경이 불가능해서 테스트가 어렵다 (사용않하는걸 권장) 
  >   - @Configuration, 테스트 코드에서는 사용가능

#### 3) 일반 메서드 주입
  > * 일반 메서드를 통해서 의존관계를 주입
  > * 한번에 여러 필드를 주입가능 (잘 사용하지 않음)

<br>

### 옵션 처리

* 주입할 스프링 빈이 없어도 동작해야 할 경우 사용

> 1. `@Autowired(required=false)`: 자동 주입할 대상이 없으면 호출 안됨
> 2. `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 null 입력
> 3. `Optional<>` : 자동 주입할 대상이 없으면 `Optional.empty` 입력


<br><br>

---

<br><br>

## 최근 DI 프레임워크 생성자 주입을 권장하는 이유

### `불변`

* 대부분 의존관계 주입은 한번 일어나면 종료 시점까지 의존관계를 변경할 일이 없으며 종료 전까지 변하면 안된다.
* 수정자 주입을 사용하면 메서드를 public으로 열어두어야 한다 (누군가 실수로 변경 할 수도 있음)
    + 생성자 주입은 객체를 생성할 떄 <U>1번만 호출</U>되므로 이후 호출될 일이 없어 불변하게 설계가능

### `누락`
* 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우
    + 생성자 주입을 사용하는 경우 테스트 작성 시 필요한 파라미터 바로 확인가능

### `final 키워드`
* 생성자에서 값이 설정되지 않는 오류를 컴파일 시점에서 확인가능


<br><br>

---

<br><br>

## Lombok

> Lombok이 어노테이션 프로세서 기능을 이용해서 컴파일 시점에 생성자 코드를 자동으로 생성

<br>

### `@RequiredArgsConstructor`
- final이 붙은 필드를 모아서 생성자를 자동으로 만들어줌
    -  최근에는 생성자를 1개 두고, `@Autowired`를 생략하는 방식을 주로 사용

<br>


### 조회 대상 빈이 2개 이상일 때 해결방법


### 1. `@Autowired 필드명 매칭`

- `@Autowired`는 타입 매칭을 시도할 때 여러 빈이 존재하면 필드 이름(파라미터 이름)으로 빈 이름을 추가 매칭

1) 타입매칭
2) 타입매칭의 결과가 2개 이상일 때 필드명, 파라미터명으로 빈 이름 매칭

### 2. `@Qualifier`

- 주입 시 추가 구분자를 붙여주는 방법(이름변경 X)
    - 가끔 사용하는 스프링 빈일 경우 `@Qualifier`를 적용해서 명시적으로 사용

1) @Qualifier끼리 매칭
2) 빈 이름 매칭 
3) `NoSuchBeanDefinitionException` 예외 발생

### 3. `@Primary`

- 여러개 존재하는 경우 우선권을 가짐
    - 메인으로 사용하는 스프링 빈일 경우 `@Primary`를 적용해서 사용

<br>

> ❗ `@Primary`는 기본값 처럼 동작, `@Qualifier`는 매우 상세하게 동작 
> 
> 스프링은 자동보다 수동이, 좁은 범위의 선택권이 우선 순위가 높아 `@Qualifier`가 우선권이 높다.

<br>

### 4. 의존관계 자동/수동주입 운영기준

- 스프링이 나오고 자동을 선호나는 추세. 
- `@Component`뿐만 아니라 `@Controller`, `@service`, `@Repository` 지원
- 최근 스프링 부트는 컴포넌트 스캔을 기본으로 사용하고 다양한 스프링 빈들도 조건이 맞으면 자동으로 등록하도록 설계
- 자동 빈 등록을 사용하면 OCP, DIP를 지킬 수 있다.

<br>

#### ❓ 수동 빈 사용을 필요로 하는 경우

1) 업무 로직 빈
- 보통 비지니스 요구사항을 개발할 때 추가되거나 변경
   - 웹을 지원하는 *컨트롤러*
   - 핵심 비지니스 로직이 있는 *서비스*
   - 데이터 계층의 로직을 처리하는 *리포지토리*

2) 기술 지원 빈
- 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용
  - 데이터베이스 연결
  - 공통 로그 처리


> 📌 업무 로직은 컨트롤러, 서비스, 리포지토리 처럼 어느정도 유사한 패턴으로 자동 기능을 적극 사용하는것이 좋으며,
> 보통 문제가 발생해도 어떤 곳에서 발생했는지 명확하게 파악하기 쉽다.   
> 
> 📌기술지원 로직은 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미치며, 적용이 잘 되고 있는지
> 아닌지 조차 파악하기 어려운 경우가 많아 가급적 수동 빈 등록을 사용해서 명확하게 들어내는게 좋다.
> 
> 💥 광범위한 영향의 객체는 수동 빈으로 등록. `설정 정보`에 바로 나타나게 하는 것이 유지보수에 좋다!


<br><br>

---

<br><br>

## 빈 생명주기

<br>
<br>

### 생명주기 콜백

* 스프링 빈은 객체를 생성하고, 의존관계 주입이 끝난 다음에 필요한 데이터를 사용할 수 있는 준비가 완료
  * `스프링`은 의존관계 주입이 끝나면 `스프링 빈`에게 콜백 메서드로 초기화 시점을 알려주는 기능 제공
  * `스프링`은 `스프링 컨테이너`가 종료되기 직전에 소멸 콜백으로 종료작업 진행

<br>

> #### ❗ 스프링 빈의 이벤트 라이프사이클
> 1. `스프링 컨테이너` 생성
> 2. `스프링 빈` 생성
> 3. `의존관계` 주입
> 4. `초기화 콜백` (빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출)
> 5. 사용
> 6. `소멸전 콜백` (빈이 소멸되기 직전에 호출)
> 7. `스프링` 종료
> <br>
> <br>
> > 참고 : 객체의 생성과 초기화는 분리
> > *생성자*는 파라미터를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가지는 반면,    
> > *초기화*는 이렇게 생성된 값들을 사용해서 외부 커넥션을 연결하는 등 무거운 동작을 수행.
>> 
>> 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는것 보다는 객체를 생성/초기화 부분을 명확하게 구분필요

<br>
<br>


#### 📢 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원

* 인터페이스 (`InitializingBean`, `DisposableBean`)
* `설정정보`에 초기화 메서드, 종료 메서드 지정
* `@PostConstruct`, `@PreDestory` 어노테이션 지원 ⭐

<br>

#### 스프링 빈 생명주기 인터페이스의 단점
- 스프링 전용 인터페이스에 의존적
- 초기화, 소멸 메서드의 이름 변경불가
- 코드를 고칠 수 없는 외부 라이브러리에 적용불가

#### 💥 스프링 초창기에 나온 방법들로 지금은 거의 사용하지 않음

<br>

#### 스프링 빈 생명주기 어노테이션 단점
- 외부 라이브러리를 초기화, 종료해야 하는 경우에는 메서드 방식으로 사용

#### ⭐ 최신 스프링에서 가장 권장하는 방법 ⭐


<br><br>

---
<br><br>

## 빈 스코프

* #### 스프링 빈이 컨테이너의 시작과 함께생성되어 종료될 때 까지 유지 
  - 스프링 빈이 기본적으로 `싱글톤 스코프로` 생성되기 때문
  - `스코프`는 빈이 존재할 수 있는 범위

<br>

> 📢 스프링은 다양한 스코프 제공
> 1. `싱글톤` : 기본 스코프, 스프링 컨테이너 시작-종료까지 유지되는 가장 넓은 스코프
> 2. `프로토타입` : 프로토타입 빈의 생성과 의존관계 주입까지만 관여하여 매우 짧은 범위의 스코프 
> 3. `웹 관련 스코프`
>    1. `request` : <U>웹 요청</U>이 들어오고 나갈때 까지 유지
>    2. `session` : <U>웹 세션</U>이 생성되고 종료될때 까지 유지
>    3. `application` : <U>웹의 서블릿 컨텍스트</U>와 같은 범위로 유지

<br>

### 1-1. 프로토타입 스코프

* #### 싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 <U>항상 같은 인스턴스</U>의 스프링 빈을 반환
  1. 싱글톤 스코프의 스프링 빈을 스프링 컨테이너에 요청
  2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환
  3. 이후 스프링 컨테이너에 같은 요청이 와도 `같은 객체 인스턴스의 스프링 빈`을 반환


* #### 프로토타입 스코프를 스프링 컨테이너에 조회하면 <U>새로운 인스턴스</U>를 반환
  1. 프로토타입 스코프의 빈을 스프링 컨테이너에 요청 시 프로토타입 빈을 생성하고 필요 시 의존관계 주입
  2. 스프링 컨테이너는 생성한 프로토타입 빈을 반환
  3. 이후 스프링 컨테이너에 같은 요청이 오면 항상 `새로운 프로토타입 빈`을 생성해서 반환

<br>

> #### 💥 `스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화 까지만 처리`한다는 것.
> 클라리언트에 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않음.   
> 프로토타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에 있고, `@PreDestory`같은 종료메서드가 없다.

<br>

### 1-2. 프로토타입 빈의 특징

* 스프링 컨테이너에 요청할 때마다 새로 생성
* 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입, 초기화 까지만 관여
* 종료 메서드 호출 ❌
* 프로토타입 빈을 조회한 클라이언트가 종료메서드 직접 호출

<br>

### 1-3. 싱글톤 빈과 함께 사용시 문제점

* ❓ 프로토타입 스코프는 항상 새로운 인스턴스를 생성해서 반환하지만 싱글톤 빈과 함께 사용하는 경우 주의

스프링 빈은 일반적으로 <U>싱글톤 빈</U>을 사용하므로 싱글톤 빈이 프로토타입 빈을 사용하게 되며, 싱글톤 빈은 `생성시점에만
의존관계를 주입`받기 때문에 프로토타입 빈이 새로 생성되기는 하지만 싱글톤 빈과 같이 `유지`되는 문제 발생