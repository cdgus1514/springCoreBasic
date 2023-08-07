package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    /**
     * 싱글톤 상태유지 문제점
     */
    @Test
    void statefulServiceSingletone() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);


        // Thread A : A사용자가 1만원
        int userA = statefulService1.order("userA", 10000);

        // ThreadB : B사용자가 2만원
        int userB = statefulService2.order("userB", 20000);


        // Thread A : A사용자 금액조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        System.out.println("userA = " + userA);


//        assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
    }


    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}