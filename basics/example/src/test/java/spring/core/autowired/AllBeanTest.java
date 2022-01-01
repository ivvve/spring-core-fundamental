package spring.core.autowired;

import com.example.AutoAppConfig;
import com.example.discount.domain.DiscountPolicy;
import com.example.member.domain.Grade;
import com.example.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        final AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // given
        final DiscountService discountService = ac.getBean(DiscountService.class);
        final Member vipUser = new Member(1L, "VIP_USER", Grade.VIP);

        // when
        final BigInteger discountPrice =
                discountService.discount(vipUser, BigInteger.valueOf(10_000), "fixedDiscountPolicy");

        // then
        assertThat(discountPrice).isEqualTo(1_000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(final Map<String, DiscountPolicy> policyMap, final List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public BigInteger discount(final Member member, final BigInteger price, final String discountCode) {
            final DiscountPolicy discountPolicy = this.policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
