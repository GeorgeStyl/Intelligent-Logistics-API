package org.stylianopoulos.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
public class IntelligentLogisticsApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(IntelligentLogisticsApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IntelligentLogisticsApiApplication.class, args);
    }

    // * This Bean executes immediately after the context is loaded.
    // ? Why CommandLineRunner? It's a fail-safe way to ensure logic runs at startup.
    @Bean
    public CommandLineRunner warmUpInternal() {
        return args -> {
            logger.info("// * [CRITICAL] STARTING SYSTEM WARM-UP PING * //");

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(2))
                    .build();

            // ! Target project's endpoint
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/orders"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{" +
                                    "\"customerName\":\"WARMUP_BOT\"," +
                                    "\"weight\":1.0," +
                                    "\"destination\":\"INTERNAL\"," + // <--- ADD THIS COMMA
                                    "\"shippingType\": \"FREE\"" +
                                    "}"
                    ))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                logger.info("// * [SUCCESS] WARM-UP STATUS: {} | RESPONSE: {} * //",
                        response.statusCode(), response.body());
            } catch (Exception e) {
                // ? If it fails, it's usually because Tomcat/Netty hasn't bound the port yet.
                logger.warn("// ! [RETRY] Port 8080 not ready yet. This is expected in some environments. ! //");
            }
        };
    }
}
