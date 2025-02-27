package com.admin.catalago.infrasctructure;

import com.admin.catalago.application.UseCase;
import com.admin.catalago.domain.category.Category;
import com.admin.catalago.infrasctructure.category.persistense.CategoryJpaEntity;
import com.admin.catalago.infrasctructure.category.persistense.CategoryRepository;
import com.admin.catalago.infrasctructure.configuration.WebServerConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

//    @RabbitListener(queues =  "video.encoded.queue")
//    void dummyListener(){
//
//    }
}