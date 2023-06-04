package com.api.kimi.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE","OPTIONS");
            }
        };
    }

    //return new WebMvcConfigurer() {
    //            @Override
    //            public void addCorsMappings(CorsRegistry registry) {
    //                registry.addMapping("/**")
    //                        .allowedOriginPatterns("*")
    //                        .allowedMethods("GET", "POST", "PUT", "DELETE")
    //                        .allowedHeaders("*")
    //                        .exposedHeaders("Authorization")
    //                        .allowCredentials(true);
    //            }
    //        };
}
