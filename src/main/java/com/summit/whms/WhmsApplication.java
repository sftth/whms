package com.summit.whms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
//@MapperScan(basePackages="com.summit.whms")
public class WhmsApplication {
    private static final Logger logger = LoggerFactory.getLogger(WhmsApplication.class);


//    @Value("${spring.redis.host}")
//    private String redisHost;

    public static void main(String[] args) {
        SpringApplication.run(WhmsApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner runner() {
//        return (a) -> {
//            logger.info("@@@@@@CommandLineRummer: " + redisHost);
//        };
//
//    };
}
