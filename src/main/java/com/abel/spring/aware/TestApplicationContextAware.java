package com.abel.spring.aware;

import com.abel.spring.aware.component.A;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Abel
 * @since 5/21/2023 1:39 PM
 */
@Component
@Slf4j
@Getter
@Setter
public class TestApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext ( @NotNull ApplicationContext applicationContext ) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void test () {
        doSomething ();
    }

    public A getPrototypeBean () {
        return applicationContext.getBean ( "a", A.class );
    }

    public void doSomething () {
        log.info ( "prototypeBean1.hashCode (): {}", getPrototypeBean ().hashCode () );
        log.info ( "prototypeBean2.hashCode (): {}", getPrototypeBean ().hashCode () );
    }
}