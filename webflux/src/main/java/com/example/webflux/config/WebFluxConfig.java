package com.example.webflux.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class WebFluxConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
