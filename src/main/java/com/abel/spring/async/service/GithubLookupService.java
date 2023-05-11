package com.abel.spring.async.service;

import com.abel.spring.async.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author Abel
 * @since 5/11/2023 8:26 PM
 */
@Service
public class GithubLookupService {

    private static final Logger logger = LoggerFactory.getLogger( GithubLookupService.class);

    private final RestTemplate restTemplate;

    public GithubLookupService ( RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser( String user) throws InterruptedException {
        logger.info("Looking up {}", user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
