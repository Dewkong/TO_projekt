package com.objectzilla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import({PersistenceConfig.class, JavaFxConfig.class})
public class AppConfig {
}
