package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 호출 할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다름
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
        SingletoneService instanceService1 = SingletoneService.getInstance();
        SingletoneService instanceService2 = SingletoneService.getInstance();

        // 같은 객체를 호출
        System.out.println("instanceService1 = " + instanceService1);
        System.out.println("instanceService2 = " + instanceService2);

        // isSameAs > ==
        // isEqualTo > equals()
        assertThat(instanceService1).isSameAs(instanceService2);
    }
}
