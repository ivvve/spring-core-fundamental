package hello.aop.proxyvs;

import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK Dynamic Proxy
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB Proxy
@Import({ProxyDIAspect.class, ProxyDITest.MemberServiceImpl.class})
public class ProxyDITest {

    @Autowired
    private MemberService memberService; // JDK Dynamic Proxy OK, CGLIB OK
    @Autowired
    private MemberServiceImpl memberServiceImpl; // JDK Dynamic Proxy X, CGLIB OK

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }

    interface MemberService {
        void hello(final String message);
    }

    @Service
    public static class MemberServiceImpl implements MemberService {
        @Override
        public void hello(final String message) {
            System.out.println("Hello: " + message);
        }
    }
}
