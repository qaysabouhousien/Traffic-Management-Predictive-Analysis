package com.Configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Application Configuration, Contains CORS Mapping Configuration.
 * @author - Qays
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {



    /**
     * Handling CORS Requests And Allowing Request Methods
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST","PUT", "DELETE")
                .allowedHeaders("*");
    }



}