package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {

        /*
         * - 수정자 메서드 주입을 사용하는 경우 코드를 확인하기 전까지는 어떤 파라미터가 들어가는지 모름
         * - 생성자 주입을 사용하는 경우 필요가 파라미터 바로 확인 가능
         */

//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L, "itemA", 10000);

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}