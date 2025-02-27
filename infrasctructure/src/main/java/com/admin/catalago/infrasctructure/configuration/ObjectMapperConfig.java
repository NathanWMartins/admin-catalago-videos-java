package com.admin.catalago.infrasctructure.configuration;


import com.admin.catalago.infrasctructure.configuration.json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return Json.mapper();
    }
}
