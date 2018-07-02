package com.xu.artemis.demo.web;

import com.xu.artemis.client.ArtemisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan()
public class AppConfig {

    @Bean
    public ArtemisConfig apolloConfig() {
        return new ArtemisConfig();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
