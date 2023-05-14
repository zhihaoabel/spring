package com.abel.spring.actuator.controller;

import com.abel.spring.actuator.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Abel
 * @since 5/12/2023 11:59 PM
 */
@Controller
public class HelloWorldController {
    private static final String STRING_TEMPLATE = "Hello, %s!";
    private final AtomicInteger counter = new AtomicInteger ();

    @GetMapping ( "/hello-world" )
    @ResponseBody
    // @ResponseBody annotation tells Spring MVC not to render a model into a view but, rather, to write the returned object into the response body
    // It does so by using one of Spring’s message converters, Jackson2, to convert the Greeting instance to JSON.
    public Greeting sayHello ( @RequestParam ( name = "name", required = false, defaultValue = "Stranger" ) String name ) {
        return new Greeting ( counter.incrementAndGet (), String.format ( STRING_TEMPLATE, name ) );
    }
}
