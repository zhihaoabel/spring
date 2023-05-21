package com.abel.spring.commons.configuration.restTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author Abel
 * @since 5/11/2023 9:14 PM
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean

    public CustomRestTemplateCustomizer customRestTemplateCustomizer () {
        return new CustomRestTemplateCustomizer ();
    }

    @Bean
    @DependsOn("customRestTemplateCustomizer")
    public RestTemplateBuilder restTemplateBuilder () {
        return new RestTemplateBuilder (customRestTemplateCustomizer ());
    }
}
