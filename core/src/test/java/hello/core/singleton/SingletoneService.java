package hello.core.singleton;

public class SingletoneService {

    // 실행 시 static 영역에 인스턴스(객체) 1개 생성
    private static final SingletoneService instance = new SingletoneService();

    // 객체가 필요할 때 static 메서드를 조회하도록 설정
    public static SingletoneService getInstance() {
        return instance;
    }

    // 외부에서 new 키워드를 사용한 객체생성 불가
    private SingletoneService() {}

    public void test() {
        System.out.println("싱글톤 객체 호출");
    }


    /**
     * 싱글톤 패턴의 문제점 > 유연성이 떨어짐
     *
     * 1) 구현하는 코드 자체가 많이 들어간다.
     * 2) 의존관계상 클라이언트가 구현 클래스에 의존한다, (DIP 위반)
     * 3) 클라이언트가 구현 클래스에 의존해서 OCP원칙을 위반할 가능성이 높다.
     * 4) 테스트 하기가 어렵다.
     * 5) 내부 속성을 변경하거나 초기화 하기가 어렵다.
     * 6) private 생성자로 자식 클래스를 만들기 어렵다.
     *
     */
}
