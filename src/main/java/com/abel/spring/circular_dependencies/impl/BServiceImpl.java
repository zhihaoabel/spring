package com.abel.spring.circular_dependencies.impl;

import com.abel.spring.circular_dependencies.services.AService;
import com.abel.spring.circular_dependencies.services.BService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Abel
 * @since 5/17/2023 9:39 PM
 */
@Component
public class BServiceImpl implements BService {
    private AService aService;

    @Autowired
    private ApplicationContext applicationContext;

    public void BService ( AService aService ) {
        this.aService = aService;
    }

    @Override
    @PostConstruct
    public void test () {
        applicationContext.getBean ( AService.class );
    }
}
