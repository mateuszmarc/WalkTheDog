package com.mateuszmarcyk.walkthedog.appconfiguration;

import com.mateuszmarcyk.walkthedog.appconfiguration.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WalkTheDogWebConfig implements WebMvcConfigurer {

    @Bean
    StringToPhotoConverter getStringToPhotoConverter() {
        return new StringToPhotoConverter();
    }

    @Bean
    StringToProfilePhotoConverter getStringToProfilePhotoConverter() {
        return new StringToProfilePhotoConverter();
    }

    @Bean
    StringToActivityLevelConverter getStringToActivityLevelConverter() {
        return new StringToActivityLevelConverter();
    }

    @Bean
    StringToRequestStatusConverter getStringToRequestConverter() {
        return new StringToRequestStatusConverter();
    }

    @Bean
    StringToWalkStatusConverter getStringToWalkStatusConverter() {
        return new StringToWalkStatusConverter();
    }

    @Bean
    StringToNotificationStatusConverter getstringToNotificationStatusConverter() {
        return new StringToNotificationStatusConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getStringToPhotoConverter());
        registry.addConverter(getStringToProfilePhotoConverter());
        registry.addConverter(getStringToActivityLevelConverter());
        registry.addConverter(getStringToRequestConverter());
        registry.addConverter(getStringToWalkStatusConverter());
        registry.addConverter(getstringToNotificationStatusConverter());
    }
}
