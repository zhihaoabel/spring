package com.abel.spring.commons.configuration.restTemplate;

import com.abel.spring.commons.interceptor.CustomClientHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * @author Abel
 * @since 5/11/2023 9:25 PM
 */
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize ( RestTemplate restTemplate ) {
        restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor ());
    }
}
