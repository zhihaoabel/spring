package com.abel.spring.async.runner;

import com.abel.spring.async.model.User;
import com.abel.spring.async.service.GithubLookupService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author Abel
 * @since 5/11/2023 9:02 PM
 */
@Component
public class AsyncRunner implements CommandLineRunner {
     private static final Logger logger = LoggerFactory.getLogger( AsyncRunner.class);

      private final GithubLookupService githubLookupService;

      @Resource
      private Executor executor;

      public AsyncRunner ( GithubLookupService githubLookupService ) {
          this.githubLookupService = githubLookupService;
      }
    @Override
    public void run ( String... args ) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = githubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = githubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = githubLookupService.findUser("Spring-Projects");

        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
    }
}
