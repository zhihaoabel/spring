package com.abel.spring.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author Abel
 * @since 5/11/2023 9:23 PM
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
     private static final Logger LOGGER = LoggerFactory.getLogger( CustomClientHttpRequestInterceptor.class);
    @Override
    public ClientHttpResponse intercept ( HttpRequest request, byte[] body, ClientHttpRequestExecution execution ) throws IOException {
        logRequestDetails(request);
        return execution.execute ( request, body );
    }

    private void logRequestDetails(HttpRequest request) {
        LOGGER.info("Headers: {}", request.getHeaders());
        LOGGER.info("Request Method: {}", request.getMethod());
        LOGGER.info("Request URI: {}", request.getURI());
    }
}
