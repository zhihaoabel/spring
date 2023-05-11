package com.abel.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class MySpringApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(MySpringApplication.class, args).close();
    }

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor ();
        // 设置核心线程数
        executor.setCorePoolSize ( 10 );
        // 设置最大线程数
        executor.setMaxPoolSize ( 20 );
        // 设置队列容量
        executor.setQueueCapacity ( 500 );
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds ( 300 );
        // 设置默认线程名称
        executor.setThreadNamePrefix ( "GithubLookup-" );
        // 设置拒绝策略
        executor.setRejectedExecutionHandler ( new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy () );
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown ( true );
        executor.initialize ();
        return executor;
    }
}
