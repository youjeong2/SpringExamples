package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.http.WebSocket;

@Configuration
// spring MVC 전역 활성화
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
        }

}
