package com.mateuszmarcyk.walkthedog.appconfiguration;

import com.mateuszmarcyk.walkthedog.appconfiguration.converters.StringToActivityLevelConverter;
import com.mateuszmarcyk.walkthedog.appconfiguration.converters.StringToFriendRequestStatusConverter;
import com.mateuszmarcyk.walkthedog.appconfiguration.converters.StringToPhotoConverter;
import com.mateuszmarcyk.walkthedog.appconfiguration.converters.StringToProfilePhotoConverter;
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
    StringToFriendRequestStatusConverter getStringToFriendRequestConverter() {
        return new StringToFriendRequestStatusConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getStringToPhotoConverter());
        registry.addConverter(getStringToProfilePhotoConverter());
        registry.addConverter(getStringToActivityLevelConverter());
        registry.addConverter(getStringToFriendRequestConverter());
    }
}
