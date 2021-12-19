package com.example.scan;

import com.example.scan.filter.MyExcludeComponent;
import com.example.scan.filter.MyIncludeComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    @Test
    void filterScanInclude() {
        final BeanA beanA = ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();
    }

    @Test
    void filterScanExclude() {
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean(BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = MyIncludeComponent.class
            ),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = MyExcludeComponent.class
            )
    )
    static class ComponentFilterAppConfig {
    }
}
