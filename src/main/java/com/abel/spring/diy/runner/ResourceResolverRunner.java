package com.abel.spring.diy.runner;

import com.abel.spring.diy.model.ResourceResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ResourceResolverRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ResourceResolver resolver = new ResourceResolver("com.abel.spring.diy");
        List<String> classes = resolver.scan(resource -> {
            String name = resource.name();
            if (name.endsWith(".class")) {
                return name.substring(0, name.length() - 6).replace("/", ".").replace("\\", ".");
            }
            return null;
        });
        log.info("找到的类：----------------------------------------{}", classes);
    }
}
