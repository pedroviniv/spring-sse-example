/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.sse.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author Pedro Arthur
 */

@SpringBootApplication
@Configuration
@EnableScheduling
public class SseExampleApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(SseExampleApplication.class, args);
    }
}
