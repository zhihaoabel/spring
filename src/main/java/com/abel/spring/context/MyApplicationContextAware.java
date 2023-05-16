package com.abel.spring.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Abel
 * @since 5/15/2023 12:32 AM
 */
@Slf4j
@Component
public class MyApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext ( ApplicationContext applicationContext ) throws BeansException {
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory ();
        Class <?> type = beanFactory.getType ( "helloWorldController" );
        log.info ( "type: {}", type );
    }
}
