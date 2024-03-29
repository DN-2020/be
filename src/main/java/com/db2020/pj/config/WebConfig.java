package com.db2020.pj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 요청에 대해서
        		.allowedOrigins("*")
        		.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
        		.allowCredentials(true); //허용할 오리진들
    }
}
