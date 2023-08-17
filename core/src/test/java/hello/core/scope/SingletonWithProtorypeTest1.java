package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithProtorypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }


    @Test
    void singletonClientUSePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // 생성시점에 주입된 빈을 사용하므로 프로토타입 빈이라고 해도 같은 인스턴스를 사용
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.logic();
        assertThat(logic1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int logic2 = clientBean2.logic();
//        assertThat(logic2).isEqualTo(2);
        assertThat(logic2).isEqualTo(1);
    }


    /*
     * 생성자를 호출하면서 스프링 컨테이너에서 prototypeBean을 생성해서 반환
     */
    @Scope("singleton")
//    @RequiredArgsConstructor
    static class ClientBean {
//        private final PrototypeBean prototypeBean; // 생성시점에 주입


        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 스프링 컨테이너를 통해 해당 빈을 찾아서 반환

            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
