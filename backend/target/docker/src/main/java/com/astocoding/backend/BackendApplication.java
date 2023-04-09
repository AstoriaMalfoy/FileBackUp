package com.astocoding.backend;

import com.astocoding.backend.anno.JobCollectionInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@Slf4j
@SpringBootApplication
public class BackendApplication{

    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


}
