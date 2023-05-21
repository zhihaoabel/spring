package com.abel.spring.circular_dependencies.controller;

import com.abel.spring.circular_dependencies.services.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abel
 * @since 5/17/2023 9:46 PM
 */
@RestController
public class CircularDepController {

    @Autowired
    private AService aService;
}
