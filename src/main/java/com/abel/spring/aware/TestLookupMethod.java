package com.abel.spring.aware;

import com.abel.spring.aware.component.A;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author Abel
 * @since 5/21/2023 2:49 PM
 */
@Slf4j
@Component
public abstract class TestLookupMethod {
    @Lookup
    public abstract A getPrototypeBean ();

    @PostConstruct
    public void test () {
        doSomething ();
    }

    public void doSomething () {
        log.info ( "TestLookupMethod1.hashCode (): {}", getPrototypeBean ().hashCode () );
        log.info ( "TestLookupMethod2.hashCode (): {}", getPrototypeBean ().hashCode () );
    }
}
