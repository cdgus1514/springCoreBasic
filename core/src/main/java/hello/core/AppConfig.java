package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * 생성자 주입을 통해서 구현체를 결정. 해당 구현체는 인터페이스만을 의존(DIP)
     * @return
     */


    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService
    // Call AppConfig.memberRepository

    // 서로 다른객체로 생성될 것처럼 보이나 모두 같은 객체로 생성된다. (memberRepository 처음 1번만 실행)
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService


    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
