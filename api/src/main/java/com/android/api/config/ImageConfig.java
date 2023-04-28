package com.android.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.android.api.service.StorageService;

public class ImageConfig {
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args -> {
            storageService.init();
        });
    }

    // @Bean
    // public StandardServletMultipartResolver multipartResolver() {
    //     StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
    //     resolver.setDefaultEncoding("UTF-8");
    //     return resolver;
    // }
}
