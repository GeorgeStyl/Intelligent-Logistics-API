package org.stylianopoulos.logistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

// * FLAG: CONCURRENCY & MULTITHREADING
@Configuration
@EnableAsync
@EnableJpaAuditing // Activate the @CreatedDate logic
public class AsyncConfig {

    @Bean(name = "logisticsExecutor")
    public Executor logisticsExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("LogisticsWorker-");
        executor.initialize();
        return executor;
    }
}