package com.example.productservice1.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
