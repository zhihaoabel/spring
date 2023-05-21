package com.abel.spring.circular_dependencies.impl;

import com.abel.spring.circular_dependencies.services.AService;
import com.abel.spring.circular_dependencies.services.BService;
import org.springframework.stereotype.Component;

/**
 * @author Abel
 * @since 5/17/2023 9:38 PM
 */
@Component
public class AServiceImpl implements AService {
    private BService bService;

    public void AService ( BService bService ) {
        this.bService = bService;
    }
}
